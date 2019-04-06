package com.kinisoftware.chatbottour.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class OrderIntent : RequestHandler {
    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.intentName("OrderIntent"))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val request = input.requestEnvelope.request
        val intentRequest = request as IntentRequest
        val intent = intentRequest.intent
        val slots = intent.slots

        val order = slots["food"]!!.value
        println("Slot value:$order")

        val text = "Okay, ¡gran elección! Vamos a comprar $order en el restaurante."
        return input.responseBuilder
            .withSpeech(text)
            .withShouldEndSession(true)
            .build()
    }
}