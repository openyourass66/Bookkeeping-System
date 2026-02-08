package com.zhang.Service;

import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;

import java.util.List;


public interface ConsumptionService {
    /**
     * 保存消费记录
     * @param consumption
     */
    void save(Consumption consumption);

    /**
     * 分页查询
     * @param consumptionQueryDTO
     * @return
     */
    PageResult<Consumption> page(ConsumptionQueryDTO consumptionQueryDTO);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 修改记录
     * @param consumption
     */
    void update(Consumption consumption);

}
