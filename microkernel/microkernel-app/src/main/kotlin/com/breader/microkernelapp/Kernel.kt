package com.breader.microkernelapp

import com.breader.microkernelapp.input.IncomeInfo
import com.breader.microkernelapp.output.Output
import com.breader.microkernelapp.processing.Processor
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Kernel(val processor: Processor, val output: Output) {

    @EventListener
    fun handleInput(input: IncomeInfo) {
        processor.process()
        output.sendOut()
    }

}
