package com.breader.hexagonal.usecase

import com.breader.hexagonal.domain.Transfer
import com.breader.hexagonal.domain.TransferCreationException
import com.breader.hexagonal.usecase.port.AccountValidator
import com.breader.hexagonal.usecase.port.TransferRepo
import com.breader.hexagonal.usecase.port.UserRepo
import org.javamoney.moneta.Money
import org.springframework.lang.NonNull
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.money.Monetary

class CreatingTransfer(
    private val userRepo: UserRepo,
    private val transferRepo: TransferRepo,
    private val accountValidator: AccountValidator
) {

    fun createTransfer(request: CreateTransferRequest) {
        val debtor = userRepo.findByAccountNumber(request.from) ?: throw TransferCreationException()
        val doesCreditorAccountExists = accountValidator.isAccountNumberCorrect(request.to)
        if (!doesCreditorAccountExists) {
            throw TransferCreationException()
        }

        val amount = Money.of(request.amount, Monetary.getCurrency(request.currency))
        val newTransfer = Transfer(request.from, request.to, request.date, amount)
        transferRepo.save(newTransfer)

        debtor.registerTransfer(request.from, request.to, amount)
    }

}

data class CreateTransferRequest(
    @NonNull val from: String,
    @NonNull val to: String,
    @NonNull val date: LocalDateTime,
    @NonNull val amount: BigDecimal,
    @NonNull val currency: String
)
