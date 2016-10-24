package com.thoughtworks.gaia.a1n.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "a1n")
public class A1NModel  extends IdBaseModel {
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
