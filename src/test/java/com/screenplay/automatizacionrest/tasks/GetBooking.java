/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.rest.interactions.Get;

/**
 *
 * @author crist
 */
public class GetBooking implements Task {

    private final int bookingId;

    public GetBooking(int bookingId) {
        this.bookingId = bookingId;
    }

    public static GetBooking byId(int bookingId) {
        return instrumented(GetBooking.class, bookingId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/booking/{id}")
                        .with(request -> request.pathParam("id", bookingId))
        );
    }
}
