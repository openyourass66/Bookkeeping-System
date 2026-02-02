package com.zhang.Mapper;

import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    @Select("select * from user where username=#{username} and password=#{password}")
    User selectByUsernameAndPAssword(LoginDTO loginDTO);

    /**
     * 注册
     * @param user
     */
    @Insert("insert into user(username,password,name,gender,phone,self_description,create_time,update_time) " +
            "values(#{username},#{password},#{name},#{gender},#{phone},#{self_description},#{create_time},#{update_time})")
    void insert(User user);
}
