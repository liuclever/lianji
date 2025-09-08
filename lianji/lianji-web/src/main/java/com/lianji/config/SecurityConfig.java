package com.lianji.config;

import com.lianji.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {
    /**
     * 定义一个密码编码器 Bean
     * Spring Security 会自动寻找这个 Bean 来进行密码的加密和比对
     * @return 密码编码器实例
     */

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //使用BC强哈希进行加密
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //1.关闭 csrf 禁用 CSRF (跨站请求伪造) 保护。
                .csrf().disable()
                //2.设置Session 管理 策略为无状态,
                //作用：这是实现无状态认证的基石。它明确告诉 Spring Security：“请不要为这个应用创建或使用任何 HttpSession”。
                //结果：服务器不会在内存中为用户保留任何会话信息。每次请求都是完全独立的，认证状态完全依赖于请求中携带的 JWT。这极大地提升了应用的可伸缩性
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //3.配置授权规则
                .authorizeRequests()
                //允许所有用户访问 "/login" ,允许匿名访问
                .antMatchers("/admin/login").anonymous()
                //除此之外都要认证
                .anyRequest().authenticated()
                .and()
                // 添加JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();

    }


}
