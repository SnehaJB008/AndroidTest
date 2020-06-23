package com.demo.demoapplication.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.demo.demoapplication.R
import com.demo.demoapplication.utils.ImageUtils
import com.demo.demoapplication.utils.PreferenceUtils
import com.demo.demoapplication.utils.Utils
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_user_profile.*


class UserProfileFragment : Fragment() {
    var preferenceUtils = PreferenceUtils ()
    var imageUtil:ImageUtils ? = null
    val REQUEST_GALLERY_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        imageUtil = ImageUtils(activity!!)
        if(!PreferenceUtils().getProfileImage(activity!!).equals("") && !PreferenceUtils().getProfileImage(activity!!)?.isEmpty()!!){
            loadProfile(PreferenceUtils().getProfileImage(activity!!)?:"")
        }
        btnChangePhoto.setOnClickListener {
            getPhoto()
        }
        btnLogout.setOnClickListener {
            preferenceUtils.setEmail(activity as Context,"")
            preferenceUtils.setPassword(activity as Context,"")
            preferenceUtils.setUserLogin(activity as Context,false)
            Utils().launchMainActivity(activity!!, LoginActivity::class.java)
        }
    }

    private fun loadProfile(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_defaut_photo)
            .into(ivUserProfilePic)
    }
    fun getPhoto(){
        Dexter.withActivity(context as Activity)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        val pickPhoto = Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        startActivityForResult(pickPhoto, REQUEST_GALLERY_IMAGE)

                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_GALLERY_IMAGE -> if (resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = data?.data
                loadProfile(imageUri.toString())
                PreferenceUtils().setProfileImage(activity!!,imageUri.toString())
            }
        }
    }
}