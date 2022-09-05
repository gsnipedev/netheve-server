package com.gsnipedev.netheve.server.controller

import com.gsnipedev.netheve.server.error.UnauthorizedException
import com.gsnipedev.netheve.server.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.persistence.EntityNotFoundException


@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun UnauthorizedResponse() : WebResponse<String>
    {
        return WebResponse(
            code = 401,
            status = "Unauthorized Access",
            data = "Unauthorized Access"
        )
    }

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun EntityNotFoundResponse() : WebResponse<String>
    {
        return WebResponse(
            code = 404,
            status = "Not Found",
            data = "Entity not Found"
        )
    }
}