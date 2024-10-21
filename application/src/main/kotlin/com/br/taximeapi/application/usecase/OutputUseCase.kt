package com.br.taximeapi.application.usecase

abstract class OutputUseCase<OUTPUT> {
    abstract fun execute(): OUTPUT
}
