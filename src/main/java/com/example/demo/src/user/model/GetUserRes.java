package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class GetUserRes {
    private int id;
    private String phone_number;
    private Timestamp created_at;
    private String nickname;
    private BigDecimal temperature;
    private int area_id;
    private String password;
}
