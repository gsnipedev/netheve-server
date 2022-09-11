package com.gsnipedev.netheve.server.interfaces

interface JwtAuthServiceInterface {

    fun generateToken(username:String): String


    fun validateToken(token: String, username: String): Boolean

}