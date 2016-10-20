package com.thoughtworks.gaia.product.entity;

/**
 * Created by szwang on 10/20/16.
 */
public class User {
    private Long id;
    private String username;
    private int age;

    public User(String username, int age) {

        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
