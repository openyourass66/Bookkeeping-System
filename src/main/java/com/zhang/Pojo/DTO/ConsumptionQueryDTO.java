package com.zhang.Pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ConsumptionQueryDTO {
    private Integer page=1;
    private Integer pageSize=10;
    private String event;
    private String username;
    private Double minAmount;
    private Double maxAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Long userId;

    private LocalDateTime beginDateTime;
    private LocalDateTime endDateTime;


}
