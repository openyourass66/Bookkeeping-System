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
    /**
     * 年龄统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> ageReport() {
        return reportMapper.ageReport();
    }
    /**
     * 性别统计
     * @return
     */
    @Override
    public List<Map<String, Integer>> genderReport() {
        return reportMapper.genderReport();
    }
    /**
     * 消费事件统计
     * @param consumptionReportDTO
     * @return
     */
    @Override
    public CountConsumptionReportVO CountConumptionReport(ConsumptionReportDTO consumptionReportDTO) {
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
        return new CountConsumptionReportVO(dateList,countList);
    }
    /**
     * 消费事件类型统计
     * @return
     */
    @Override
    public CountConsumptionTypeReportVO CountConumptionTypeReport(ConsumptionReportDTO consumptionReportDTO) {
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
        return countConsumptionTypeReportVO;
    }
    /**
     * 总消费统计
     * @param consumptionReportDTO
     * @return
     */
    @Override
    public TotalConsumptionReportVO totalConsumtionReport(ConsumptionReportDTO consumptionReportDTO){
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
            countList.add(count);
        }
       return new TotalConsumptionReportVO(dateList,countList);
    }

    /**
     * 平均消费统计
     * @param consumptionReportDTO
     * @return
     */
    @Override
    public  Double averageConsumtionReport(ConsumptionReportDTO consumptionReportDTO){
        LocalDate beginDate = consumptionReportDTO.getBeginDate();
        LocalDate endDate = consumptionReportDTO.getEndDate();
        //获取天数
        Long days = ChronoUnit.DAYS.between(beginDate, endDate)+1;
        consumptionReportDTO.setBeginDateTime(DateUtil.toStartOfDay(beginDate));
        consumptionReportDTO.setEndDateTime(DateUtil.toEndOfDay(endDate));
        Double total=reportMapper.totalConsumtionReport(consumptionReportDTO);
        return total/days;

    }
    /**
     * 新增用户统计
     * @param userReportDTO
     * @return
     */
    @Override
    public UserReportVO newUserReport(UserReportDTO userReportDTO) {
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
        return new UserReportVO(dateList,countList);
    }
}
