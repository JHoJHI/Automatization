/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tests;

import com.screenplay.automatizacionrest.tasks.DeleteBooking;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 *
 * @author crist
 */
@ExtendWith(SerenityJUnit5Extension.class)
public class DeleteBookingTest {

    private String token;

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        token = "f3080b546058224";
    }

    @Tag("booking")
    @Test
    public void DeleteTheFirstBooking() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(
                        DeleteBooking.withIdAndToken(3810, token)
                );

        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El booking debería eliminarse correctamente",
                        response -> response.statusCode(201)
                                .body(equalTo("Created"))
                )
        );
    }

    @Tag("booking")
    @Test
    public void DeleteTheSecondBooking() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(
                        DeleteBooking.withIdAndToken(3835, token)
                );

        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El segundo booking debería eliminarse correctamente",
                        response -> response.statusCode(201)
                                .body(equalTo("Created"))
                )
        );
    }
}