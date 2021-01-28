package com.example.hometaskl18

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.hometaskl18.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val LOG_TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnClicker.setOnClickListener {
            bitMapColoredPicFun()
        }
        binding.btnUrlLoad.setOnClickListener {
            showGlide()
        }
    }

    private fun showGlide() {
        val link = "https://i.kym-cdn.com/entries/icons/mobile/000/001/420/977.jpg"
        Glide.with(this)
            .load(link)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .transition(DrawableTransitionOptions.withCrossFade(1000))
            .into(binding.imageGlide)
    }

    private fun bitMapColoredPicFun() {
        val testImage = binding.imageBit.drawable as BitmapDrawable
        val bmp = testImage.bitmap.copy(Bitmap.Config.RGB_565, true)
        val canvas = Canvas(bmp)
        val paint = Paint()
        paint.color = Color.BLUE
        val strokeWidthForPaint = 100F
        paint.strokeWidth = strokeWidthForPaint
        val startX = 0f
        val startY = 0f
        val stopX = 1000f
        val stopY = 1000f
        canvas.drawLine(startX, startY, stopX, stopY, paint)
        binding.imageBit.setImageBitmap(bmp)
        val byteArray = getByteArrayFromBitmap(bmp)

        Log.d(LOG_TAG, java.util.Arrays.toString(byteArray))
    }

    private fun getByteArrayFromBitmap(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        return stream.toByteArray()
    }

}