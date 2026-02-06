package com.zhang.Pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryDTO {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    private Integer status;
    private LocalDate beginDate;
    private LocalDate endDate;
}
