/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.screenplay.automatizacionrest.actors;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

/**
 *
 * @author crist
 */
public class ApiUserRest  {

    public static Actor buildactor(String actorRole) {
        return Actor.named(actorRole)
                .whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }
}

