package edu.infnet.controller;

import io.javalin.http.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ControllerTest {

    @BeforeEach
    public void setUp(){
        MensalistaController.mensalistas.clear();
    }

    @Test
    public void deveValidarOEndpointHello() {
        Context ctx = Mockito.mock(Context.class);
        Controller.getHello(ctx);

        Mockito.verify(ctx).result("Hello Javalin!");
        Mockito.verify(ctx).status(200);
    }

    @Test
    public void deveCriarNovoMensalista() {
        Context ctx = Mockito.mock(Context.class);
        Mockito.when(ctx.body()).thenReturn("{\"nome\":\"Lohran Mendes\",\"matricula\":\"123456\"}");

        MensalistaController.criarMensalista(ctx);

        Mockito.verify(ctx).status(201);
    }

    @Test
    public void deveBuscarMensalistaPorId() {
        Context ctx = Mockito.mock(Context.class);

        Mockito.when(ctx.body()).thenReturn("{\"nome\":\"Lohran Mendes\",\"matricula\":\"123456\"}");
        MensalistaController.criarMensalista(ctx);

        Mockito.when(ctx.pathParam("id")).thenReturn("123456");
        MensalistaController.getMensalistaById(ctx);

        Mockito.verify(ctx).status(200);
    }

    @Test
    public void deveRetornarTodosMensalistas() {
        Context ctx = Mockito.mock(Context.class);


        Mockito.when(ctx.body()).thenReturn("{\"nome\":\"Lohran Mendes\",\"matricula\":\"123456\"}");
        MensalistaController.criarMensalista(ctx);
        Mockito.when(ctx.body()).thenReturn("{\"nome\":\"Maria Eduarda\",\"matricula\":\"654321\"}");
        MensalistaController.criarMensalista(ctx);

        MensalistaController.getMensalisas(ctx);

        Mockito.verify(ctx).status(200);
        Assertions.assertEquals("[Mensalista{nome='Lohran Mendes', matricula='123456'}, Mensalista{nome='Maria Eduarda', matricula='654321'}]", MensalistaController.mensalistas.toString());
    }
}
