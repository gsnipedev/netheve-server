package com.gsnipedev.netheve.server.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.gsnipedev.netheve.server.interfaces.JwtAuthServiceInterface
import org.springframework.stereotype.Service

@Service
class JwtAuthService: JwtAuthServiceInterface {
    val algorithm = Algorithm.HMAC256("secret")!!
    override fun generateToken(username: String): String {
        return try {

            val token = JWT.create().withIssuer(username).sign(algorithm)
            token
        }catch (e: Exception)
        {
            "error"
        }
    }

    override fun validateToken(token: String, username: String): Boolean {

        val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(username).build()
        return try {
            verifier.verify(token)
            true
        }catch (e:JWTVerificationException) {
            false
        }
    }

}