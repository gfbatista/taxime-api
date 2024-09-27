package com.br.com.br.taximeapi.application.usecase

abstract class UseCase<INPUT, OUTPUT> {
    abstract fun execute(input: INPUT): OUTPUT
}
