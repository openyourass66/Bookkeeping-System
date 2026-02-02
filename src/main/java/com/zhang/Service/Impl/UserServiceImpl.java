package com.zhang.Service.Impl;

import com.zhang.Mapper.UserMapper;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.LoginVO;
import com.zhang.Service.UserService;
import com.zhang.Utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @Override
    public LoginVO login(LoginDTO  loginDTO){
        User user=userMapper.selectByUsernameAndPAssword(loginDTO);
        if(user != null){
            log.info("登录成功,用户:{}",user);
            //生成jwt令牌
            Map<String,Object> claims=new HashMap<>();
            claims.put("userId",user.getId());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginVO(user.getId(),user.getUsername(),jwt);
        }
        return null;
    }
    /**
     * 注册
     * @param user
     */
    @Override
    public void register(User user){
        user.setSelf_description("这个人很懒，什么都没有留下");
        user.setCreate_time(LocalDateTime.now());
        user.setUpdate_time(LocalDateTime.now());
        userMapper.insert(user);
    }
}

