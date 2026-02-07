package com.zhang.Pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumption {
    private Long id;
    private Long userId;
    private Integer event; //1饮食 2购物 3交通 4医疗 5娱乐                                                                                                    4游戏 5运动
    private Double amount;
    private String detail;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
