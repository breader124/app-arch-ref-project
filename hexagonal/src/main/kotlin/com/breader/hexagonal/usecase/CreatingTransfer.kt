package com.breader.hexagonal.usecase

import com.breader.hexagonal.usecase.port.AccountValidator
import com.breader.hexagonal.usecase.port.TransferRepo
import com.breader.hexagonal.usecase.port.UserRepo
import org.springframework.lang.NonNull
import java.math.BigInteger

class CreatingTransfer(
    private val userRepo: UserRepo,
    private val transferRepo: TransferRepo,
    private val accountValidator: AccountValidator
) {

    fun createTransfer(request: CreateTransferRequest) {
        TODO("Not yet implemented")

        // check if debtor account exists
        // check if creditor account exists
        // check if there is enough money on debtor's account
        // check if there is enough money od debtor's account
    }

}

data class CreateTransferRequest(
    @NonNull val from: String,
    @NonNull val to: String,
    @NonNull val amount: BigInteger,
    @NonNull val commaPos: Int,
)
