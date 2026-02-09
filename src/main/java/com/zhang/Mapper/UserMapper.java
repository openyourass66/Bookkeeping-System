package com.zhang.Mapper;

import com.github.pagehelper.Page;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.DTO.UserQueryDTO;
import com.zhang.Pojo.Entity.User;
import com.zhang.Pojo.VO.UserVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {

    /**
     * 添加用户
     * @param u
     */
    @Insert("insert into user(username,password,name,gender,age,phone,self_description,create_time,update_time) " +
    "values(#{username},#{password},#{name},#{gender},#{age},#{phone},#{selfDescription},#{createTime},#{updateTime})")
    void insert(User u);
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User selectById(Long id);

    /**
     * 修改用户
     * @param u
     */
    void update(User u);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void deleteById(Long id);

    /**
     * 分页查询
     * @param userQueryDTO
     * @return
     */
    Page<UserVO> page(UserQueryDTO userQueryDTO);
}
