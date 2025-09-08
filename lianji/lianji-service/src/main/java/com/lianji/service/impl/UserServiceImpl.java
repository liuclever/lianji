package com.lianji.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lianji.Utils.JwtUtils;
import com.lianji.service.UserService;
import com.lianji.mapper.UserMapper;
import com.lianji.dto.LoginDto;
import com.lianji.globalException.AppException;
import com.lianji.globalException.AppExceptionCodeMsg;
import com.lianji.po.User;
import com.lianji.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginVo login(LoginDto loginDto) {

        log.info("开始处理登录请求，用户名: {}", loginDto.getUserName());
        if(StringUtils.isEmpty(loginDto.getUserName()) || StringUtils.isEmpty(loginDto.getPassWord())){
            log.info("用户名， 密码为空");
            throw new AppException(AppExceptionCodeMsg.ISN_NOT_USER);
        }


            //1.根据用户名查询 用户
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            //从User表里 获取 userName字段，与loginDto里面进行比较
            queryWrapper.eq(User::getUserName, loginDto.getUserName());
            User user = userMapper.selectOne(queryWrapper);

            //2.判断是否存在
            if (user == null) {
                log.warn("登录失败，用户{}不存在", loginDto.getUserName());
                throw new AppException(AppExceptionCodeMsg.ISN_NOT_EXIST);
            }

            //3.校验密码

            Boolean isPassword = checkPassword(loginDto.getPassWord(), user.getPassWord());
            if (!isPassword) {
                log.info("输入密码为:{}",loginDto.getPassWord());
                log.info("数据库密码为:{}",user.getPassWord());
                log.warn("登录失败，密码错误");
                throw new AppException(AppExceptionCodeMsg.INVALID_USER);
            }
            //4.检查用户 状态
            if (user.getStatus() == 0) {
                log.warn("登录失败，用户{}被禁用", loginDto.getUserName());
                throw new AppException(AppExceptionCodeMsg.USER_DISABLED);
            }

            //5.生成jwt,token
        String token = jwtUtils.generateToken(user.getUserName());

            log.info("登录成功，用户{}，密码****", loginDto.getUserName());

            //6.封装
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(user, loginVo);
            loginVo.setToken(token);

            return loginVo;


        }
    /**
     * 加密
     */
    public void registerUser(String userName, String passWord){
        String encode = passwordEncoder.encode(passWord);
        log.info("加密前的密码: {}", passWord);
        log.info("加密后的密码: {}", encode);
    }

    /**
     * 校对密码
     * @param password
     * @param encodePassword
     * @return
     */
    public Boolean checkPassword(String password,String encodePassword){

        return passwordEncoder.matches(password,encodePassword);
    }




}