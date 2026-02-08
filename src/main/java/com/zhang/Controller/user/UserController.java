package com.zhang.Controller.user;

import com.zhang.Pojo.DTO.UpdatePasswordDTO;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Service.UserService;
import com.zhang.Utils.CurrentHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录，用户信息：{}", loginDTO);
        LoginVO loginVO = userService.login(loginDTO);
        if (loginVO == null) {
            return Result.error("用户名或密码输出错误");
        }
        return Result.success(loginVO);
    }
    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public Result logout() {
        log.info("退出登录");
        CurrentHolder.remove();
        return Result.success();
    }
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        log.info("新增用户:{}", user);
        userService.save(user);
        return Result.success();
    }
    /**
     * 修改密码
     * @param updatePasswordDTO
     * @return
     */
    @PutMapping("/updatePassword")
    public Result changePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        log.info("修改密码，用户信息：{}", updatePasswordDTO);
        userService.updatePassword(updatePasswordDTO);
        return Result.success();
    }
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        log.info("修改用户信息：{}", user);
        userService.update(user);
        return Result.success();
    }
    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            log.info("用户注销：{}", id);
            userService.deleteById(id);
            return Result.success();
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

}
