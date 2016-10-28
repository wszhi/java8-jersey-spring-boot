package com.thoughtworks.gaia.ann.service;

import com.thoughtworks.gaia.ann.ANNMap;
import com.thoughtworks.gaia.ann.dao.ANNDao;
import com.thoughtworks.gaia.ann.dao.BNNDao;
import com.thoughtworks.gaia.ann.entity.ANN;
import com.thoughtworks.gaia.ann.model.ANNModel;
import com.thoughtworks.gaia.ann.model.BNNModel;
import com.thoughtworks.gaia.common.Loggable;
import com.thoughtworks.gaia.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@Transactional
public class ANNService implements Loggable {
    @Autowired
    private ANNMap annMap;
    @Autowired
    private ANNDao annDao;
    @Autowired
    private BNNDao bnnDao;


    public List getAllANNs() {
        List<ANNModel> aNNModels = annDao.where().queryList();
        if (aNNModels == null) {
            error("ANN not found");
            throw new NotFoundException();
        }
        List<ANN> a1ns = annMap.mapList(aNNModels, ANN.class);
        return a1ns;
    }

    public ANN getANNById(Long id) {
        ANNModel annModel = annDao.idEquals(id).querySingle();
        if (annModel == null) {
            error("ANN not found");
            throw new NotFoundException();
        }
        return annMap.map(annModel, ANN.class);
    }

    public String addANN(ANN ann) {
        ANNModel annModel = annMap.map(ann, ANNModel.class);
        if (annModel == null) {
            error("ANN not found");
            throw new NotFoundException();
        }
        saveBNNList(annModel.getBnn());

        annDao.save(annModel);
        return "/gaia/rest/anns";
    }

    public String updateANN(ANN ann) {
        ANNModel annModel = annMap.map(ann, ANNModel.class);
        if (annModel == null) {
            error("ANN not found");
            throw new NotFoundException();
        }
        saveBNNList(annModel.getBnn());
        annDao.update(annModel);
        return "/gaia/rest/anns/"+annModel.getId();
    }

    private void saveBNNList(List<BNNModel> bnnModelList) {
        if (bnnModelList != null) {
            for (BNNModel bnnModel : bnnModelList) {
                if (bnnModel.getId() == null) {
                    bnnDao.save(bnnModel);
                }
            }
        }
    }

    public String deleteANN(Long id) {
        ANNModel annModel = annDao.idEquals(id).querySingle();
        if (annModel == null) {
            error("ANN not found");
            throw new NotFoundException();
        }
        annDao.remove(annModel);
        return "/gaia/rest/anns";
    }
}
