package com.gsnipedev.netheve.server.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.gsnipedev.netheve.server.interfaces.JwtAuthServiceInterface
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtAuthService: JwtAuthServiceInterface {

    override fun generateToken(username: String, issuerId: Int): String {
        val defaultPayload = mapOf<String, Int>(
            "id" to issuerId
        )
        return try {
            val algorithm = Algorithm.HMAC256("secret")
            val token = JWT.create().withIssuer(username).withPayload(defaultPayload).withIssuedAt(Date()).sign(algorithm)
            token
        }catch (e: Exception)
        {
            "error"
        }
    }

    override fun validateToken(token: String, username: String): Boolean {
        val algorithm: Algorithm = Algorithm.HMAC256("secret")
        val verifier: JWTVerifier = JWT.require(algorithm).withIssuer(username).build()
        return try {
            verifier.verify(token)
            true
        }catch (e:JWTVerificationException) {
            false
        }
    }

}