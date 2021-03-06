package com.demo.demoapplication.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.demo.demoapplication.R
import com.demo.demoapplication.module.Photo
import com.demo.demoapplication.utils.Utils
import kotlinx.android.synthetic.main.fragment_item_detail.*
import org.koin.android.ext.android.get

class ItemDetailFragment : Fragment() {
    var viewModel: MainViewModel? = null
    val utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =  get()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(utils.checkInternetConnection(activity!!)){
            getPhotoDetails()
        }else{
            utils.showToast(activity,getString(R.string.connection_err))
        }
    }

    private fun getPhotoDetails() {
        viewModel?.getPhotoDetail(arguments?.getInt("id",0)?:0)?.observe(this, Observer { response ->
            val photoDetail = response as Photo
            tvTitle.text= photoDetail.title

            try {
                Glide.with(context as Activity)
                    .asBitmap()
                    .load(photoDetail.url)
                    .placeholder(R.drawable.posterimg)
                    .into(ivItem)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }

}