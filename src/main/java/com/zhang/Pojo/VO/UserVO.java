package com.zhang.Pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    Long id;
    String username;
    String name;
    Integer gender;
    Integer age;
    String phone;
    String selfDescription;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}

