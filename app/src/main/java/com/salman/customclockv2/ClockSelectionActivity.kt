package com.salman.customclockv2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salman.customclockv2.databinding.ActivityClockSelectionBinding


class ClockSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClockSelectionBinding
    private lateinit var rv : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockSelectionBinding.inflate(layoutInflater)
        rv =  binding.myRv
        rv.layoutManager = GridLayoutManager(this, 2)
        rv.adapter = ClockListAdapter(this)
        binding.btnDoneClockSelection.setOnClickListener { doneBtnClick() }
        setContentView(binding.root)

        if (intent.getBooleanExtra("Analogue", true))
            showAnalogueClocks()
        else showDigitalClocks()
    }

    private fun showAnalogueClocks(){
        val id = R.drawable.analog_clock
        (rv.adapter as ClockListAdapter).setData(List(25){id})
    }

    private fun showDigitalClocks(){
        val id = R.drawable.digital_clock
        (rv.adapter as ClockListAdapter).setData(List(25){id})
    }

    private fun doneBtnClick(){
        setResult(Activity.RESULT_OK, Intent().putExtra("idx", (rv.adapter as ClockListAdapter).selection))
        finish()
    }
}