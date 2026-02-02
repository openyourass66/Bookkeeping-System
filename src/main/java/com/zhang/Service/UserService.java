package com.zhang.Service;

import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.LoginVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserService {
    /**
     * 登录
     * @param loginDTO
     * @return
     */

    LoginVO login(LoginDTO loginDTO);

    /**
     * 注册
     * @param user
     */
    void register(User user);
}
