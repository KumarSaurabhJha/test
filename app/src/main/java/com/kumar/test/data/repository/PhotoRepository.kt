package com.kumar.test.data.repository

import com.kumar.test.data.api.RestApi
import com.kumar.test.data.model.Photo
import com.kumar.test.data.model.PhotoData
import com.kumar.test.domain.model.PhotoDomainModel
import retrofit2.Response
import java.io.IOException

class PhotoRepository(private val restApi: RestApi) : Repository<PhotoDomainModel> {

    override suspend fun get(id: Int): PhotoDomainModel =
        processResponse(restApi.getPicture(id))

    override suspend fun getAll(): List<PhotoDomainModel> =
        processListResponse(restApi.getPictures())


    private fun processResponse(response: Response<Photo>): PhotoDomainModel =
        when {
            response.isSuccessful -> {
                response.body()?.toDomainModel() ?: throw NoSuchElementException()
            }
            else -> throw NoSuchElementException()
        }

    private fun processListResponse(response: Response<PhotoData>): List<PhotoDomainModel> =
        when {
            response.isSuccessful -> {
                val list = response.body()

                list?.toDomainList() ?: emptyList()

            }
            else -> throw IOException()
        }
}

private fun List<Photo>.toDomainList(): List<PhotoDomainModel> {
    val list = mutableListOf<PhotoDomainModel>()
    this.forEach {
        list.add(it.toDomainModel())
    }
    return list
}

private fun Photo.toDomainModel(): PhotoDomainModel =
    PhotoDomainModel(
        thumbnailUrl = this.thumbnailUrl,
        title = this.title,
        url = this.url
    )
