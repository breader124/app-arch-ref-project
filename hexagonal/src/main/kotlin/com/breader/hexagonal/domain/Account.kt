package com.breader.hexagonal.domain

import javax.money.CurrencyUnit
import javax.money.MonetaryAmount

data class Account(
    val accountNumber: String,
    val accountCurrency: CurrencyUnit,
    val balance: MonetaryAmount,
    val reserved: MonetaryAmount
)
