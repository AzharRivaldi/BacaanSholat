package com.azhar.bacaansholat.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.bacaansholat.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        linearNiatShalat.setOnClickListener(this)
        linearBacaanShalat.setOnClickListener(this)
        linearAyatKursi.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.linearNiatShalat -> {
                val intentNiat = Intent(this@MainActivity, NiatShalatActivity::class.java)
                startActivity(intentNiat)
            }
            R.id.linearBacaanShalat -> {
                val intentBacaan = Intent(this@MainActivity, BacaanShalatActivity::class.java)
                startActivity(intentBacaan)
            }
            R.id.linearAyatKursi -> {
                val intentAyatKursi = Intent(this@MainActivity, AyatKursiActivity::class.java)
                startActivity(intentAyatKursi)
            }
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val activityWindow = activity.window
            val layoutParams = activityWindow.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            activityWindow.attributes = layoutParams
        }
    }

}