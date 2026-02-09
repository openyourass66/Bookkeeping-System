package com.zhang.Service;

import com.zhang.Pojo.DTO.ConsumptionReportDTO;
import com.zhang.Pojo.DTO.UserReportDTO;
import com.zhang.Pojo.VO.CountConsumptionReportVO;
import com.zhang.Pojo.VO.CountConsumptionTypeReportVO;
import com.zhang.Pojo.VO.TotalConsumptionReportVO;
import com.zhang.Pojo.VO.UserReportVO;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 年龄统计
     * @return
     */
    List<Map<String,Integer>> ageReport();

    /**
     * 性别统计
     * @return
     */
    List<Map<String, Integer>> genderReport();
    /**
     * 事件统计
     * @param consumptionReportDTO
     * @return
     */
    CountConsumptionReportVO CountConumptionReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 事件类型统计
     * @param consumptionReportDTO
     * @return
     */
    CountConsumptionTypeReportVO CountConumptionTypeReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 总消费统计
     * @param consumptionReportDTO
     * @return
     */
    TotalConsumptionReportVO totalConsumtionReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 平均消费统计
     * @param consumptionReportDTO
     * @return
     */
    Double averageConsumtionReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 新增用户统计
     * @param userReportDTO
     * @return
     */
    UserReportVO newUserReport(UserReportDTO userReportDTO);



}
