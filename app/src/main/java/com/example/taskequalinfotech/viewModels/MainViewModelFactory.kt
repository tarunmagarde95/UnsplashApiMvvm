package com.example.taskequalinfotech.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskequalinfotech.repository.MainRepository

class MainViewModelFactory(private val repository: MainRepository,private var page :Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository,page) as T
    }

}