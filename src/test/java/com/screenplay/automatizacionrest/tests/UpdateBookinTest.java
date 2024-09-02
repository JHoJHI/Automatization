/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tests;

import com.screenplay.automatizacionrest.tasks.GenerateToken;
import com.screenplay.automatizacionrest.tasks.UpdateBooking;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class UpdateBookinTest {

    @BeforeEach
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Test
    @Tag("update")
    public void should_be_able_to_update_booking_1() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(GenerateToken.withCredentials("admin", "password123"));

        OnStage.theActorInTheSpotlight().attemptsTo(
                UpdateBooking.withDetails(
                        3784, 
                        "Jose", "Gutierrez", 100, true, "2023-05-12", "2023-06-28", "Comics"
                )
        );
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El status de la respuesta debería ser 200",
                        response -> response.statusCode(200)
                )
        );
    }

    @Test
    @Tag("update")
    public void should_be_able_to_update_booking_2() {
        OnStage.theActorCalled("Admin")
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"))
                .attemptsTo(GenerateToken.withCredentials("admin", "password123"));

        OnStage.theActorInTheSpotlight().attemptsTo(
                UpdateBooking.withDetails(
                        3809, // bookingId
                        "Javier", "Mora", 356, true, "2023-06-20", "2023-07-20", "Terror"
                )
        );
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El status de la respuesta debería ser 200",
                        response -> response.statusCode(200)
                )
        );
    }
}
