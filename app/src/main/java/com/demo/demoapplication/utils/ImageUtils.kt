package com.demo.demoapplication.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ImageUtils(val context: Context) {
    fun saveimage(bitmap: Bitmap,file: File):String{
        try  {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()
        } catch ( e: IOException) {
            e.printStackTrace()
        }
        return getFilePath(file)
    }

    private fun getFilePath(file: File): String {
        var path = ""
        path = file.getPath()
        return path
    }

}