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
    private String event;
    private Double amount;
    private String detail;
    private LocalDateTime createTime;

    private String name;
}
