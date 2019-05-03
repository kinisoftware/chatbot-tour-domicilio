package com.kinisoftware.chatbottour

import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import com.kinisoftware.chatbottour.handler.LaunchRequestHandler
import com.kinisoftware.chatbottour.handler.OrderIntent
import com.kinisoftware.chatbottour.handler.WannaEatAtIntent
import com.kinisoftware.chatbottour.handler.WannaEatIntent
import com.kinisoftware.chatbottour.interceptor.LogRequestInterceptor
import com.kinisoftware.chatbottour.interceptor.LogResponseInterceptor

class ChatbotTourStreamHandler : SkillStreamHandler(skill) {
    companion object {
        private val skill = Skills.custom()
            .addRequestInterceptor(LogRequestInterceptor())
            .addResponseInterceptor(LogResponseInterceptor())
            .addRequestHandlers(
                LaunchRequestHandler(),
                WannaEatIntent(),
                WannaEatAtIntent(),
                OrderIntent()
            )
            .build()
    }
}