package com.breader.hexagonal.adapter

import com.breader.hexagonal.usecase.port.AccountValidator

class MockedAccountValidator : AccountValidator {

    override fun isAccountNumberCorrect(accountNumber: String): Boolean {
        TODO("Not yet implemented")
    }

}
