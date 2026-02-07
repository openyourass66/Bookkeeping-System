package com.zhang.Service;

import com.zhang.Pojo.DTO.EmpQueryDTO;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.Emp;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.VO.LoginVO;


public interface EmpService {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 分页查询
     * @param empQueryDTO
     * @return
     */
    PageResult<Emp> page(EmpQueryDTO  empQueryDTO);
    /**
     *  保存
     * @param emp
     */
    void save(Emp emp);

    /**
     * 修改职员信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 启停
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);


}
