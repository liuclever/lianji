package com.lianji.filter;

import com.lianji.Utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. 从请求头中获取 Authorization 字段
        final String authHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // 2. 检查 Header 是否存在，并且是否以 "Bearer " 开头
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtUtils.extractUsername(jwt);
                log.debug("从jwt中取得用户名: {}", username);
            } catch (Exception e) {
                // Token 解析失败 (比如过期、格式错误等)
                log.warn("JWT token parsing failed: {}", e.getMessage());
            }
        }

        // 3. 暂时跳过复杂的验证逻辑，等应用正常启动后再完善
        // TODO: 后续添加用户验证和SecurityContext设置逻辑

        // 4. 放行请求，让它进入后续的过滤器
        filterChain.doFilter(request, response);
    }
}