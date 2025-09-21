package edu.infnet.controller;

import com.google.gson.Gson;
import edu.infnet.entity.Mensalista;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MensalistaController {
    static Gson gson = new Gson();
    static List<Mensalista> mensalistas = new ArrayList<>();

    public static void config(Javalin app) {
        app.get("/mensalistas", MensalistaController::getMensalisas);
        app.get("/mensalista/{id}", MensalistaController::getMensalistaById);
        app.post("/mensalista", MensalistaController::criarMensalista);
    }

    public static void getMensalisas(Context ctx) {
        String json = gson.toJson(mensalistas);

        ctx.json(json);
        ctx.status(HttpURLConnection.HTTP_OK);
    }

    public static void getMensalistaById(Context ctx) {
        int idMatricula = Integer.parseInt(ctx.pathParam("id"));
        ctx.result("Detalhes do mensalista com ID: " + idMatricula);

        Mensalista mensalista = mensalistas.stream()
                .filter(m -> m.getMatricula() == (idMatricula))
                .findFirst()
                .orElse(null);

        if (mensalista != null) {
            ctx.json(gson.toJson(mensalista));
            ctx.status(HttpURLConnection.HTTP_OK);
        } else {
            ctx.result("Mensalista não encontrado");
            ctx.status(HttpURLConnection.HTTP_NOT_FOUND);
        }
    }

    public static void criarMensalista(Context ctx) {
        String body = ctx.body();
        Mensalista novoMensalista = gson.fromJson(body, Mensalista.class);

        boolean isDuplicateMatricula = mensalistas.stream()
                .anyMatch(m -> m.getMatricula() == (novoMensalista.getMatricula()));

        if (isDuplicateMatricula) {
            ctx.result("Já existe um mensalista com a matrícula: " + novoMensalista.getMatricula());
            ctx.status(HttpURLConnection.HTTP_CONFLICT);
            return;
        }

        mensalistas.add(novoMensalista);
        ctx.json(gson.toJson(novoMensalista));
        ctx.status(HttpURLConnection.HTTP_CREATED);
    }

}
