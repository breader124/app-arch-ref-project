package com.breader.hexagonal.domain

import javax.money.MonetaryAmount

class User(val id: UserId, val accountList: List<Account>) {

    fun isAccountHolderForAccount(accountNumber: String): Boolean {
        TODO("Not yet implemented")
    }

    fun doesHaveEnoughMoney(accountNumber: String, monetaryAmount: MonetaryAmount): Boolean {
        TODO("Not yet implemented")
    }

    fun registerExternalTransfer(monetaryAmount: MonetaryAmount) {
        TODO("Not yet implemented")
    }

}
