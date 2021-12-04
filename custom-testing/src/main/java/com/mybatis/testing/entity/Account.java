package com.mybatis.testing.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Account {
    Integer account_id;
    Integer user_id;
    BigDecimal amount;
    LocalDateTime add_dt;
    LocalDateTime update_dt;
}
