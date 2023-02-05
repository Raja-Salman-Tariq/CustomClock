package com.salman.customclockv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.salman.customclockv2.databinding.ActivityClockTypeSelectionBinding

class ClockTypeSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClockTypeSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClockTypeSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpOptions()
    }

    private fun setUpOptions(){
        binding.clockTypeAnaloge.clClockType.setOnClickListener {
            startActivity(
                Intent(this@ClockTypeSelectionActivity, ClockSelectionActivity::class.java)
                    .also { it.putExtra("Analogue", true) }
            )
        }

        binding.clockTypeDigital.run {
            tvClockType.text="Digital Clock"
            ivClockType.setImageResource(R.drawable.digital)
            clClockType.setOnClickListener {
                startActivity(
                    Intent(this@ClockTypeSelectionActivity, ClockSelectionActivity::class.java)
                        .also { it.putExtra("Analogue", false) }
                )
            }
        }

        binding.clockTypeCustom.run {
            tvClockType.text="Custom Clock"
            ivClockType.setImageResource(R.drawable.custom)

            clClockType.setOnClickListener {
                startActivity(
                    Intent(this@ClockTypeSelectionActivity, CustomClockSetupActivity::class.java)
                )
            }
        }
    }
}