package com.thoughtworks.gaia.product.entity;

import java.util.List;

/**
 * Created by szwang on 10/21/16.
 */
public class A1N {
    private Long id;
    private String name;
    private List<B1N> b1Ns;

    public List<B1N> getB1Ns() {
        return b1Ns;
    }

    public void setB1Ns(List<B1N> b1Ns) {
        this.b1Ns = b1Ns;
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
