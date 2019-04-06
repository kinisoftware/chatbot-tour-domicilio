package com.kinisoftware.chatbottour.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class WannaEatIntent : RequestHandler {
    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("WannaEatIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val request = input.requestEnvelope.request
        val intentRequest = request as IntentRequest
        val intent = intentRequest.intent
        val slots = intent.slots

        val foodType = slots["foodType"]!!.value.toLowerCase()
        println("Slot value:$foodType")

        val restaurantsByFoodType = mapOf(
            "pizza" to listOf("Pizzeria Lucio", "Pizzeria Donatello")
        )

        val text = if (restaurantsByFoodType.containsKey(foodType)) {
            "Puedes comer $foodType en: " + restaurantsByFoodType[foodType]?.joinToString()
        } else {
            "Aún no conozco restaurantes que cocinen $foodType. Lo siento"
        }

        return input.responseBuilder
            .withSpeech(text)
            .build()
    }
}