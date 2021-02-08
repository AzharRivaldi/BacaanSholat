package com.azhar.bacaansholat.activities

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.azhar.bacaansholat.R
import kotlinx.android.synthetic.main.activity_ayat_kursi.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class AyatKursiActivity : AppCompatActivity(), View.OnClickListener {

    var strAyat: String? = null
    var strLatin: String? = null
    var strTerjemahan: String? = null
    var strTafsir: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayat_kursi)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        toolbar.setTitle(null)
        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fabTafsir.setOnClickListener(this)

        getDataAyatKursi()
    }

    private fun getDataAyatKursi() {
        try {
            val stream = assets.open("ayatkursi.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val strResponse = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strResponse)
                val jsonData = jsonObject.getJSONObject("data")
                strAyat = jsonData.getString("arabic")
                strLatin = jsonData.getString("latin")
                strTerjemahan = jsonData.getString("translation")
                strTafsir = jsonData.getString("tafsir")

                txtAyat.text = strAyat
                txtLatin.text = strLatin
                txtTerjemahan.text = "Terjemahan : $strTerjemahan"
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
        }
    }

    override fun onClick(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tafsir Ayat Kursi :")
        builder.setMessage(strTafsir)
        builder.setCancelable(true)

        builder.setPositiveButton("Tutup") {
            dialog, which -> dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()

        val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        button.setTextColor(Color.BLACK)
        button.isAllCaps = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}