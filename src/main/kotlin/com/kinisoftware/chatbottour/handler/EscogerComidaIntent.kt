package com.kinisoftware.chatbottour.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class EscogerComidaIntent : RequestHandler {
    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("escoger_comida"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        if (!input.attributesManager.sessionAttributes.containsKey(AttributeConstants.FOOD)
            || !input.attributesManager.sessionAttributes.containsKey(AttributeConstants.RESTAURANT)
        ) {
            return input.responseBuilder
                .withSpeech("Comienza seleccionando el tipo de comida como pizza o hamburguesa")
                .withShouldEndSession(false)
                .build()
        }

        val request = input.requestEnvelope.request
        val intentRequest = request as IntentRequest
        val intent = intentRequest.intent
        val slots = intent.slots

        val order = slots["comprar"]!!.value

        val restaurant = input.attributesManager.sessionAttributes[AttributeConstants.RESTAURANT]
        val text = "Okay, ¡gran elección! Vamos a comprar $order en $restaurant."
        return input.responseBuilder
            .withSpeech(text)
            .withShouldEndSession(true)
            .build()
    }
}