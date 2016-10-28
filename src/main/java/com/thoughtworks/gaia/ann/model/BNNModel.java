package com.thoughtworks.gaia.ann.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "BNN")
public class BNNModel extends IdBaseModel {

    @ManyToMany(targetEntity = ANNModel.class, mappedBy = "bnn")
    private List<ANNModel> ann;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public List<ANNModel> getAnn() {
        return ann;
    }

    public void setAnn(List<ANNModel> ann) {
        this.ann = ann;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
