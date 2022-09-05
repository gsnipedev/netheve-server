package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.entity.AccountEntity
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.login.LoginRequest
import com.gsnipedev.netheve.server.model.login.LoginResponse
import com.gsnipedev.netheve.server.model.register.RegisterRequest
import com.gsnipedev.netheve.server.model.register.RegisterResponse
import com.gsnipedev.netheve.server.model.updatepassword.UpdatePasswordRequest
import com.gsnipedev.netheve.server.model.updatepassword.UpdatePasswordResponse
import com.gsnipedev.netheve.server.service.AccountService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/account")
class AccountController(val accountService: AccountService) {

    @GetMapping("/{id}")
    fun getone(@PathVariable(name = "id") data: Int) : WebResponse<AccountEntity>
    {
        return accountService.getOne(data)
    }

    @PostMapping(
        value = ["/login"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun Login(@RequestBody body: LoginRequest): WebResponse<LoginResponse>{

        return accountService.login(body.username, body.password)
    }

    @PostMapping(
        value = ["/register"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun Register(@RequestBody body: RegisterRequest): WebResponse<RegisterResponse>
    {
        return accountService.register(body)
    }

    @PostMapping(
        value = ["/change-password"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun UpdatePassword(@RequestBody body: UpdatePasswordRequest): WebResponse<UpdatePasswordResponse>
    {
        return accountService.updatePassword(body)
    }

}