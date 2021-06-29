package com.breader.mortgage.processor

import com.breader.mortgage.source.IncomeDataReq
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.util.logging.Level
import java.util.logging.Logger

@Component
class ScoreCalculator(
    @Value("\${topic.rating.input}") private val ratingInput: String
) {

    companion object {
        private val logger = Logger.getLogger(this::class.java.name)
    }

    @KafkaListener(
        topics = ["\${topic.rating.input}"],
        groupId = "\${group.rating.id}",
        containerFactory = "incomeInputContainerFactory"
    )
    fun incomeInputListener(@Payload incomeData: IncomeDataReq) {
        logger.log(Level.INFO, "Received payload from $ratingInput topic: $incomeData")
    }

}
