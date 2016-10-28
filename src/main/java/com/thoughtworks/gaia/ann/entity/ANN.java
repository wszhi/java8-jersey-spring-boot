package com.thoughtworks.gaia.ann.entity;

import java.util.List;


public class ANN {
    private Long id;
    private String name;
    private List<BNN> bnn;

    public List<BNN> getBnn() {
        return bnn;
    }

    public void setBnn(List<BNN> bnn) {
        this.bnn = bnn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
