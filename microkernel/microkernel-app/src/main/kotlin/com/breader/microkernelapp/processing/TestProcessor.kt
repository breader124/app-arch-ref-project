package com.breader.microkernelapp.processing

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestProcessor : Processor {

    override fun process() {
        println("Processing")
    }

    @PostConstruct
    private fun postConstruct() {
        println("${this.javaClass.name} bean has been registered")
    }

}
