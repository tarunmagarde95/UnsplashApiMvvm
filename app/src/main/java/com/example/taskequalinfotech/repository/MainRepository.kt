package com.example.taskequalinfotech.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskequalinfotech.api.ApiService
import com.example.taskequalinfotech.models.ImageList
import com.example.taskequalinfotech.models.ImageListItem
import com.example.taskequalinfotech.models.UsersList

class MainRepository(private val apiService: ApiService) {

    private val quotesLiveData = MutableLiveData<ImageList>()
    private val imageListItemData = MutableLiveData<ImageListItem>()

    val quotes: LiveData<ImageList> get() = quotesLiveData
    val images: LiveData<ImageListItem> get() = imageListItemData

    suspend fun getUsers(page: Int) {
        val result = apiService.getQuotes(page,10,"nifAqTBPkMdQK7MLQx8f8z8cdgTYBac1ppQDxQbplpY")
        if (result.body() != null) {
            quotesLiveData.postValue(result.body())

        }

    }
}