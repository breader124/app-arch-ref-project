package com.breader.mortgageinfosource

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MortgageController(private val template: KafkaTemplate<String, String>) {

    @PostMapping("income")
    fun fetchIncomeData(@RequestBody req: IncomeDataReq) {
        template.send("test-topic", "TEST")
    }

}

data class IncomeDataReq(
    val name: String,
    val salary: String,
    val constantCharges: Long,
    val childrenNum: String
)
