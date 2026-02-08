package com.zhang.Pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long id;
    String username;
    String password;
    String name;
    Integer gender;
    Integer age;
    String phone;
    String selfDescription;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}
