package com.zhang.Pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private Integer age;
    private String phone;
    private  String selfDescription;
    private  LocalDateTime createTime;
    private  LocalDateTime updateTime;
}
