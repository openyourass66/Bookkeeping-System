package com.zhang.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhang.Mapper.ConsumptionMapper;
import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Service.ConsumptionService;
import com.zhang.Utils.CurrentHolder;
import com.zhang.Utils.DateUtil;
import com.zhang.Utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
    @Autowired
    private ConsumptionMapper consumptionMapper;
    @Autowired
    private RedisUtil redisUtil;

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
        // 清除缓存
        clearConsumptionCache();
    }
    /**
     * 分页查询
     * @param consumptionQueryDTO
     * @return
     */
    @Override
    public PageResult<Consumption> page(ConsumptionQueryDTO  consumptionQueryDTO){
        // 生成缓存键
        String cacheKey = "consumption:page:" + JSON.toJSONString(consumptionQueryDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), PageResult.class);
        }
        
        // 从数据库查询
        consumptionQueryDTO.setBeginDateTime(DateUtil.toStartOfDay(consumptionQueryDTO.getBeginDate()));
        consumptionQueryDTO.setEndDateTime(DateUtil.toEndOfDay(consumptionQueryDTO.getEndDate()));
        PageHelper.startPage(consumptionQueryDTO.getPage(),consumptionQueryDTO.getPageSize());
        Page<Consumption> page =  consumptionMapper.page(consumptionQueryDTO);
        PageResult<Consumption> result = new PageResult<>(page.getTotal(),page.getResult());
        
        // 存入缓存，设置过期时间为5分钟
        redisUtil.set(cacheKey, JSON.toJSONString(result), 300);
        
        return result;
    }
    /**
     * 批量删除
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<Long> ids){
        consumptionMapper.deleteByIds(ids);
        // 清除缓存
        clearConsumptionCache();
    }

    /**
     * 修改
     * @param consumption
     */
    @Override
    public void update(Consumption consumption){
        consumption.setUpdateTime(LocalDateTime.now());
        consumptionMapper.update(consumption);
        // 清除缓存
        clearConsumptionCache();
    }
    
    /**
     * 清除消费记录缓存
     */
    private void clearConsumptionCache() {
        // 清除所有消费记录分页缓存
        redisUtil.deleteByPattern("consumption:page:*");
    }

}
