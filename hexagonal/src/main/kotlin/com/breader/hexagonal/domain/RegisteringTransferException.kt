package com.breader.hexagonal.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad input params, transfer registration error")
class RegisteringTransferException(message: String) : Exception(message)
