package com.example.hse_application

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.RawRes
import com.google.android.material.textfield.TextInputEditText
import java.lang.Math as Math1


class MainActivity : AppCompatActivity() {

    private var postcardImageView: ImageView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val button = findViewById<Button>(R.id.button)

        val image = findViewById<ImageView>(R.id.imageView)

//        postcardImageView = findViewById<ImageView>(R.id.image)
//        val audioPlayer = AudioPlayer1()
//        postcardImageView?.setOnClickListener {
//            audioPlayer.playFromFile(this@MainActivity, R.raw.sound)
//        }
//        val sp = this.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

//        val editor = sp.edit()
//        editor.putInt(" ", 3)
//            .putString(snakeKey, "ssss")
//            .apply()

        button.setOnClickListener {
            changeCmykToRgb()
        }
    }

    fun changeCmykToRgb() {
        val red = findViewById<TextInputEditText>(R.id.red).text
        val green = findViewById<TextInputEditText>(R.id.green).text
        val blue = findViewById<TextInputEditText>(R.id.blue).text

        val cyan = findViewById<TextInputEditText>(R.id.cyan)
        val magenta = findViewById<TextInputEditText>(R.id.magenta)
        val yellow = findViewById<TextInputEditText>(R.id.yellow)
        val black = findViewById<TextInputEditText>(R.id.black)

        val list = rgbToCmyk(red.toString().toInt(), green.toString().toInt(),
            blue.toString().toInt())

        cyan.setText(list[0].toString())
        magenta.setText(list[1].toString())
        yellow.setText(list[2].toString())
        black.setText(list[3].toString())
    }

    private fun rgbToCmyk(red: Int, green: Int, blue: Int): ArrayList<Int> {
        val r = red / 255
        val g = green / 255
        val b = blue / 255

        val k = 1 - maxOf(r, g, b)
        val c = (1 - r - k) / (1 - k)
        val m = (1 - g - k) / (1 - k)
        val y = (1 - b - k) / (1 - k)

        return arrayListOf<Int>(c, m, y, k)
    }

    companion object {
        private const val sharedPreferencesName = "settings"
        const val snakeKey = "snake"
    }

}


