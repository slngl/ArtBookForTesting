package com.slngl.artbookfortesting.repository

import androidx.lifecycle.LiveData
import com.slngl.artbookfortesting.db.Art
import com.slngl.artbookfortesting.model.ImageResponse
import com.slngl.artbookfortesting.util.Resource

interface IArtRepository {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt(): LiveData<List<Art>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}