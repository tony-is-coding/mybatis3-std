package com.mybatis.testing.mapper;


import com.mybatis.testing.entity.Account;

public interface AccountMapper {
    Account findByAccountId(Integer accountId);

    Account findByUserId(Integer userId);

    Account findByUserId();

    Integer insert(Account account);
}
