package com.zhang.Mapper;

import com.github.pagehelper.Page;
import com.zhang.Pojo.DTO.EmpQueryDTO;
import com.zhang.Pojo.DTO.LoginDTO;
import com.zhang.Pojo.Entity.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface EmpMapper {
    /**
     *  登录
     * @param username
     * @return
     */
    @Select("select * from emp where username=#{username}")
    Emp selectByUsername(String username);

    /**
     * 分页查询
     * @param empQueryDTO
     * @return
     */
    Page<Emp> page(EmpQueryDTO empQueryDTO);

    /**
     * 新增职工
     * @param emp
     */
    @Insert("insert into emp(username,name,password,gender,phone,status,entry_date,create_time,update_time) " +
    "values(#{username},#{name},#{password},#{gender},#{phone},#{status},#{entryDate},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 修改职工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 删除职工
     * @param
     */
    void deleteById(Long id);


}
