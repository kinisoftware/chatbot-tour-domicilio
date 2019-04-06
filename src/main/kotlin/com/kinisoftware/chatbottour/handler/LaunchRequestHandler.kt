package com.kinisoftware.chatbottour.handler

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.LaunchRequest
import com.amazon.ask.model.Response
import com.amazon.ask.request.Predicates
import java.util.*

class LaunchRequestHandler : RequestHandler {
    override fun canHandle(input: HandlerInput): Boolean {
        return input.matches(Predicates.requestType(LaunchRequest::class.java))
    }

    override fun handle(input: HandlerInput): Optional<Response> {
        val repromptText = """Prueba a escoger un tipo de comida. Por ejemplo, "Quiero comer pizza" o "Quiero comer hamburguesa""""
        val text = "hey, ¿qué te apetece comer hoy?"
        return input.responseBuilder
            .withSpeech(text)
            .withReprompt(repromptText)
            .build()
    }
}