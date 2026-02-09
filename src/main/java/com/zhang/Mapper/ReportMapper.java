package com.zhang.Mapper;

import com.zhang.Pojo.DTO.ConsumptionReportDTO;
import com.zhang.Pojo.DTO.UserReportDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    /**
     * 年龄统计
     * @return
     */
    List<Map<String, Integer>> ageReport();

    /**
     * 性别统计
     * @return
     */
    List<Map<String, Integer>> genderReport();

    /**
     * 消费事件统计
     * @param consumptionReportDTO
     * @return
     */
    Integer CountConumptionReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 消费事件类型统计
     * @param consumptionReportDTO
     * @return
     */
    Integer CountConumptionTypeReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 总消费统计
     * @param consumptionReportDTO
     * @return
     */
    Double totalConsumtionReport(ConsumptionReportDTO consumptionReportDTO);
    /**
     * 新增用户统计
     * @param userReportDTO
     * @return
     */
    Integer newUserReport(UserReportDTO userReportDTO);



}
