package com.zhang.Pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpVO {
    private Long id;
    private String username;
    private String name;
    private Integer gender;
    private Integer age;
    private String phone;
    private Integer status;
    private LocalDate entryDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
