package io.softwarestrategies.projectk.ui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UiServerApplication

fun main(args: Array<String>) {
    runApplication<UiServerApplication>(*args)
}
