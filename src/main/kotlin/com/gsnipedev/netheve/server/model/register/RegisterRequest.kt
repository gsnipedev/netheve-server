package com.gsnipedev.netheve.server.model.register

data class RegisterRequest(

    val username: String,
    val password: String,
    val password_confirmation: String,
    val email: String,
    val firstname: String,
    val lastname: String,
)
