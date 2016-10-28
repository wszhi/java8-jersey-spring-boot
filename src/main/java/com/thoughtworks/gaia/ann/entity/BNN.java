package com.thoughtworks.gaia.ann.entity;

import java.util.List;

public class BNN {
    private Long id;
    private String name;
    private List<ANN> ann;

    public List<ANN> getAnn() {
        return ann;
    }

    public void setAnn(List<ANN> ann) {
        this.ann = ann;
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
