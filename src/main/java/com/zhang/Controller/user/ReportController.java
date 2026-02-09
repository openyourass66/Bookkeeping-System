package com.zhang.Controller.user;

import com.zhang.Pojo.DTO.ConsumptionReportDTO;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Pojo.VO.CountConsumptionReportVO;
import com.zhang.Pojo.VO.CountConsumptionTypeReportVO;
import com.zhang.Pojo.VO.TotalConsumptionReportVO;
import com.zhang.Service.ReportService;
import com.zhang.Utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserReportController")
@RequestMapping("/user/report")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 消费事件统计
     * @param consumptionReportDTO
     * @return
     */
    @GetMapping("/CountConumptionReport")
    public Result CountConumptionReport(@RequestBody ConsumptionReportDTO consumptionReportDTO){
        log.info("消费事件统计");
        CountConsumptionReportVO countConsumptionReportVO = reportService.CountConumptionReport(consumptionReportDTO);
        return Result.success(countConsumptionReportVO);
    }

    /**
     * 消费事件类型统计
     * @param consumptionReportDTO
     * @return
     */
    @GetMapping("/CountConumptionTypeReport")
    public Result CountConumptionTypeReport(@RequestBody ConsumptionReportDTO consumptionReportDTO){
        log.info("消费事件类型统计");
        CountConsumptionTypeReportVO countConsumptionTypeReportVO = reportService.CountConumptionTypeReport(consumptionReportDTO);
        return Result.success(countConsumptionTypeReportVO);
    }
    /**
     * 消费统计
     * @param consumptionReportDTO
     * @return
     */
    @GetMapping("/totalConsumtionReport")
    public Result totalConsumtionReport(@RequestBody ConsumptionReportDTO consumptionReportDTO){
        log.info("消费统计");
        consumptionReportDTO.setUserId(CurrentHolder.getCurrentId());
        TotalConsumptionReportVO totalConsumptionReportVO = reportService.totalConsumtionReport(consumptionReportDTO);
        return Result.success(totalConsumptionReportVO);
    }

    /**
     * 平均消费统计
     * @param consumptionReportDTO
     * @return
     */
    @GetMapping("/averageConsumtionReport")
    public Result averageConsumtionReport(@RequestBody ConsumptionReportDTO consumptionReportDTO){
        log.info("平均消费统计");
        consumptionReportDTO.setUserId(CurrentHolder.getCurrentId());
        Double averageConsumtionReport = reportService.averageConsumtionReport(consumptionReportDTO);
        return Result.success(averageConsumtionReport);
    }
}
