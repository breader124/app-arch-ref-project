package com.breader.microkernelapp.output

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class TestOutput : Output {

    override fun sendOut() {
        println("Sending out")
    }

    @PostConstruct
    private fun postConstruct() {
        println("${this.javaClass.name} bean has been registered")
    }

}
