package com.zhang.Controller.admin;

import com.zhang.Pojo.Entity.Result;
import com.zhang.Service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/report")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/ageReport")
    public Result ageReport(){
        log.info("年龄统计");
        List<Map<String,Integer>> list = reportService.ageReport();
        return Result.success(list);
    }
    @GetMapping("/genderReport")
    public Result genderReport(){
        log.info("性别统计");
        List<Map<String,Integer>> list = reportService.genderReport();
        return Result.success(list);
    }
    @GetMapping("/eventReport")
    public Result eventReport(){
        log.info("消费事件统计");
        List<Map<String,Integer>> list = reportService.eventReport();
        return Result.success(list);
    }
}
