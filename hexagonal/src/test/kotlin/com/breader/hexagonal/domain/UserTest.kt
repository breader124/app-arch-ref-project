package com.breader.hexagonal.domain

import org.javamoney.moneta.Money
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.time.LocalDate
import javax.money.Monetary

internal class UserTest {

    private val userId: UserId = UserId()

    private val PLN = Monetary.getCurrency("PLN")
    private val accountList = listOf(
        Account("PL10 1050 0099 7603 1234 5678 9123", PLN, Money.of(1234, "PLN"), Money.of(0, "PLN")),
        Account("PL10 0700 9312 3456 7867 8900 9976", PLN, Money.of(1234, "PLN"), Money.of(0, "PLN"))
    )

    private val user = User(userId, accountList)

    private val nonBankingDate = LocalDate.of(2021, 6, 20)
    private val bankingDate = LocalDate.of(2021, 6, 21)

    @Test
    fun transfer_should_not_be_registered_when_user_does_not_have_such_an_account() {
        assertThrows(RegisteringTransferException::class.java) {
            user.registerTransfer(
                "fake account number",
                "PL10 0700 9312 3456 7867 8900 9976",
                LocalDate.now(),
                Money.of(20, "PLN")
            )
        }
    }

    @Test
    fun transfer_should_not_be_registered_when_there_is_no_enough_money_on_the_account() {
        assertThrows(RegisteringTransferException::class.java) {
            user.registerTransfer(
                "PL10 1050 0099 7603 1234 5678 9123",
                "PL10 0700 9312 3456 7867 8900 9976",
                LocalDate.now(),
                Money.of(2000, "PLN")
            )
        }
    }

    @Test
    fun transfer_should_be_registered_when_there_is_enough_money_on_the_account() {
        user.registerTransfer(
            "PL10 1050 0099 7603 1234 5678 9123",
            "PL10 0700 9312 3456 7867 8900 9976",
            LocalDate.now(),
            Money.of(20, "PLN")
        )
    }

    @Test
    fun transfer_should_be_registered_when_the_date_is_banking_date_and_transfer_is_internal() {
        user.registerTransfer(
            "PL10 1050 0099 7603 1234 5678 9123",
            "PL10 0700 9312 3456 7867 8900 9976",
            bankingDate,
            Money.of(20, "PLN")
        )
    }

    @Test
    fun transfer_should_be_registered_when_the_date_is_not_banking_date_and_transfer_is_internal() {
        user.registerTransfer(
            "PL10 1050 0099 7603 1234 5678 9123",
            "PL10 0700 9312 3456 7867 8900 9976",
            nonBankingDate,
            Money.of(20, "PLN")
        )
    }

    @Test
    fun transfer_should_be_registered_when_the_date_is_banking_date_and_transfer_is_external() {
        user.registerTransfer(
            "PL10 1050 0099 7603 1234 5678 9123",
            "external account",
            bankingDate,
            Money.of(20, "PLN")
        )
    }

    @Test
    fun transfer_should_not_be_registered_when_the_date_is_not_banking_date_and_transfer_is_external() {
        assertThrows(RegisteringTransferException::class.java) {
            user.registerTransfer(
                "PL10 1050 0099 7603 1234 5678 9123",
                "external account",
                nonBankingDate,
                Money.of(20, "PLN")
            )
        }
    }

}
