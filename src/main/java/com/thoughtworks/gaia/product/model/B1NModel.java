package com.thoughtworks.gaia.product.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "B1N")
public class B1NModel extends IdBaseModel {
    @Column(name = "a1nid", nullable = false, length = 64)
    private Long a1nid;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public Long getA1nid() {
        return a1nid;
    }

    public void setA1nid(Long a1nid) {
        this.a1nid = a1nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
