/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tests;

import com.screenplay.automatizacionrest.tasks.CreateBooking;
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
public class CreateBookingTest {

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Tag("booking")
    @Test
    public void CreateTheFirstBooking() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(CreateBooking.withDetails("Pedro", "Gutierrez", 100, true, "2024-03-01", "2024-04-01", "Comics"));

        // Afirmaciones
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El booking debería crearse correctamente pro primera vez",
                        response -> response.statusCode(200)
                                .body("booking.firstname", equalTo("Pedro"))
                                .body("booking.lastname", equalTo("Gutierrez"))
                )
        );
    }

    @Tag("booking")
    @Test
    public void CreateTheSecondBooking() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(CreateBooking.withDetails("Javier", "Jaramillo", 356, true, "2024-03-15", "2024-04-15", "Terror"));

        // Afirmaciones
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El booking debería crearse correctamente de nuevo",
                        response -> response.statusCode(200)
                                .body("booking.firstname", equalTo("Javier"))
                                .body("booking.lastname", equalTo("Jaramillo"))
                )
        );
    }

}
