package com.br.com.br.taximeapi.application.usecase

abstract class InputUseCase<INPUT> {
    abstract fun execute(input: INPUT)
}
