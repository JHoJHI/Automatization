/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tests;

import com.screenplay.automatizacionrest.tasks.GetBooking;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author crist
 */
@ExtendWith(SerenityJUnit5Extension.class)
public class GetBookingTest {

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Tag("regression")
    @Test
    public void should_retrieve_booking_1_successfully() {
        int bookingId = 3514;

        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(GetBooking.byId(bookingId));

        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El booking debería recuperarse correctamente",
                        response -> response.statusCode(200)
                                .body("firstname", equalTo("Pedro"))
                )
        );
    }

    @Tag("regression")
    @Test
    public void should_retrieve_booking_2_successfully() {
        int bookingId = 3525; 
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(GetBooking.byId(bookingId));

        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El booking debería recuperarse correctamente",
                        response -> response.statusCode(200)
                                .body("firstname", equalTo("Javier"))
                )
        );
    }
}
