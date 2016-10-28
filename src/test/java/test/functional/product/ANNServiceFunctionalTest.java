package test.functional.product;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.ann.dao.ANNDao;
import com.thoughtworks.gaia.ann.dao.BNNDao;
import com.thoughtworks.gaia.ann.entity.ANN;
import com.thoughtworks.gaia.ann.entity.BNN;
import com.thoughtworks.gaia.ann.model.ANNModel;
import com.thoughtworks.gaia.ann.model.BNNModel;
import com.thoughtworks.gaia.ann.service.ANNService;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class ANNServiceFunctionalTest {
    @Autowired
    private ANNDao annDao;

    @Autowired
    private ANNService annService;

    @Autowired
    private BNNDao bnnDao;


    @Test
    public void shouldGetANN() throws Exception {
        initData();

        List<ANN> anns = annService.getAllANNs();

        assertThat(anns.size()).isEqualTo(2);
        assertThat(anns.get(0).getName()).isEqualTo("name1");
        assertThat(anns.get(0).getBnn().size()).isEqualTo(1);
        assertThat(anns.get(0).getBnn().get(0).getName()).isEqualTo("bname1");

        assertThat(anns.get(1).getName()).isEqualTo("name2");
        assertThat(anns.get(1).getBnn().size()).isEqualTo(2);
        assertThat(anns.get(1).getBnn().get(0).getName()).isEqualTo("bname1");
        assertThat(anns.get(1).getBnn().get(1).getName()).isEqualTo("bname2");

    }

    @Test
    public void shouldGetCorrectANNById() throws Exception {

        List<ANNModel> annModels = initData();
        ANN ann = annService.getANNById(annModels.get(0).getId());
        assertThat(ann.getName()).isEqualTo("name1");
        assertThat(ann.getBnn().size()).isEqualTo(1);
        assertThat(ann.getBnn().get(0).getName()).isEqualTo("bname1");

    }

    @Test
    public void shouldReturnURIAndInsertIntoDatabaseWhenAddANNSuccess() throws Exception {

        BNNModel bnnModel = new BNNModel();
        bnnModel.setName("nameB1");
        bnnDao.save(bnnModel);

        BNN bnn1 = new BNN();
        bnn1.setId(bnnModel.getId());

        BNN bnn2 = new BNN();
        bnn2.setName("nameB2");
        ANN ann = new ANN();
        ann.setName("nameA");
        ann.setBnn(Arrays.asList(bnn1, bnn2));

        String url = annService.addANN(ann);

        assertEquals("/gaia/rest/anns", url);

        List<ANNModel> annModels = annDao.where().queryList();
        assertThat(annModels.size()).isEqualTo(1);
        assertThat(annModels.get(0).getBnn().size()).isEqualTo(2);
        assertThat(annModels.get(0).getBnn().get(0).getId()).isEqualTo(bnnModel.getId());
        assertThat(annModels.get(0).getBnn().get(1).getName()).isEqualTo("nameB2");


        List<BNNModel> bnnModels = bnnDao.where().queryList();
        assertThat(bnnModels.size()).isEqualTo(2);
        assertThat(bnnModels.get(0).getName()).isEqualTo("nameB1");
        assertThat(bnnModels.get(1).getName()).isEqualTo("nameB2");

    }

    @Test
    public void shouldReturnURIStringAndUpdateDataInDatabaseWhenCallUpdateANNMethod() throws Exception {
        List<ANNModel> annModels = initData();
        Long id = annModels.get(0).getId();
        ANN ann = new ANN();
        ann.setId(id);
        ann.setName("testChangeA");

        String url = annService.updateANN(ann);

        assertEquals("/gaia/rest/anns/"+id, url);

        ANNModel annModel = annDao.idEquals(id).querySingle();
        assertThat(annModel.getName()).isEqualTo("testChangeA");

    }

    @Test
    public void shouldReturnURIStringAndDeleteDataInDatabaseWhenCallDeleteANNMethod() throws Exception {
        List<ANNModel> annModels = initData();
        Long id = annModels.get(0).getId();

        String url = annService.deleteANN(id);

        assertEquals("/gaia/rest/anns", url);

        List<ANNModel> annModelList=annDao.where().queryList();
        assertThat(annModelList.size()).isEqualTo(1);

    }

    private List<ANNModel> initData() {
        BNNModel bnnModel1 = dummyBNNModel("bname1");
        BNNModel bnnModel2 = dummyBNNModel("bname2");
        bnnDao.save(bnnModel1);
        bnnDao.save(bnnModel2);
        List<BNNModel> bnnModels1 = Arrays.asList(bnnModel1);
        List<BNNModel> bnnModels2 = Arrays.asList(bnnModel1, bnnModel2);

        ANNModel annModel1 = dummyANNModel("name1");
        ANNModel annModel2 = dummyANNModel("name2");

        annModel1 = addBNNModels(annModel1, bnnModels1);
        annModel2 = addBNNModels(annModel2, bnnModels2);

        annDao.save(annModel1);
        annDao.save(annModel2);
        return new ArrayList<>(Arrays.asList(annModel1, annModel2));
    }

    private ANNModel dummyANNModel(String name) {
        ANNModel annModel = new ANNModel();
        annModel.setName(name);
        return annModel;
    }

    private BNNModel dummyBNNModel(String name) {
        BNNModel bnnModel = new BNNModel();
        bnnModel.setName(name);
        return bnnModel;
    }

    private ANNModel addBNNModels(ANNModel annModel, List<BNNModel> bnnModelList) {
        annModel.setBnn(bnnModelList);
        return annModel;
    }
}
