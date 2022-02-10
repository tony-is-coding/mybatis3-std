package com.mybatis.testing.dto;

/**
 * com.mybatis.testing.dto - AccountQueryDTO
 *
 * @author tony-is-coding
 * @date 2022/2/9 15:01
 */

public class UserQueryDTO {

    private final String userName;
    private final String addr;

    public UserQueryDTO(String userName, String addr) {
        this.userName = userName;
        this.addr = addr;
    }


    public String getUserName() {
        return userName;
    }


    public String getAddr() {
        return addr;
    }


}
