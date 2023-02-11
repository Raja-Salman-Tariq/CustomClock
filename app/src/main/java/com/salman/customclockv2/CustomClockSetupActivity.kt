package com.salman.customclockv2

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.salman.customclockv2.databinding.ActivityCustomClockSetupBinding
import com.salman.customclockv2.utils.Consts
import com.salman.customclockv2.utils.PrefMgr
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener


class CustomClockSetupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomClockSetupBinding
    private var clockTypeAnalogue : Boolean? = null
    private var clr : Int? = null
    private var bgColor : Int? = null
    private var dateTimeClr : Int? = null
    private var clrFor = ""
    private lateinit var prefMgr : PrefMgr
    private val clockTypeSelection = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
         { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Handle the result here
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomClockSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefMgr = PrefMgr(applicationContext)

        setUpfontDropDown()

        binding.chipDigital.setOnClickListener {
            (it as Chip).setTextColor(resources.getColor(R.color.black))
            binding.chipAnalogue.setTextColor(resources.getColor(com.salman.customclockv2.R.color.teal_200))
            clockTypeAnalogue = false
            clockTypeSelection.launch(Intent(this, ClockSelectionActivity::class.java).putExtra("Analogue", false))
        }

        binding.chipAnalogue.setOnClickListener {
            (it as Chip).setTextColor(resources.getColor(R.color.black))
            binding.chipDigital.setTextColor(resources.getColor(com.salman.customclockv2.R.color.teal_200))
            clockTypeAnalogue = true
            clockTypeSelection.launch(Intent(this, ClockSelectionActivity::class.java).putExtra("Analogue", true))
        }

        setUpColorPickerViews()

        binding.btnPreview.setOnClickListener {
            saveSettings()
            startActivity(Intent(this, CustomClockViewActivity::class.java))
        }

        binding.btnDone.setOnClickListener {
            saveSettings()
        }
    }

    private fun setUpColorPickerViews(){
        // text color
        binding.cvColor.setCardBackgroundColor(prefMgr.getObj(Consts.TEXT_S, 0) as Int)
        binding.cvColor.setOnClickListener {
            clrFor = Consts.TEXT_S
            showColorPicker()
        }

        // background color
        binding.cvBgColor.setCardBackgroundColor(prefMgr.getObj(Consts.BACKGROUND_S, 0) as Int)
        binding.cvBgColor.setOnClickListener {
            clrFor = Consts.BACKGROUND_S
            showColorPicker()
        }

        // date/time color
        binding.cvDt.setCardBackgroundColor(prefMgr.getObj(Consts.DATE_TIME_S, 0) as Int)
        binding.cvDt.setOnClickListener {
            clrFor = Consts.DATE_TIME_S
            showColorPicker()
        }
    }

    private fun saveSettings(){
        prefMgr.run{
            putObj("name", binding.etName.text.trimStart().trimEnd().run {if (length>0) toString() else "No Name Entered"} )
            clr?.let { putObj(Consts.TEXT_S, it) }
            bgColor?.let { putObj(Consts.BACKGROUND_S, it) }
            dateTimeClr?.let { putObj(Consts.DATE_TIME_S, it) }
            clockTypeAnalogue?.let { putObj("type", it) }
            when (Consts.FONT_TYPES.indexOf(binding.filledExposedDropdown.text.toString())){
                0 -> putObj("font", com.salman.customclockv2.R.font.dancing_script)
                1 -> putObj("font", com.salman.customclockv2.R.font.roboto_black)
            }
        }
    }

    private fun setUpfontDropDown(){
        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_dropdown_item,
            Consts.FONT_TYPES
        )

        binding.filledExposedDropdown.setAdapter(adapter)
    }

    private fun handlePickedColor(color : Int){
        when (clrFor) {
            Consts.TEXT_S -> {
                clr = color
                binding.cvColor.setCardBackgroundColor(clr!!)
            }
            Consts.BACKGROUND_S -> {
                bgColor = color
                binding.cvBgColor.setCardBackgroundColor(bgColor!!)
            }
            Consts.DATE_TIME_S -> {
                dateTimeClr = color
                binding.cvDt.setCardBackgroundColor(dateTimeClr!!)
            }
        }
    }

    private fun showColorPicker(){
        ColorPickerDialog.Builder(this)
            .setPositiveButton("OK",
                ColorEnvelopeListener { envelope, _ -> handlePickedColor(envelope.color) })
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface, _ -> dialogInterface.dismiss() }
            .attachAlphaSlideBar(true) // the default value is true.
            .attachBrightnessSlideBar(true) // the default value is true.
            .show()
    }
}