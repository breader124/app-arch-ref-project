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
import java.time.LocalDate
import javax.money.Monetary

class CreatingTransfer(
    private val userRepo: UserRepo,
    private val transferRepo: TransferRepo,
    private val transferApi: TransferApi
) {

    fun createTransfer(request: CreateTransferRequest) {
        val debtor = userRepo.findByAccountNumber(request.from) ?: throw TransferCreationException()
        val doesCreditorAccountExists = transferApi.isAccountNumberCorrect(request.to)
        if (!doesCreditorAccountExists) {
            throw TransferCreationException()
        }

        val amount = Money.of(request.amount, Monetary.getCurrency(request.currency))
        val newTransfer = Transfer(request.from, request.to, request.date, amount)
        transferRepo.save(newTransfer)

        val type = debtor.registerTransfer(request.from, request.to, request.date, amount)
        if (type == TransferType.EXTERNAL) {
            transferApi.notifyExternalBankAboutTransfer(request.to, request.date, amount)
        }
        userRepo.save(debtor)
    }

}

data class CreateTransferRequest(
    @NonNull val from: String,
    @NonNull val to: String,
    @NonNull val date: LocalDate,
    @NonNull val amount: BigDecimal,
    @NonNull val currency: String
)
