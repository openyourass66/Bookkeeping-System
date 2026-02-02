package com.zhang.Pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ConsumptionQueryDTO {
    private Integer page=1;
    private Integer pageSize=10;
    private Long userId;
    private String event;
    private Double minAmount;
    private Double maxAmount;
    private LocalDate beginDate;
    private LocalDate endDate;
}
