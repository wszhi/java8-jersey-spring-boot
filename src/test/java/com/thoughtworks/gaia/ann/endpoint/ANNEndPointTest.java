package com.thoughtworks.gaia.ann.endpoint;


import com.thoughtworks.gaia.ann.entity.ANN;
import com.thoughtworks.gaia.ann.service.ANNService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class ANNEndPointTest {
    @Mock
    private ANNService annService;
    @InjectMocks
    private ANNEndPoint annEndPoint;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldReturnANNListWhenCallGetANN() throws Exception {
        ANN ann1 = new ANN();
        ANN ann2 = new ANN();
        List<ANN> anns = new ArrayList<>(Arrays.asList(ann1,ann2));
        when(annService.getAllANNs()).thenReturn(anns);
        Response response = annEndPoint.getANN();
        Response expect = Response.ok().entity(anns).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }

    @Test
    public void shouldReturnANNWhenCallGetANNByID() throws Exception {
        ANN ann = new ANN();
        when(annService.getANNById(1L)).thenReturn(ann);
        Response response = annEndPoint.getANNByID(1L);
        Response expect = Response.ok().entity(ann).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }

    @Test
    public void shouldReturnPostURIWhenAddANNSuccess() throws Exception {
        ANN ann =new ANN();
        String url="/gaia/rest/anns";
        when(annService.addANN(ann)).thenReturn(url);

        Response response = annEndPoint.addANN(ann);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }

    @Test
    public void shouldReturnURIWhenUpdateANNSuccess() throws Exception {
        ANN ann =new ANN();
        ann.setId(1L);
        String url="/gaia/rest/anns/1";
        when(annService.updateANN(ann)).thenReturn(url);

        Response response = annEndPoint.updateANN(1L,ann);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));

    }

    @Test
    public void shouldReturnURIStringWhenDeleteANNByIDSuccess() throws Exception {

        String url="/gaia/rest/anns";
        when(annService.deleteANN(1L)).thenReturn(url);
        Response response = annEndPoint.deleteANN(1L);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
}