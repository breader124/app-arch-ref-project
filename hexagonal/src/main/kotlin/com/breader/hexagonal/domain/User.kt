package com.breader.hexagonal.domain

import com.breader.hexagonal.isBankingDay
import java.time.LocalDate
import javax.money.MonetaryAmount

class User(val id: UserId, val accountList: List<Account>) {

    fun registerTransfer(from: String, to: String, date: LocalDate, monetaryAmount: MonetaryAmount) : TransferType {
        val fromAccount = findAccountByNumber(from) ?: throw RegisteringTransferException("Account not found")
        val isEnoughMoney = doesHaveEnoughMoney(fromAccount, monetaryAmount)
        if (!isEnoughMoney) {
            throw RegisteringTransferException("Not enough money")
        }

        val toAccount = findAccountByNumber(to)
        return if (toAccount == null) {
            registerExternalTransfer(fromAccount, date, monetaryAmount)
            TransferType.EXTERNAL
        } else {
            registerInternalTransfer(fromAccount, toAccount, monetaryAmount)
            TransferType.INTERNAL
        }
    }

    private fun registerInternalTransfer(from: Account, to: Account, amount: MonetaryAmount) {
        from.balance.subtract(amount)
        to.balance.add(amount)
    }

    private fun registerExternalTransfer(from: Account, date: LocalDate, amount: MonetaryAmount) {
        if (!date.isBankingDay()) {
            throw RegisteringTransferException("External transfer cannot be registered on non banking day")
        }

        from.balance.subtract(amount)
        from.reserved.add(amount)
    }

    private fun doesHaveEnoughMoney(account: Account, amount: MonetaryAmount) = account.balance.isGreaterThan(amount)

    private fun findAccountByNumber(accountNumber: String): Account? {
        return accountList.firstOrNull { it.accountNumber == accountNumber }
    }

}

enum class TransferType {
    INTERNAL, EXTERNAL
}
