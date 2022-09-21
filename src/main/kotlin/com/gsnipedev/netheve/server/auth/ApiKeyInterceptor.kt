package com.gsnipedev.netheve.server.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.gsnipedev.netheve.server.error.UnauthorizedException
import com.gsnipedev.netheve.server.service.JwtAuthService
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor

@Component
class ApiKeyInterceptor(val jwtAuthService: JwtAuthService) : WebRequestInterceptor{

    private final val algorithm: Algorithm = Algorithm.HMAC256("secret")
    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer("secret").build()

    override fun preHandle(request: WebRequest) {
        val key: String? = request.getHeader("X-Api-Key")
        val username: String? = request.getHeader("username")

        if(key == null) throw UnauthorizedException()
        if(username == null) throw UnauthorizedException()

        if(!jwtAuthService.validateToken(token = key, username = username))
        {
            throw UnauthorizedException()
        }


    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //nothing
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //nothing
    }

}