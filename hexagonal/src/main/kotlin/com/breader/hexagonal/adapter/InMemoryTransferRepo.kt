package com.breader.hexagonal.adapter

import com.breader.hexagonal.domain.Transfer
import com.breader.hexagonal.usecase.port.TransferRepo
import java.util.logging.Level
import java.util.logging.Logger

class InMemoryTransferRepo : TransferRepo {

    companion object {
        private val logger = Logger.getLogger(InMemoryTransferRepo::javaClass.name)
    }

    private val db: MutableList<Transfer> = mutableListOf()

    override fun save(t: Transfer): Boolean {
        val result = db.add(t)
        if (result) {
            logger.log(Level.INFO, "Transfer with ID = ${t.transferId} saved in db")
        } else {
            logger.log(Level.INFO, "Problem occured trying to save transfer with ID = ${t.transferId}")
        }
        return result
    }

    override fun modify(t: Transfer): Boolean {
        val transferIndex = db.indexOf(t)
        return if (transferIndex != -1) {
            db[transferIndex] = t
            true
        } else {
            false
        }
    }

    override fun delete(t: Transfer): Boolean {
        val transferIndex = db.indexOf(t)
        return if (transferIndex != -1) {
            db.removeAt(transferIndex)
            true
        } else {
            false
        }
    }

}
