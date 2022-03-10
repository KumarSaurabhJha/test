package com.kumar.test.domain.usecase


interface UseCase<in I, out O> {
    suspend fun execute(input: I): O
}

interface NoInputUseCase<out O> {
    suspend fun execute(): O
}