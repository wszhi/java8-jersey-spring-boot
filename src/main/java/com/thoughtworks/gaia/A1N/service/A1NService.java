package com.thoughtworks.gaia.a1n.service;

import com.thoughtworks.gaia.a1n.A1NMap;
import com.thoughtworks.gaia.a1n.B1NMap;
import com.thoughtworks.gaia.a1n.dao.A1NDao;
import com.thoughtworks.gaia.a1n.dao.B1NDao;
import com.thoughtworks.gaia.a1n.entity.A1N;
import com.thoughtworks.gaia.a1n.entity.B1N;
import com.thoughtworks.gaia.a1n.model.A1NModel;
import com.thoughtworks.gaia.a1n.model.B1NModel;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
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
            error("A1N not found");
            throw new NotFoundException();
        }
        List<A1N> a1ns = a1NMap.mapList(a1NModels, A1N.class);
        for (A1N a1N : a1ns) {
            setB1NListIntoA1N(a1N);
        }
        return a1ns;
    }

    public A1N getA1NById(Long id) {
        A1NModel a1NModel = a1NDao.idEquals(id).querySingle();
        if (a1NModel == null) {
            error("A1N not found");
            throw new NotFoundException();
        }
        A1N a1N = a1NMap.map(a1NModel, A1N.class);

        setB1NListIntoA1N(a1N);
        return a1N;
    }

    private void setB1NListIntoA1N(A1N a1N) {
        List<B1NModel> b1NModels = b1NDao.where().queryList();
        List<B1N> b1ns = b1NMap.mapList(b1NModels, B1N.class);
        List<B1N> b1NList = new ArrayList<>();
        for (B1N b1N : b1ns) {
            if (b1N.getA1nid().equals(a1N.getId())) {
                b1NList.add(b1N);
            }
        }
        a1N.setB1Ns(b1NList);
    }

    public String addA1N(A1N a1N) {
        A1NModel a1NModel = a1NMap.map(a1N, A1NModel.class);
        if (a1NModel == null || a1NModel.getName() == null) {
            error("A1N not found");
            throw new NotFoundException();
        }
        a1NDao.save(a1NModel);
        if (a1N.getB1Ns() != null) {
            List<B1NModel> b1NModels = b1NMap.mapList(a1N.getB1Ns(), B1NModel.class);
            for (B1NModel b1NModel : b1NModels) {
                b1NModel.setA1nid(a1NModel.getId());
                b1NDao.save(b1NModel);
            }
        }
        return "/gaia/rest/a1ns";


    }

    public String updateA1N(A1N a1N) {
        A1NModel a1NModel = a1NMap.map(a1N, A1NModel.class);
        if (a1NModel == null || a1N.getId() == null) {
            error("A1N not found");
            throw new NotFoundException();
        }
        if (a1NModel.getName() != null) {
            a1NDao.update(a1NModel);
        }
        if (a1N.getB1Ns() != null) {
            List<B1NModel> b1NModels = b1NMap.mapList(a1N.getB1Ns(), B1NModel.class);
            for (B1NModel b1NModel : b1NModels) {
                b1NModel.setA1nid(a1N.getId());
                b1NDao.update(b1NModel);
            }
        }
        return "/gaia/rest/a1ns/" + a1NModel.getId();

    }

    public String deleteA1N(Long id) {

        A1NModel a1NModel = a1NDao.idEquals(id).querySingle();
        if (a1NModel == null) {
            error("A1N not found");
            throw new NotFoundException();
        }
        a1NDao.remove(a1NModel);
        return "/gaia/rest/a1ns";

    }
}
