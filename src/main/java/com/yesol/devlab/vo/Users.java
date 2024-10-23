package com.yesol.devlab.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString
@Table(name="T_USER_INFO")
public class Users {
    @Id
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
}
