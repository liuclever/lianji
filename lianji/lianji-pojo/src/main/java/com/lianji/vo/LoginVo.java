package com.lianji.vo;

import lombok.Data;

@Data
public class LoginVo {
    private Long userId;
    private String userName;
    private String password;
    private String token;
}
