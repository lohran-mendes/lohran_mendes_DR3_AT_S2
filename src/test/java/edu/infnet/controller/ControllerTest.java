package edu.infnet.controller;

import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ControllerTest {

    @Test
    public void deveValidarOEndpointHello(){
        Context ctx = Mockito.mock(Context.class);
        Controller.getHello(ctx);

        Mockito.verify(ctx).result("Hello Javalin!");
        Mockito.verify(ctx).status(200);
    }

}
