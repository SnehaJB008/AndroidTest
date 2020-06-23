package com.demo.demoapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.demoapplication.module.Photo
import com.demo.demoapplication.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val client: RestClient?): ViewModel() {
    val photosList: MutableLiveData<ArrayList<Photo>> = MutableLiveData()
    val photoDetail : MutableLiveData<Photo> = MutableLiveData()

    fun getData(): LiveData<ArrayList<Photo>?>? {
        client?.getAlbum()?.enqueue(object : Callback<ArrayList<Photo>?> {
            override fun onResponse(
                call: Call<ArrayList<Photo>?>,
                response: Response<ArrayList<Photo>?>
            ) {
                photosList.setValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<Photo>?>, t: Throwable) {

            }
        })
        return photosList
    }

    fun getPhotoDetail(photoId:Int): LiveData<Photo>? {
        client?.getPhotoById(photoId)?.enqueue(object : Callback<Photo?> {
            override fun onResponse(
                call: Call<Photo?>,
                response: Response<Photo?>
            ) {
                photoDetail.setValue(response.body())
            }

            override fun onFailure(call: Call<Photo?>, t: Throwable) {

            }
        })
        return photoDetail
    }
}