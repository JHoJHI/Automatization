/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeleteBooking implements Task {

    private final int bookingId;
    private final String token;

    public DeleteBooking(int bookingId, String token) {
        this.bookingId = bookingId;
        this.token = token;
    }

    public static DeleteBooking withIdAndToken(int bookingId, String token) {
        return instrumented(DeleteBooking.class, bookingId, token);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Delete.from("/booking/" + bookingId)
                .with(request -> request.header("Cookie", "token=" + token))
        );
    }
}
