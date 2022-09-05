package com.gsnipedev.netheve.server.config

import com.auth0.jwt.algorithms.Algorithm
import com.gsnipedev.netheve.server.auth.ApiKeyInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class InterceptorConfiguration(val apiKeyInterceptor: ApiKeyInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addWebRequestInterceptor(apiKeyInterceptor).addPathPatterns("/api/account/change-password")
    }

}