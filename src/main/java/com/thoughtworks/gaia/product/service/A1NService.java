package com.thoughtworks.gaia.product.service;

import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import com.thoughtworks.gaia.product.A1NMap;
import com.thoughtworks.gaia.product.B1NMap;
import com.thoughtworks.gaia.product.dao.A1NDao;
import com.thoughtworks.gaia.product.dao.B1NDao;
import com.thoughtworks.gaia.product.entity.A1N;
import com.thoughtworks.gaia.product.entity.B1N;
import com.thoughtworks.gaia.product.model.A1NModel;
import com.thoughtworks.gaia.product.model.B1NModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class A1NService implements Loggable {
    @Autowired
    private A1NMap a1NMap;
    @Autowired
    private B1NMap b1NMap;
    @Autowired
    private A1NDao a1NDao;
    @Autowired
    private B1NDao b1NDao;

    public List getAllA1Ns() {
        List<A1NModel> a1NModels = a1NDao.where().queryList();
        if (a1NModels == null) {
            error("User not found");
            throw new NotFoundException();
        }
        List<A1N> a1ns = a1NMap.mapList(a1NModels, A1N.class);
        for (A1N a1N : a1ns) {
            List<B1NModel> b1NModels = b1NDao.where().queryList();
            List<B1N> b1ns = b1NMap.mapList(b1NModels, B1N.class);
            List<B1N> b1NList = new ArrayList<>();
            for (B1N b1N : b1ns) {
                if (b1N.getA1nid().equals(a1N.getId())) {
                    b1NList.add(b1N);
                }
            }
            a1N.setB1Ns(b1ns);
        }
        return a1ns;
    }

    public A1N getA1NById(long id) {
        A1NModel a1NModel = a1NDao.idEquals(id).querySingle();
        if (a1NModel == null) {
            error("User not found");
            throw new NotFoundException();
        }
        A1N a1N = a1NMap.map(a1NModel, A1N.class);

        List<B1NModel> b1NModels = b1NDao.where().queryList();
        List<B1N> b1ns = b1NMap.mapList(b1NModels, B1N.class);
        List<B1N> b1NList = new ArrayList<>();
        for (B1N b1N : b1ns) {
            if (b1N.getA1nid().equals(a1N.getId())) {
                b1NList.add(b1N);
            }
        }
        a1N.setB1Ns(b1ns);
        return a1N;
    }

    public String addA1N(A1N a1N) {
        A1NModel a1NModel = a1NMap.map(a1N, A1NModel.class);
        List<B1N> b1NList = a1N.getB1Ns();
        try {
            a1NDao.save(a1NModel);
            List<B1NModel> b1NModels=b1NMap.mapList(b1NList,B1NModel.class);
            for (B1NModel b1NModel:b1NModels) {
                b1NModel.setA1nid(a1NModel.getId());
                b1NDao.save(b1NModel);
            }
            return "/gaia/rest/a1ns";
        } catch (Exception e) {

            return null;
        }

    }
}
