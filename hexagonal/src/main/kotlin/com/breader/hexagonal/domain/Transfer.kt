package com.breader.hexagonal.domain

import org.javamoney.moneta.Money
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
import javax.money.Monetary
import javax.money.MonetaryAmount

class Transfer(val from: String, val to: String, val date: LocalDateTime, val amount: MonetaryAmount) {

    val id = TransferId()

    init {
        if (!isDateBankingDay() || !doesAmountExceedBankLimit()) {
            throw TransferCreationException()
        }
    }

    private fun isDateBankingDay(): Boolean {
        val dayOfWeek = date.dayOfWeek
        return !(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) // simplified as hell!
    }

    private fun doesAmountExceedBankLimit(): Boolean {
        val limit = Money.of(BigDecimal.valueOf(5000000), Monetary.getCurrency("USD"))
        return amount.isGreaterThan(limit)
    }

}
