package com.salman.customclockv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.salman.customclockv2.databinding.ActivityClockSelectionBinding

class ClockSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClockSelectionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tv.text= if (intent.getBooleanExtra("Analogue", true)) "Analogue" else "Digital"
    }
}