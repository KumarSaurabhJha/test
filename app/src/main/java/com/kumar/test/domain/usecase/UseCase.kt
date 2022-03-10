package com.kumar.test.domain.usecase


interface UseCase<in I, out O> {
    fun execute(input: I): O
}

interface NoInputUseCase<out O> {
    fun execute(): O
}