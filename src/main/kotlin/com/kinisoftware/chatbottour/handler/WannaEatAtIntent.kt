package com.kinisoftware.chatbottour.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class WannaEatAtIntent : RequestHandler {
    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("WannaEatAtIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val request = input.requestEnvelope.request
        val intentRequest = request as IntentRequest
        val intent = intentRequest.intent
        val slots = intent.slots

        val restaurant = slots["restaurant"]!!.value.toLowerCase()
        println("Slot value:$restaurant")

        val menuByRestaurant = mapOf(
            "pizzeria lucio" to listOf("Pizza Margarita", "Pizza Especial Lucio"),
            "pizzeria donatello" to listOf("Pizza Cuatro Quesos", "Pizza Especial Donatello")
        )

        val text = if (menuByRestaurant.containsKey(restaurant)) {
            "En $restaurant puedes comer: " + menuByRestaurant[restaurant]?.joinToString()
        } else {
            "Vaya, no conozco el restaurante $restaurant. Prueba alguna de las opciones que te dije antes."
        }

        return input.responseBuilder
            .withSpeech(text)
            .build()
    }
}