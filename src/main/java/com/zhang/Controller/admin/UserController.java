package com.zhang.Controller.admin;

import com.zhang.Pojo.DTO.UserQueryDTO;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Pojo.Entity.User;
import com.zhang.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("AdminUserController")
@RequestMapping("/admin/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/page")
    public Result page(UserQueryDTO userQueryDTO){
        log.info("分页查询用户信息");
        PageResult<User> list = userService.page(userQueryDTO);
        return Result.success(list);
    }
    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        log.info("保存用户:{}", user);
        userService.save(user);
        return Result.success();
    }
    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        log.info("更新用户:{}", user);
        userService.update(user);
        return Result.success();
    }
    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除用户{}", id);
        userService.deleteById(id);
        return Result.success();
    }
}
