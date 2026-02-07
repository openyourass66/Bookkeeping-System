package com.zhang.Controller.user;
import com.zhang.Pojo.DTO.ConsumptionQueryDTO;
import com.zhang.Pojo.Entity.Consumption;
import com.zhang.Pojo.Entity.PageResult;
import com.zhang.Pojo.Entity.Result;
import com.zhang.Service.ConsumptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("UserConsumptionController")
@RequestMapping("/user/consumption")
@Slf4j
public class ConsumptionController {
    @Autowired
    private ConsumptionService consumptionService;
    /**
     * 分页查询
     * @return
     */
    @GetMapping("/page")
    public Result page(ConsumptionQueryDTO consumptionQueryDTO){
        log.info("用户查询：{}",consumptionQueryDTO);
        PageResult<Consumption> list =  consumptionService.page(consumptionQueryDTO);
        return Result.success(list);
    }
    /**
     * 保存记录
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Consumption consumption){
        log.info("保存记录:{}", consumption);
        consumptionService.save(consumption);
        return Result.success();
    }
    /**
     * 批量删除消费记录
     * @return
     */
    @DeleteMapping("/delete")
    public Result deleteByIds(@RequestParam List<Long> ids){
        log.info("批量删除消费记录:{}", ids);
        consumptionService.deleteByIds(ids);
        return Result.success();
    }
}
