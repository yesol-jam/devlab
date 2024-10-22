package com.yesol.devlab.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Users {
    private String user_id;
    private String password;
    private String name;
    private String email;
}
