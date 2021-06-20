package com.breader.hexagonal.adapter

import com.breader.hexagonal.usecase.port.AccountValidator

class IbanAccountValidator : AccountValidator {

    override fun isAccountNumberCorrect(accountNumber: String): Boolean {
        TODO("Integration with external account validation service")
    }

}
