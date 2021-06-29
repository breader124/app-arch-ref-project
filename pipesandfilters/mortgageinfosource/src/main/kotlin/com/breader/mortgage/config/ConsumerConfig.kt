package com.breader.mortgage.config

import com.breader.mortgage.source.IncomeDataReq
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class ConsumerConfig(
    @Value("\${kafka.bootstrap.server}") private val bootstrapServerAddress: String
) {

    @Bean
    fun incomeInputConsumerFactory(): ConsumerFactory<String, IncomeDataReq> = DefaultKafkaConsumerFactory(
        mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServerAddress,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
            JsonDeserializer.TYPE_MAPPINGS to "incomeInputReq: com.breader.mortgage.source.IncomeDataReq"
        ),
    )

    @Bean
    fun incomeInputContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, IncomeDataReq> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, IncomeDataReq>()
        factory.consumerFactory = incomeInputConsumerFactory()
        return factory
    }

}
