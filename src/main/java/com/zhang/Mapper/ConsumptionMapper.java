package com.zhang.Mapper;

import com.github.pagehelper.Page;
import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConsumptionMapper {
    /**
     *  添加消费记录
     * @param consumption
     */
    @Insert("insert into consumption(user_id,event,amount,detail,create_time,update_time) " +
    "values(#{userId},#{event},#{amount},#{detail},#{createTime},#{updateTime})")
    void insert(Consumption consumption);
    /**

    /**
     *  分页查询
     * @param consumptionQueryDTO
     * @return
     */
    //TODO 时间
    Page<Consumption> page(ConsumptionQueryDTO consumptionQueryDTO);

    /**
     *  通过id查询
     * @param userId
     * @return
     */
    PageResult<Consumption> selectByUserId(Long userId);

    /**
     *  批量删除
     * @param ids
     */
    void deleteByIds(List ids);

    /**
     *  修改
     * @param consumption
     */
    void update(Consumption consumption);

    /**
     *  通过用户id查询数量
     * @param userId
     * @return
     */
    @Select("select count(*) from consumption where user_id=#{userId}")
    Integer countByUserId(Long userId);
}
