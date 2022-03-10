package com.kumar.test.domain.usecase

import com.kumar.test.data.repository.PhotoRepository
import com.kumar.test.domain.model.PhotoDomainModel

class GetPictureUseCase(
    private val photoRepository: PhotoRepository
) : UseCase<Int, PhotoDomainModel> {
    override suspend fun execute(input: Int): PhotoDomainModel = photoRepository.get(input)
}