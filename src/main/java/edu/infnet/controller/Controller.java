package edu.infnet.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.net.HttpURLConnection;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    public static void config(Javalin app) {
        app.get("/", Controller::getConnection);
        app.get("/hello", Controller::getHello);
        app.get("/status", Controller::getStatus);
        app.get("/saudacao/{nome}", Controller::getSaudacao);
        app.post("/echo", Controller::postEcho);
    }

    public static void getConnection(Context ctx) {
        ctx.result("Conexão realizada com sucesso!");
        ctx.status(HttpURLConnection.HTTP_OK);
    }

    public static void getHello(Context ctx) {
        ctx.result("Hello Javalin!");
        ctx.status(HttpURLConnection.HTTP_OK);
    }

    public static void getStatus(Context ctx) {
        String zonedDateTimeISOFormat = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String json = "{\"status:\": \"OK\", \"timestamp\": \"" + zonedDateTimeISOFormat + "\"}";

        ctx.json(json);
        ctx.status(HttpURLConnection.HTTP_OK);
    }

    public static void getSaudacao(Context ctx) {
        String name = ctx.pathParam("nome");
        String json = "{\"mensagem\": \"" + "Olá, " + name + "!" + "\"}";

        ctx.json(json);
        ctx.status(HttpURLConnection.HTTP_OK);
    }

    public static void postEcho(Context ctx) {
        String body = ctx.body();

        ctx.result(body);
        ctx.status(HttpURLConnection.HTTP_OK);
    }
}
