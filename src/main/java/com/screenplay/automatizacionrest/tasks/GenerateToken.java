package com.screenplay.automatizacionrest.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class GenerateToken implements Task {

    private String username;
    private String password;

    public GenerateToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static GenerateToken withCredentials(String username, String password) {
        return new GenerateToken(username, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/auth")
                        .with(request -> request
                        .body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
                        .header("Content-Type", "application/json")
                        )
        );
    }
}
