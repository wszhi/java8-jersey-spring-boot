package com.thoughtworks.gaia.product.model;


import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserModel  extends IdBaseModel {
    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Column(name = "age", nullable = false, updatable = false)
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
