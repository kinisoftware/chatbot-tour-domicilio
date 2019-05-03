package com.kinisoftware.chatbottour.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.model.slu.entityresolution.StatusCode
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

        val foodSlot = slots["food"]
        val food = foodSlot?.value
        val text = when (food) {
            null -> "Elige algún tipo de comida como pizza o hamburguesa"
            else -> {
                val cousine = foodSlot.resolutions.resolutionsPerAuthority
                    .first { it.status.code == StatusCode.ER_SUCCESS_MATCH }.values[0].value.name

                if (restaurantsByCousine.containsKey(cousine)) {
                    input.attributesManager.sessionAttributes[AttributeConstants.FOOD] = food
                    "Puedes comer $food en: " + restaurantsByCousine[cousine]?.joinToString()
                } else {
                    "Aún no conozco restaurantes que cocinen $food. Lo siento"
                }
            }
        }

        return input.responseBuilder
            .withSpeech(text)
            .withShouldEndSession(false)
            .build()
    }

    companion object {
        val restaurantsByCousine = mapOf(
            "Italiana" to listOf("Pizzeria Lucio", "Pizzeria Donatello", "Al rico tiramisú", "Pasta al dente"),
            "Americana" to listOf("Hamburguesas Gourmet", "Todo a la brasa"),
            "Japonesa" to listOf("Sushi express", "Sumo")
        )
    }
}

