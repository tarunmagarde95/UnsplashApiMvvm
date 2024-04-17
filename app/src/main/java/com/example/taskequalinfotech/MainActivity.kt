package com.example.taskequalinfotech

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskequalinfotech.adapter.CustomAdapter
import com.example.taskequalinfotech.api.ApiService
import com.example.taskequalinfotech.api.RetrofitHelper
import com.example.taskequalinfotech.models.ImageListItem
import com.example.taskequalinfotech.repository.MainRepository
import com.example.taskequalinfotech.viewModels.MainViewModel
import com.example.taskequalinfotech.viewModels.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var imageListItem: ArrayList<ImageListItem>
    var mlist = arrayListOf<String>()
    lateinit var nestedScrollV: NestedScrollView
    lateinit var idPBLoading: ProgressBar
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repository = MainRepository(apiService)

        nestedScrollV = findViewById<NestedScrollView>(R.id.idNestedSV)
        idPBLoading = findViewById<ProgressBar>(R.id.idPBLoading)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository, page)
        ).get(MainViewModel::class.java)

        mainViewModel.usersList.observe(this, Observer {
            Log.d("newJson>>", it.toString())

            for (obj in it) {
                val id = obj.urls.regular

                val arrayOfObjects = arrayOf(id)
                println("ID>>>>: ${id}")
                mlist.addAll(arrayOfObjects)
            }
            Log.d("mList", "" + mlist.size)

            val recyclerview = findViewById<RecyclerView>(R.id.idRVUsers)

            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager(this)

            // This will pass the ArrayList to our Adapter
            val adapter = CustomAdapter(mlist)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter

            nestedScrollV.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    page++
                    idPBLoading.setVisibility(View.VISIBLE)
                    mainViewModel.viewModelScope.launch {
                        repository.getUsers(page)
                        adapter.notifyDataSetChanged()
                    }
                    
                }
            })

        })


    }


}