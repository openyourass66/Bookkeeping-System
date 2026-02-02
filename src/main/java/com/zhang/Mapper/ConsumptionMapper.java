package com.zhang.Mapper;

import com.github.pagehelper.Page;
import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsumptionMapper {
    /**
     *  添加消费记录
     * @param consumption
     */
    void insert(Consumption consumption);
    /**
     *  查询所有消费记录
     * @return
     */
    List<Consumption> test();

    /**
     *  分页查询
     * @param consumptionQueryDTO
     * @return
     */
    Page<Consumption> page(ConsumptionQueryDTO consumptionQueryDTO);
}
