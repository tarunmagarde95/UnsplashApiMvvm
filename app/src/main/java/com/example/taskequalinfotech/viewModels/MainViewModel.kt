package com.example.taskequalinfotech.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskequalinfotech.models.ImageList
import com.example.taskequalinfotech.models.ImageListItem
import com.example.taskequalinfotech.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository,private var page :Int) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getUsers(page)
        }
    }

    val usersList : LiveData<ImageList> get() = repository.quotes

    val images : LiveData<ImageListItem> get() = repository.images


}