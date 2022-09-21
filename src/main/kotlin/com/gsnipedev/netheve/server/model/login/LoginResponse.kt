package com.gsnipedev.netheve.server.model.login

data class LoginResponse(

    val error_message : String,
    val access_token: String,
    val user_id: Int
)
