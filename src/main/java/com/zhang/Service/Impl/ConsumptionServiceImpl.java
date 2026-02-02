package com.zhang.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhang.Mapper.ConsumptionMapper;
import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Service.ConsumptionService;
import com.zhang.Utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
    @Autowired
    private ConsumptionMapper consumptionMapper;
    /**
     * 保存记录
     * @param consumption
     */
    @Override
    public void save(Consumption consumption){
        consumption.setCreateTime(LocalDateTime.now());
        consumption.setUserId(CurrentHolder.getCurrentId());
        consumptionMapper.insert(consumption);
    }
    /**
     * 分页查询
     * @param consumptionQueryDTO
     * @return
     */
    //分页条件查询
    @Override
    public PageResult<Consumption> page(ConsumptionQueryDTO  consumptionQueryDTO){
        //调用pagehelper
        PageHelper.startPage(consumptionQueryDTO.getPage(),consumptionQueryDTO.getPageSize());
        Page<Consumption> page =  consumptionMapper.page(consumptionQueryDTO);
        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
