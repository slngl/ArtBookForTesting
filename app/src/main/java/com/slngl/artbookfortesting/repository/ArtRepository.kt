package com.slngl.artbookfortesting.repository

import androidx.lifecycle.LiveData
import com.slngl.artbookfortesting.api.PixabayAPI
import com.slngl.artbookfortesting.db.Art
import com.slngl.artbookfortesting.db.ArtDao
import com.slngl.artbookfortesting.model.ImageResponse
import com.slngl.artbookfortesting.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val pixabayAPI: PixabayAPI
) : IArtRepository {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.imageSearch(imageString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("No data1!", null)
            }else{
                Resource.error("No data2!", null)
            }
        } catch (e:java.lang.Exception){
            Resource.error("No data3!", null)
        }
    }

}