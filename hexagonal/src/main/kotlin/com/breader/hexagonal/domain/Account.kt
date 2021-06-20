package com.breader.hexagonal.domain

import javax.money.CurrencyUnit
import javax.money.MonetaryAmount

class Account(val accountNumber: AccountNumber, val accountCurrency: CurrencyUnit, val balance: MonetaryAmount) {

}
