package com.zhang.Service;

import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.DTO.UpdatePasswordDTO;
import com.zhang.Pojo.DTO.UserQueryDTO;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Pojo.VO.UserVO;

public interface UserService {
    /**
     * 分页查询
     * @param userQueryDTO
     * @return
     */
    PageResult<UserVO> page(UserQueryDTO userQueryDTO);
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    LoginVO login(LoginDTO loginDTO);

    /**
     * 新增用户
     * @param user
     */
    void save(User user);
    /**
     * 修改密码
     * @param updatePasswordDTO
     */
    void updatePassword(UpdatePasswordDTO updatePasswordDTO);
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectById(Long id);

    /**
     * 修改用户
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteById(Long id);



}
