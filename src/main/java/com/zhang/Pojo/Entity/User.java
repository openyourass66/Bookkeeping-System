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
    int gender;
    String phone;
    String self_description;
    LocalDateTime create_time;
    LocalDateTime update_time;
}
