package com.gsnipedev.netheve.server.model.updatepassword

data class UpdatePasswordRequest(

    val username: String,
    val oldPassword: String,
    val newPassword: String,
    val newPasswordConfirmation: String
)
