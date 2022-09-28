package com.gsnipedev.netheve.server.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.gsnipedev.netheve.server.error.UnauthorizedException
import com.gsnipedev.netheve.server.service.JwtAuthService
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ApiKeyInterceptor(val jwtAuthService: JwtAuthService) : HandlerInterceptor{

    private final val algorithm: Algorithm = Algorithm.HMAC256("secret")
    val verifier: JWTVerifier = JWT.require(algorithm).withIssuer("secret").build()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val key: String? = request.getHeader("X-Api-Key")
        val username: String? = request.getHeader("username")


        if(key == null) throw UnauthorizedException()
        if(username == null) throw UnauthorizedException()

        if(!jwtAuthService.validateToken(token = key, username = username))
        {
            throw UnauthorizedException()
        }

        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        @Nullable modelAndView: ModelAndView?
    ) {
        //nothing
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        @Nullable ex: Exception?
    ) {
        //nothing
    }

}