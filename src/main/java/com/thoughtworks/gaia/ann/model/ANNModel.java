package com.thoughtworks.gaia.ann.model;

import com.thoughtworks.gaia.common.jpa.IdBaseModel;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "ANN")
public class ANNModel extends IdBaseModel {

    @ManyToMany(targetEntity = BNNModel.class)
    private List<BNNModel> bnn;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    public List<BNNModel> getBnn() {
        return bnn;
    }

    public void setBnn(List<BNNModel> bnn) {
        this.bnn = bnn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
