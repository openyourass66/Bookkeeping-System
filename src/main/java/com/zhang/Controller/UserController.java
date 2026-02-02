package com.zhang.Controller;

import com.zhang.Constant.LoginConstant;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Service.UserService;
import com.zhang.Utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody  LoginDTO loginDTO){
        LoginVO loginVO = userService.login(loginDTO);
        if(loginVO == null){
            return Result.error(LoginConstant.LOGIN_FAIL);
        }
        return Result.success(loginVO);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.register(user);
        return Result.success();
    }
    @PostMapping("/logout")
    public Result logout(){
        log.info("用户退出登录");
        CurrentHolder.remove();
        return Result.success();
    }
}
