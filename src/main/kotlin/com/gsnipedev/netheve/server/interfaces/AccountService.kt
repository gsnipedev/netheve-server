package com.gsnipedev.netheve.server.interfaces

import com.gsnipedev.netheve.server.entity.AccountEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.login.LoginResponse
import com.gsnipedev.netheve.server.model.logout.LogoutResponse
import com.gsnipedev.netheve.server.model.register.RegisterRequest
import com.gsnipedev.netheve.server.model.register.RegisterResponse
import com.gsnipedev.netheve.server.model.updatepassword.UpdatePasswordRequest
import com.gsnipedev.netheve.server.model.updatepassword.UpdatePasswordResponse

interface AccountService {

    fun getOne(data: Int): WebResponse<AccountEntity>

    fun login(username: String, password: String): WebResponse<LoginResponse>

    fun logout(username: String, apiKey: String) : WebResponse<LogoutResponse>

    fun register(data: RegisterRequest) : WebResponse<RegisterResponse>

    fun updatePassword(data: UpdatePasswordRequest) : WebResponse<UpdatePasswordResponse>

}