package com.salman.customclockv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.salman.customclockv2.databinding.ActivityCustomClockViewBinding
import com.salman.customclockv2.utils.Consts
import com.salman.customclockv2.utils.PrefMgr


class CustomClockViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomClockViewBinding
    private var clockTypeAnalogue : Boolean? = null
//    private var clr : Int? = null
//    private var bgColor : Int? = null
//    private var dateTimeClr : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomClockViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val customAnalogClock = binding.analogClock
        customAnalogClock.setAutoUpdate(true)

        getSettings()
    }

    private fun getSettings(){
        PrefMgr(applicationContext).run{

            binding.name.apply {
                text= getObj("name", "Name").toString()
                setTextColor(getObj(Consts.TEXT_S, 0) as Int)
                val fontVal = getObj("font", R.font.roboto_black) as Int
                typeface = ResourcesCompat.getFont(this@CustomClockViewActivity, fontVal)
            }
            binding.bg.setBackgroundColor(getObj(Consts.BACKGROUND_S, 0) as Int)
            binding.date.setTextColor(getObj(Consts.DATE_TIME_S, 0) as Int)
            clockTypeAnalogue= getObj("type", true) as Boolean
        }
    }
}