package com.breader.hexagonal.usecase.port

import com.breader.hexagonal.domain.Transfer
import com.breader.hexagonal.domain.TransferId

interface TransferRepo {
    fun findById(transferId: TransferId): Transfer?
    fun save(t: Transfer)
    fun delete(t: Transfer)
}
