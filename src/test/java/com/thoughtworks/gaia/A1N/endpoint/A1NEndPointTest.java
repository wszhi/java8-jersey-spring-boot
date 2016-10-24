package com.thoughtworks.gaia.a1n.endpoint;

import com.thoughtworks.gaia.a1n.entity.A1N;
import com.thoughtworks.gaia.a1n.service.A1NService;
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
        String url="/gaia/rest/a1ns";
        when(a1NService.addA1N(a1N1)).thenReturn(url);

        Response response = a1NEndPoint.addA1N(a1N1);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
    @Test
    public void shouldReturnUpdateURIWhenUpdateAINSuccess() throws Exception {
        A1N a1N1 =new A1N();
        a1N1.setId(1L);
        String url="/gaia/rest/a1ns/"+a1N1.getId();
        when(a1NService.updateA1N(a1N1)).thenReturn(url);

        Response response = a1NEndPoint.update(a1N1,1L);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
    @Test
    public void shouldReturnDeleteURIWhenDeleteAINSuccess() throws Exception {
        String url="/gaia/rest/a1ns";
        when(a1NService.deleteA1N(1L)).thenReturn(url);

        Response response = a1NEndPoint.deleteA1N(1L);

        Response expect = Response.ok().entity(url).build();
        assertThat(response.getEntity(), is(expect.getEntity()));
    }
}