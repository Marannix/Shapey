package com.example.shapey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewOutlineProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val customView = findViewById<CustomView>(R.id.customView)
        val customDottedView = findViewById<DottedCircle>(R.id.customDottedView)
//
        customDottedView.outlineProvider = ViewOutlineProvider.BACKGROUND
        customDottedView.clipToOutline = true
//        swapColorButton.setOnClickListener {
//            customView.swapColor()
//        }
    }
}
