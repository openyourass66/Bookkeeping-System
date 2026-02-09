package com.zhang.Controller.admin;

import com.zhang.Pojo.DTO.ConsumptionReportDTO;
import com.zhang.Pojo.DTO.UserReportDTO;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Pojo.VO.CountConsumptionReportVO;
import com.zhang.Pojo.VO.CountConsumptionTypeReportVO;
import com.zhang.Pojo.VO.UserReportVO;
import com.zhang.Service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("AdminReportController")
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;
    /**
     * 年龄统计
     * @return
     */
    @GetMapping("/ageReport")
    public Result ageReport(){
        log.info("年龄统计");
        List<Map<String,Integer>> list = reportService.ageReport();
        return Result.success(list);
    }
    /**
     * 性别统计
     * @return
     */
    @GetMapping("/genderReport")
    public Result genderReport(){
        log.info("性别统计");
        List<Map<String,Integer>> list = reportService.genderReport();
        return Result.success(list);
    }
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
     * 新增用户统计
     * @param userReportDTO
     * @return
     */
    @GetMapping("newUserReport")
    public Result newUserReport(@RequestBody UserReportDTO userReportDTO){
        log.info("新增用户统计");
        UserReportVO userReportVO = reportService.newUserReport(userReportDTO);
        return Result.success(userReportVO);
    }
}
