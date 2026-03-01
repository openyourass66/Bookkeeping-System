package com.zhang.Service.Impl;

import com.zhang.Mapper.ReportMapper;
import com.zhang.Pojo.DTO.ConsumptionReportDTO;
import com.zhang.Pojo.DTO.UserReportDTO;
import com.zhang.Pojo.VO.CountConsumptionReportVO;
import com.zhang.Pojo.VO.CountConsumptionTypeReportVO;
import com.zhang.Pojo.VO.TotalConsumptionReportVO;
import com.zhang.Pojo.VO.UserReportVO;
import com.zhang.Service.ReportService;
import com.zhang.Utils.DateUtil;
import com.zhang.Utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 年龄统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> ageReport() {
        // 生成缓存键
        String cacheKey = "report:age";
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), List.class);
        }
        
        // 从数据库查询
        List<Map<String, Integer>> result = reportMapper.ageReport();
        
        // 存入缓存，设置过期时间为1小时
        redisUtil.set(cacheKey, JSON.toJSONString(result), 3600);
        
        return result;
    }
    /**
     * 性别统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> genderReport() {
        // 生成缓存键
        String cacheKey = "report:gender";
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), List.class);
        }
        
        // 从数据库查询
        List<Map<String, Integer>> result = reportMapper.genderReport();
        
        // 存入缓存，设置过期时间为1小时
        redisUtil.set(cacheKey, JSON.toJSONString(result), 3600);
        
        return result;
    }
    /**
     * 消费事件统计
     * @param consumptionReportDTO
     * @return
     */
    @Override
    public CountConsumptionReportVO CountConumptionReport(ConsumptionReportDTO consumptionReportDTO) {
        // 生成缓存键
        String cacheKey = "report:consumption:count:" + JSON.toJSONString(consumptionReportDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), CountConsumptionReportVO.class);
        }
        
        // 从数据库查询
        LocalDate beginDate = consumptionReportDTO.getBeginDate();
        LocalDate endDate = consumptionReportDTO.getEndDate();
        List<LocalDate> dateList = new ArrayList<>();
        while (!beginDate.isAfter(endDate)){
            dateList.add(beginDate);
            beginDate = beginDate.plusDays(1);
        }
        List<Integer> countList= new ArrayList<>();
        for (LocalDate date : dateList) {
            consumptionReportDTO.setBeginDateTime(DateUtil.toStartOfDay(date));
            consumptionReportDTO.setEndDateTime(DateUtil.toEndOfDay(date));
            Integer count= reportMapper.CountConumptionReport(consumptionReportDTO);
            countList.add(count);
        }
        CountConsumptionReportVO result = new CountConsumptionReportVO(dateList,countList);
        
        // 存入缓存，设置过期时间为30分钟
        redisUtil.set(cacheKey, JSON.toJSONString(result), 1800);
        
        return result;
    }
    /**
     * 消费事件类型统计
     * @return
     */
    @Override
    public CountConsumptionTypeReportVO CountConumptionTypeReport(ConsumptionReportDTO consumptionReportDTO) {
        // 生成缓存键
        String cacheKey = "report:consumption:type:" + JSON.toJSONString(consumptionReportDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), CountConsumptionTypeReportVO.class);
        }
        
        // 从数据库查询
        LocalDate beginDate = consumptionReportDTO.getBeginDate();
        LocalDate endDate = consumptionReportDTO.getEndDate();
        consumptionReportDTO.setBeginDateTime(DateUtil.toStartOfDay(beginDate));
        consumptionReportDTO.setEndDateTime(DateUtil.toEndOfDay(endDate));
        CountConsumptionTypeReportVO countConsumptionTypeReportVO = new CountConsumptionTypeReportVO();
        List<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            consumptionReportDTO.setEvent(i);
            Integer count = reportMapper.CountConumptionTypeReport(consumptionReportDTO);
            countList.add(count);
        }
        countConsumptionTypeReportVO.setCountList(countList);
        
        // 存入缓存，设置过期时间为30分钟
        redisUtil.set(cacheKey, JSON.toJSONString(countConsumptionTypeReportVO), 1800);
        
        return countConsumptionTypeReportVO;
    }
    /**
     * 总消费统计
     * @param consumptionReportDTO
     * @return
     */
    @Override
    public TotalConsumptionReportVO totalConsumtionReport(ConsumptionReportDTO consumptionReportDTO){
        // 生成缓存键
        String cacheKey = "report:consumption:total:" + JSON.toJSONString(consumptionReportDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), TotalConsumptionReportVO.class);
        }
        
        // 从数据库查询
        LocalDate beginDate = consumptionReportDTO.getBeginDate();
        LocalDate endDate = consumptionReportDTO.getEndDate();
        List<LocalDate> dateList = new ArrayList<>();
        while(!beginDate.isAfter(endDate)){
            dateList.add(beginDate);
            beginDate = beginDate.plusDays(1);
        }
        List<Double> countList = new ArrayList<>();
        for (LocalDate date : dateList) {
            consumptionReportDTO.setBeginDateTime(DateUtil.toStartOfDay(date));
            consumptionReportDTO.setEndDateTime(DateUtil.toEndOfDay(date));
            Double count = reportMapper.totalConsumtionReport(consumptionReportDTO);
            countList.add(count != null ? count : 0.0);
        }
        TotalConsumptionReportVO result = new TotalConsumptionReportVO(dateList,countList);
        
        // 存入缓存，设置过期时间为30分钟
        redisUtil.set(cacheKey, JSON.toJSONString(result), 1800);
        
        return result;
    }

    /**
     * 平均消费统计
     * @param consumptionReportDTO
     * @return
     */
    @Override
    public  Double averageConsumtionReport(ConsumptionReportDTO consumptionReportDTO){
        // 生成缓存键
        String cacheKey = "report:consumption:average:" + JSON.toJSONString(consumptionReportDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return Double.parseDouble(cachedData.toString());
        }
        
        // 从数据库查询
        LocalDate beginDate = consumptionReportDTO.getBeginDate();
        LocalDate endDate = consumptionReportDTO.getEndDate();
        //获取天数
        Long days = ChronoUnit.DAYS.between(beginDate, endDate)+1;
        consumptionReportDTO.setBeginDateTime(DateUtil.toStartOfDay(beginDate));
        consumptionReportDTO.setEndDateTime(DateUtil.toEndOfDay(endDate));
        Double total=reportMapper.totalConsumtionReport(consumptionReportDTO);
        Double result = 0.0;
        if (total != null) {
            result = total/days;
        }
        
        // 存入缓存，设置过期时间为30分钟
        redisUtil.set(cacheKey, result.toString(), 1800);
        
        return result;

    }
    /**
     * 新增用户统计
     * @param userReportDTO
     * @return
     */
    @Override
    public UserReportVO newUserReport(UserReportDTO userReportDTO) {
        // 生成缓存键
        String cacheKey = "report:user:new:" + JSON.toJSONString(userReportDTO);
        
        // 先从缓存中获取
        Object cachedData = redisUtil.get(cacheKey);
        if (cachedData != null) {
            return JSON.parseObject(cachedData.toString(), UserReportVO.class);
        }
        
        // 从数据库查询
        LocalDate beginDate = userReportDTO.getBeginDate();
        LocalDate endDate = userReportDTO.getEndDate();
        List<LocalDate> dateList = new ArrayList<>();
        while (!beginDate.isAfter(endDate)){
            dateList.add(beginDate);
            beginDate = beginDate.plusDays(1);
        }
        List<Integer> countList= new ArrayList<>();
        for (LocalDate date : dateList) {
            userReportDTO.setBeginDateTime(DateUtil.toStartOfDay(date));
            userReportDTO.setEndDateTime(DateUtil.toEndOfDay(date));
            Integer count= reportMapper.newUserReport(userReportDTO);
            countList.add(count);
        }
        UserReportVO result = new UserReportVO(dateList,countList);
        
        // 存入缓存，设置过期时间为30分钟
        redisUtil.set(cacheKey, JSON.toJSONString(result), 1800);
        
        return result;
    }
}
