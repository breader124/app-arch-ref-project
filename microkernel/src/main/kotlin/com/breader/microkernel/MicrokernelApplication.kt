package com.breader.microkernel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MicrokernelApplication

fun main(args: Array<String>) {
    runApplication<MicrokernelApplication>(*args)
}
