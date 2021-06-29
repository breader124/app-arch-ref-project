package com.breader.mortgage.config

import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin.NewTopics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class ProducerConfig(
    @Value("\${kafka.bootstrap.server}") private val bootstrapServerAddress: String,
    @Value("\${topic.income.input}") private val mortgageIncomeInput: String,
    @Value("\${topic.income.output}") private val mortgageIncomeOutput: String,
    @Value("\${topic.rating.input}") private val ratingInput: String,
    @Value("\${topic.rating.output}") private val ratingOutput: String
) {

    @Bean
    fun inputMortgageTopic(): NewTopics = NewTopics(
        NewTopic(mortgageIncomeInput, 3, 1),
        NewTopic(mortgageIncomeOutput, 3, 1),
        NewTopic(ratingInput, 3, 1),
        NewTopic(ratingOutput, 3, 1)
    )

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> = DefaultKafkaProducerFactory(
        mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServerAddress,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
        )
    )

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }

}
