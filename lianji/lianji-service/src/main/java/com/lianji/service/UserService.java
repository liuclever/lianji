package com.lianji.service;

import com.lianji.dto.LoginDto;
import com.lianji.vo.LoginVo;

public interface UserService {

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);
}
