package com.breader.hexagonal.domain

import java.time.LocalDateTime
import javax.money.MonetaryAmount

class Transfer(val from: String, val to: String, val date: LocalDateTime, val amount: MonetaryAmount) {

    val id = TransferId()

    init {

    }

}
