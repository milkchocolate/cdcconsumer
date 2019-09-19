package org.mad.cdcconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CdcconsumerApplication

fun main(args: Array<String>) {
	runApplication<CdcconsumerApplication>(*args)
}
