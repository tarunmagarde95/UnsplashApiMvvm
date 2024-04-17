package com.example.taskequalinfotech.api

import com.example.taskequalinfotech.models.ImageList
import com.example.taskequalinfotech.models.ImageListItem
import com.example.taskequalinfotech.models.UsersList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun getQuotes(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("client_id") client_id: String) : Response<ImageList>

}