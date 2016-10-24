package test.functional.product;

import com.thoughtworks.gaia.GaiaApplication;
import com.thoughtworks.gaia.common.constant.EnvProfile;
import com.thoughtworks.gaia.product.dao.A1NDao;
import com.thoughtworks.gaia.product.dao.B1NDao;
import com.thoughtworks.gaia.product.entity.A1N;
import com.thoughtworks.gaia.product.entity.B1N;
import com.thoughtworks.gaia.product.model.A1NModel;
import com.thoughtworks.gaia.product.model.B1NModel;
import com.thoughtworks.gaia.product.service.A1NService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(GaiaApplication.class)
@Rollback
@Transactional
@ActiveProfiles({EnvProfile.TEST})
public class A1NServiceFunctionalTest {
    @Autowired
    private A1NDao a1NDao;

    @Autowired
    private A1NService a1NService;

    @Autowired
    private B1NDao b1NDao;

    @Test
    public void shouldGetA1N() throws Exception {
        A1NModel a1NModel = dummyA1NModel();

        a1NDao.save(a1NModel);
        b1NDao.save(dummyB1NModel());

        List<A1N> a1Ns = a1NService.getAllA1Ns();

        assertThat(a1Ns.size()).isEqualTo(1);
        assertThat(a1Ns.get(0).getName()).isEqualTo("name");
        assertThat(a1Ns.get(0).getB1Ns().get(0).getName()).isEqualTo("bname");

    }

    @Test
    public void shouldGetA1NByID() throws Exception {
        A1NModel a1NModel = dummyA1NModel();

        a1NDao.save(a1NModel);
        b1NDao.save(dummyB1NModel());

        A1N a1Ns = a1NService.getA1NById(1L);

        assertThat(a1Ns.getName()).isEqualTo("name");
        assertThat(a1Ns.getB1Ns().get(0).getName()).isEqualTo("bname");

    }
    @Test
    public void shouldAddA1NAndReturnURL() throws Exception {

        A1N a1N = new A1N();
        a1N.setName("testA");
        List<B1N> b1NList=new ArrayList<>();
        B1N b1N = new B1N();
        b1N.setName("testB");
        b1NList.add(b1N);
        a1N.setB1Ns(b1NList);

        String url = a1NService.addA1N(a1N);

        assertThat(url).isEqualTo("/a1ns");

        List<A1N> a1Ns = a1NService.getAllA1Ns();
        assertThat(a1Ns.size()).isEqualTo(1);
        assertThat(a1Ns.get(0).getB1Ns().size()).isEqualTo(1);
        assertThat(a1Ns.get(0).getB1Ns().get(0).getName()).isEqualTo("testB");

    }

    private A1NModel dummyA1NModel() {
        A1NModel a1NModel = new A1NModel();
        a1NModel.setName("name");
        return a1NModel;
    }
    private B1NModel dummyB1NModel() {
        B1NModel b1NModel = new B1NModel();
        b1NModel.setName("bname");
        b1NModel.setA1nid(1L);
        return b1NModel;
    }

}
