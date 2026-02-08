package com.zhang.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhang.Mapper.EmpMapper;
import com.zhang.Pojo.DTO.EmpQueryDTO;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.Emp;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Service.EmpService;
import com.zhang.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO loginDTO){
        Emp emp=empMapper.selectByUsernameAndPAssword(loginDTO);
        if(emp != null){
            //生成jwt令牌
            Map<String,Object> claims=new HashMap<>();
            claims.put("empId",emp.getId());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginVO(emp.getId(),emp.getUsername(),jwt);
        }
        return null;
    }
    /**
     * 分页查询
     * @param empQueryDTO
     * @return
     */
    public PageResult<Emp> page(EmpQueryDTO  empQueryDTO){
        PageHelper.startPage(empQueryDTO.getPage(),empQueryDTO.getPageSize());
        Page<Emp> page =  empMapper.page(empQueryDTO);
        return new PageResult<>(page.getTotal(),page.getResult());
    }
    /**
     * 新增职员
     * @param emp
     */
    @Override
    public void save(Emp emp){
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }
    /**
     * 修改职员信息
     * @param emp
     */
    public void update(Emp emp){
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    /**
     *  启停
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id){
        Emp emp=new Emp();
        emp.setId(id);
        emp.setStatus(status);
        empMapper.update(emp);
    }
    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Long id){
        empMapper.deleteById(id);
    }
}
