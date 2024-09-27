package com.br.taximeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaximeApiApplication

fun main(args: Array<String>) {
    runApplication<TaximeApiApplication>(*args)
}
