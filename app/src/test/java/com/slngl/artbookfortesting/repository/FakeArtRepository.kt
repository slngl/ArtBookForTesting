package com.slngl.artbookfortesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.slngl.artbookfortesting.db.Art
import com.slngl.artbookfortesting.model.ImageResponse
import com.slngl.artbookfortesting.util.Resource

class FakeArtRepository : IArtRepository {

    private val arts = mutableListOf<Art>()
    private val artsLiveData = MutableLiveData<List<Art>>(arts)

    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshLiveData()
    }

    override suspend fun deleteArt(art: Art) {
        arts.remove(art)
        refreshLiveData()
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(), 0, 0))
    }

    private fun refreshLiveData() {
        artsLiveData.postValue(arts)
    }


}