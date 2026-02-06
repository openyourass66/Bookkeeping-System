package com.zhang.Controller.admin;

import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Service.ConsumptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("EmpConsumptionController")
@RequestMapping("/admin/consumption")
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;
    /**
     * 分页查询
     * @return
     */
    @GetMapping("/page")
    public Result page(ConsumptionQueryDTO consumptionQueryDTO){
        log.info("分页查询消费记录");
        PageResult<Consumption> list =  consumptionService.page(consumptionQueryDTO);
        return Result.success(list);
    }
    /**
     * 批量删除消费记录
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam List<Long> ids){
        log.info("根据ids批量删除消费记录:{}", ids);
        consumptionService.deleteByIds(ids);
        return Result.success();
    }
}
