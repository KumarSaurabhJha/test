package com.kumar.test.domain.usecase

import com.kumar.test.data.repository.PhotoRepository
import com.kumar.test.domain.model.PhotoDomainModel

class GetPicturesListUseCase(
    private val photoRepository: PhotoRepository
) : NoInputUseCase<List<PhotoDomainModel>> {

    override suspend fun execute(): List<PhotoDomainModel> = photoRepository.getAll()
}