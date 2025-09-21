package edu.infnet;

import edu.infnet.controller.Controller;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        var app = Javalin.create().start(8080);
        Controller.config(app);
    }
}