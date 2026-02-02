package com.zhang.Controller;

import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Service.ConsumptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/consumption")
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;
    /**
     * 保存记录
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody  Consumption  consumption){
        log.info("保存记录:{}", consumption);
        consumptionService.save(consumption);
        return Result.success();
    }
    /**
     * 分页查询
     * @return
     */
    @GetMapping("/page")
    public Result list(ConsumptionQueryDTO consumptionQueryDTO){
        log.info("分页查询消费记录");
        PageResult<Consumption> list =  consumptionService.page(consumptionQueryDTO);
        return Result.success(list);
    }
}
