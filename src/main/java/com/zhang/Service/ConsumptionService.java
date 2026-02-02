package com.zhang.Service;

import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;


public interface ConsumptionService {
    /**
     * 保存记录
     * @param consumption
     */
    void save(Consumption consumption);

    /**
     * 分页查询
     * @param consumptionQueryDTO
     * @return
     */
    PageResult<Consumption> page(ConsumptionQueryDTO consumptionQueryDTO);
}
