/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class UpdateBooking implements Task {

    private final int bookingId;
    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final String checkin;
    private final String checkout;
    private final String additionalneeds;

    public UpdateBooking(int bookingId, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        this.bookingId = bookingId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
        
    }

    public static UpdateBooking withDetails(int bookingId, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        return new UpdateBooking(bookingId, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }

     @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Put.to("/booking/" + bookingId)
                .with(request -> request
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + "1898088ac386e69")
                    .body("{\"firstname\":\"" + firstname + "\", \"lastname\":\"" + lastname + "\", \"totalprice\":" + totalprice + ", \"depositpaid\":" + depositpaid + ", \"checkin\":\"" + checkin + "\", \"checkout\":\"" + checkout + "\", \"additionalneeds\":\"" + additionalneeds + "\"}")
                )
        );
    }
}
