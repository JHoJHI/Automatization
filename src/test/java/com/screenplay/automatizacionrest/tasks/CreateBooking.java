/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateBooking implements Task {

    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final String checkin;
    private final String checkout;
    private final String additionalneeds;

    public CreateBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
    }

    public static CreateBooking withDetails(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        return instrumented(CreateBooking.class, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/booking")
                        .with(request -> request
                        .header("Content-Type", "application/json")
                        .body("{\"firstname\":\"" + firstname + "\", \"lastname\":\"" + lastname + "\", \"totalprice\":" + totalprice + ", \"depositpaid\":" + depositpaid + ", \"bookingdates\": {\"checkin\":\"" + checkin + "\", \"checkout\":\"" + checkout + "\"}, \"additionalneeds\":\"" + additionalneeds + "\"}")
                        )
        );
    }
}
