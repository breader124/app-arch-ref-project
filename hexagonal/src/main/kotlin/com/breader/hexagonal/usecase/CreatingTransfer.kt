package com.breader.hexagonal.usecase

import com.breader.hexagonal.domain.Transfer
import com.breader.hexagonal.domain.TransferCreationException
import com.breader.hexagonal.domain.TransferType
import com.breader.hexagonal.usecase.port.TransferApi
import com.breader.hexagonal.usecase.port.TransferRepo
import com.breader.hexagonal.usecase.port.UserRepo
import org.javamoney.moneta.Money
import org.springframework.lang.NonNull
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import javax.money.Monetary

class CreatingTransfer(
    private val userRepo: UserRepo,
    private val transferRepo: TransferRepo,
    private val transferApi: TransferApi
) {

    fun createTransfer(request: CreateTransferRequest) {
        val debtor = userRepo.findByAccountNumber(request.from) ?: throw TransferCreationException("No such debtor account")
        val doesCreditorAccountExists = transferApi.isAccountNumberCorrect(request.to)
        if (!doesCreditorAccountExists) {
            throw TransferCreationException("No such creditor account")
        }

        val amount = Money.of(request.amount, Monetary.getCurrency(request.currency))
        val date = LocalDate.from(request.timestamp)
        val newTransfer = Transfer(request.from, request.to, date, amount)
        transferRepo.save(newTransfer)

        val type = debtor.registerTransfer(request.from, request.to, date, amount)
        if (type == TransferType.EXTERNAL) {
            transferApi.notifyExternalBankAboutTransfer(request.to, date, amount)
        }
        userRepo.save(debtor)
    }

}

data class CreateTransferRequest(
    @NonNull val from: String,
    @NonNull val to: String,
    @NonNull val timestamp: Instant,
    @NonNull val amount: BigDecimal,
    @NonNull val currency: String
)
