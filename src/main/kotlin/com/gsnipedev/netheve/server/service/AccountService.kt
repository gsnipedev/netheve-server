package com.gsnipedev.netheve.server.service

import com.gsnipedev.netheve.server.entity.AccountEntity
import com.gsnipedev.netheve.server.entity.UserDataEntity
import com.gsnipedev.netheve.server.interfaces.AccountService
import com.gsnipedev.netheve.server.model.WebResponse
import com.gsnipedev.netheve.server.model.login.LoginResponse
import com.gsnipedev.netheve.server.model.logout.LogoutResponse
import com.gsnipedev.netheve.server.model.register.RegisterRequest
import com.gsnipedev.netheve.server.model.register.RegisterResponse
import com.gsnipedev.netheve.server.model.updatepassword.UpdatePasswordRequest
import com.gsnipedev.netheve.server.model.updatepassword.UpdatePasswordResponse
import com.gsnipedev.netheve.server.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException


@Service
class AccountService (
    val accountRepository: AccountRepository,
    val jwtAuthService: JwtAuthService
    ) :
    AccountService {
    override fun getOne(data: Int): WebResponse<AccountEntity> {

        if(!accountRepository.existsById(data))
        {
            throw EntityNotFoundException()
        }

        val response = WebResponse(
            code = 200,
            status = "OK",
            data = accountRepository.getReferenceById(data)
        )

        return response

    }

    override fun login(username: String, password: String): WebResponse<LoginResponse> {
        val issuer : AccountEntity
        val response = WebResponse(
            code = 200,
            status = "OK",
            data = LoginResponse(error_message = "null", access_token = "null", user_id = 0)
        )
        try{
            issuer = accountRepository.getByUsernameAndPassword(username, password)
        }catch (e: Exception)
        {
            response.data = LoginResponse("Wrong username or Password", access_token = "", user_id = 0)
            return response
        }

        val token = jwtAuthService.generateToken(username, issuer.id)
        response.data = LoginResponse(error_message = "Logged In", access_token = token, user_id = issuer.id)
        return response
    }

    override fun logout(username: String, apiKey: String): WebResponse<LogoutResponse> {
        TODO("Implement logout logic")
    }

    override fun register(data: RegisterRequest): WebResponse<RegisterResponse> {
        val response = WebResponse(code = 200, status = "OK", data = RegisterResponse("null"))

        if(data.username.length < 8 || data.username.length > 20)
        {
            response.data = RegisterResponse("Please use 8-20 character for username")
            return response
        }
        if(data.password.length < 8 || data.password.length > 20)
        {
            response.data = RegisterResponse("Please use 8-20 character for password")
            return response
        }

        if(data.password != data.password_confirmation)
        {
            response.data = RegisterResponse("Password does not match")
            return response
        }
        try {
            accountRepository.findByUsername(data.username)
            response.data = RegisterResponse("Username Already Exist")
            return response
        }catch (err : Exception){
            response.data = RegisterResponse("Account Created")
        }
        val newUserDataEntity = UserDataEntity(
            id = 0,
            firstname = data.firstname,
            lastname = data.lastname,
            email = data.email,
            createdAt = Date(),
            updatedAt = null
        )
        val newAccountEntity = AccountEntity(
            id = 0,
            username = data.username,
            password = data.password,
            createdAt = Date(),
            updatedAt = null,
            userData = newUserDataEntity
        )
        accountRepository.save(newAccountEntity)
        response.data = RegisterResponse("Account Created")
        return response
    }

    @Transactional
    override fun updatePassword(data: UpdatePasswordRequest): WebResponse<UpdatePasswordResponse> {
        val response = WebResponse(code = 200, status = "OK", data = UpdatePasswordResponse(""))
        var result: Int = 0

        if(data.newPassword != data.newPasswordConfirmation)
        {
            response.data = UpdatePasswordResponse("New password does not match")
            return response
        }

        try{
            result = accountRepository.findByUsernameAndPassword(data.username, data.oldPassword)
            if(result > 0){
                accountRepository.changePassword(username = data.username, oldPassword = data.oldPassword, newPassword = data.newPassword)
                response.data = UpdatePasswordResponse("Password successfully changed")
            }

        }catch(err: Exception){
            response.data = UpdatePasswordResponse("Something is Wrong, please try again")
        }

        return response

    }
}