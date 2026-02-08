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
import org.springframework.transaction.annotation.Transactional;

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
        consumption.setUserId(CurrentHolder.getCurrentId());
        consumption.setCreateTime(LocalDateTime.now());
        consumption.setUpdateTime(LocalDateTime.now());
        consumptionMapper.insert(consumption);
    }
    /**
     * 分页查询
     * @param consumptionQueryDTO
     * @return
     */
    @Override
    public PageResult<Consumption> page(ConsumptionQueryDTO  consumptionQueryDTO){
        LocalDateTime beginDateTime = consumptionQueryDTO.getBeginDate()==null?null:consumptionQueryDTO.getBeginDate().atStartOfDay();
        LocalDateTime endDateTime = consumptionQueryDTO.getEndDate()==null?null:consumptionQueryDTO.getEndDate().atTime(23, 59, 59, 999_999_999);
        consumptionQueryDTO.setBeginDateTime(beginDateTime);
        consumptionQueryDTO.setEndDateTime(endDateTime);
        PageHelper.startPage(consumptionQueryDTO.getPage(),consumptionQueryDTO.getPageSize());Page<Consumption> page =  consumptionMapper.page(consumptionQueryDTO);
        return new PageResult<>(page.getTotal(),page.getResult());
    }
    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional
    public void deleteByIds(List<Long> ids){
        consumptionMapper.deleteByIds(ids);
    }

    /**
     * 修改
     * @param consumption
     */
    @Override
    public void update(Consumption consumption){
        consumption.setUpdateTime(LocalDateTime.now());
        consumptionMapper.update(consumption);
    }

}
