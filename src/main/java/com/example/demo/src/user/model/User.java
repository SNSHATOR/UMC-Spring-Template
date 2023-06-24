package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User {
//    private int userIdx;
//    private String ID;
//    private String userName;
//    private String password;
//    private String email;
    private int id;
    private String phone_number;
    private Timestamp created_at;
    private String nickname;
    private BigDecimal temperature;
    private int area_id;
    private String password;
}
