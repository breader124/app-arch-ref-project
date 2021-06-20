package com.breader.hexagonal.usecase.port

interface AccountValidator {

    fun isAccountNumberCorrect(accountNumber: String): Boolean

}
