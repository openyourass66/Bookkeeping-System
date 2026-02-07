package com.zhang.Pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionVO {
    private Long id;
    private Long userId;
    private Integer event;
    private Double amount;
    private String detail;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String name;


}
