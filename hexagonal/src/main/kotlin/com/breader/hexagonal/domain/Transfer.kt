package com.breader.hexagonal.domain

import javax.money.MonetaryAmount

class Transfer(val id: TransferId, val from: String, val to: String, val amount: MonetaryAmount) {

}
