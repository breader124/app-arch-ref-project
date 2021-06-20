package com.breader.hexagonal.domain

import javax.money.MonetaryAmount

class User(val id: UserId, val accountList: List<Account>) {

    fun registerTransfer(from: String, to: String, monetaryAmount: MonetaryAmount) {
        TODO("Not yet implemented")
    }

    private fun registerInternalTransfer(from: String, to: String, amount: MonetaryAmount) {
        TODO("Not yet implemented")
    }

    private fun registerExternalTransfer(from: String, to: String, amount: MonetaryAmount) {
        TODO("Not yet implemented")
    }

    private fun doesHaveEnoughMoney(accountNumber: String, monetaryAmount: MonetaryAmount): Boolean? {
        return findAccountByNumber(accountNumber)?.run {
            return balance.isGreaterThan(monetaryAmount)
        }
    }

    private fun findAccountByNumber(accountNumber: String): Account? {
        return accountList.firstOrNull { it.accountNumber == accountNumber }
    }

}
