package com.zhang.Controller.admin;

import com.zhang.Pojo.DTO.EmpQueryDTO;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.Emp;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Service.EmpService;
import com.zhang.Utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/emp")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;
    /**
     * 职员登录
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO){
        log.info("职员登录，职员信息：{}",loginDTO);
        LoginVO loginVO = empService.login(loginDTO);
        if(loginVO == null){
            return Result.error("用户名或密码输出错误");
        }
        return Result.success(loginVO);
    }
    /**
     * 分页查询
     * @param empQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result page(EmpQueryDTO  empQueryDTO){
        log.info("分页查询用户信息：{}", empQueryDTO);
        PageResult<Emp> list = empService.page(empQueryDTO);
        return Result.success(list);
    }
    @GetMapping("/logout")
    public Result logout(){
        log.info("退出登录");
        CurrentHolder.remove();
        return Result.success();
    }
    /**
    /**
     * 新增职员
     * @param emp
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Emp emp){
        log.info("保存用户:{}", emp);
        empService.save(emp);
        return Result.success();
    }
    /**
     * 修改职员
     * @param emp
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Emp emp){
        log.info("更新用户:{}", emp);
        empService.update(emp);
        return Result.success();
    }
    /**
     * 启停用职员
     */
    @PutMapping("/startOrStop/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("修改用户状态:{}", status);
        empService.startOrStop(status,id);
        return Result.success();
    }
    /**
     * 删除职员
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除职员{}", id);
        empService.deleteById(id);
        return Result.success();
    }

}
