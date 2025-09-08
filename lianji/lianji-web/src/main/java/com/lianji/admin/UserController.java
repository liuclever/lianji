package com.lianji.admin;

import com.lianji.dto.LoginDto;
import com.lianji.globalException.AppExceptionCodeMsg;
import com.lianji.result.Result;
import com.lianji.service.UserService;
import com.lianji.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
   private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto){
        //判断用户名和密码是否为空
        if(StringUtils.isEmpty( loginDto.getUserName())&&StringUtils.isEmpty(loginDto.getPassWord())){
            return Result.error(AppExceptionCodeMsg.ISN_NOT_USER);
        }
       LoginVo loginVo=userService.login(loginDto);

        return Result.success(loginVo);

    }
}
