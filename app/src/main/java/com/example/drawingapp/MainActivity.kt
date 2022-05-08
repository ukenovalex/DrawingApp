package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.drawingapp.databinding.ActivityMainBinding
import com.example.drawingapp.databinding.DialogBrushSizeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingDialog: DialogBrushSizeBinding

    private var mImageButtonCurrentPaint: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingDialog = DialogBrushSizeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mImageButtonCurrentPaint = binding.paintColors[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )

        binding.drawingView.setSizeForBrush(20.toFloat())
        binding.ibBrush.setOnClickListener{
            showBrushSizeChooserDialog()
        }
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        val dialogView = bindingDialog.root
        brushDialog.setContentView(dialogView)
        brushDialog.setTitle("Brush size: ")
        bindingDialog.ibSmallBrush.setOnClickListener{
            binding.drawingView.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        bindingDialog.ibMediumBrush.setOnClickListener{
            binding.drawingView.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        bindingDialog.ibLargeBrush.setOnClickListener{
            binding.drawingView.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

    fun paintClicked(view: View) {
        if(view != mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton

            val colorTag = imageButton.tag.toString()
            binding.drawingView.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = view
        }
    }
}