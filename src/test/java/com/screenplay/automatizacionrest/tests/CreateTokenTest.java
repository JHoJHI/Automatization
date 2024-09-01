/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tests;

import com.screenplay.automatizacionrest.tasks.GenerateToken;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author crist
 */
@ExtendWith(SerenityJUnit5Extension.class)
public class CreateTokenTest {

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Tag("TestCaseOK")
    @Tag("auth")
    @Test
    public void credentialsOkTokenPresent() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(GenerateToken.withCredentials("admin", "password123"));

        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El token debería generarse correctamente",
                        response -> response.statusCode(200)
                                .body("token", notNullValue())
                )
        );
    }

    @Tag("TestCaseNoauthWrong")
    @Test
    public void BadCredentialsUnahut() {
        OnStage.theActorCalled("User")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(GenerateToken.withCredentials("diminsBAD", "badpsw12"));


        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El acceso no autorizado debería retornar un error 401",
                        response -> response.statusCode(200)
                                .body("reason", equalTo("Bad credentials"))
                )
        );

    }
}
