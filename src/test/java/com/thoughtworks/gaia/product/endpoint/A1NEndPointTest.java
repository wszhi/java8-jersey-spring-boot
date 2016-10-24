package com.thoughtworks.gaia.product.endpoint;

import com.thoughtworks.gaia.product.entity.A1N;
import com.thoughtworks.gaia.product.service.A1NService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by szwang on 10/21/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class A1NEndPointTest {
    @Mock
    A1NService a1NService;
    @InjectMocks
    A1NEndPoint a1NEndPoint;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldReturnA1nListDataWhenSelectSuccess() throws Exception {
        A1N a1N1 =new A1N();
        A1N a1N2 =new A1N();
        List<A1N> a1Ns = new ArrayList<>();
        a1Ns.add(a1N1);
        a1Ns.add(a1N2);
        when(a1NService.getAllA1Ns()).thenReturn(a1Ns);

        Response response = a1NEndPoint.getA1N();

        Response expect = Response.ok().entity(a1Ns).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }

    @Test
    public void shouldReturnA1nDataWhenSelectByIDSuccess() throws Exception {
        A1N a1N1 =new A1N();
        when(a1NService.getA1NById(1L)).thenReturn(a1N1);

        Response response = a1NEndPoint.getA1NById(1L);

        Response expect = Response.ok().entity(a1N1).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
    @Test
    public void shouldReturnPostURIWhenAddAINSuccess() throws Exception {
        A1N a1N1 =new A1N();
        String url="/a1ns";
        when(a1NService.addA1N(a1N1)).thenReturn(url);

        Response response = a1NEndPoint.addA1N(a1N1);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
}