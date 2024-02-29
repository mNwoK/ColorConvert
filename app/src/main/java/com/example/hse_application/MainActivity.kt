package com.example.hse_application

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat
import kotlin.math.min
import kotlin.math.round


class MainActivity : AppCompatActivity() {

    private var postcardImageView: ImageView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.convertButton)




        button.setOnClickListener {
            changeRgbToCmyk()
        }
    }

    private fun changeRgbToCmyk() {
        val red: Double = findViewById<TextInputEditText>(R.id.red).text.toString().toDouble()
        val green: Double = findViewById<TextInputEditText>(R.id.green).text.toString().toDouble()
        val blue: Double = findViewById<TextInputEditText>(R.id.blue).text.toString().toDouble()

        val cyan = findViewById<TextInputEditText>(R.id.cyan)
        val magenta = findViewById<TextInputEditText>(R.id.magenta)
        val yellow = findViewById<TextInputEditText>(R.id.yellow)
        val black = findViewById<TextInputEditText>(R.id.black)

        val list = rgbToCmyk(red, green, blue)

        cyan.setText(list[0].toString())
        magenta.setText(list[1].toString())
        yellow.setText(list[2].toString())
        black.setText(list[3].toString())

        val image = findViewById<ImageView>(R.id.imageView)
        image.setColorFilter(Color.argb(255, red.toInt(), green.toInt(), blue.toInt()))
    }

    private fun rgbToCmyk(red: Double, green: Double, blue: Double): ArrayList<Double> {
        val r = min(red / 255, 1.0)
        val g = min(green / 255, 1.0)
        val b = min(blue / 255, 1.0)

        val df = DecimalFormat("#.##")


        var k = 1 - maxOf(r, g, b)
        val c = df.format((1 - r - k) / (1 - k) * 100).toDouble()
        val m = df.format((1 - g - k) / (1 - k) * 100).toDouble()
        val y = df.format((1 - b - k) / (1 - k) * 100).toDouble()
        k = df.format(k * 100).toDouble()

        val arr = arrayListOf<Double>(c, m, y, k)

        if (arr.any { it.isNaN() }) {
            return arrayListOf<Double>(.0, .0, .0, 100.0)
        }

        return arr
    }

    companion object {
        private const val sharedPreferencesName = "settings"
        const val snakeKey = "snake"
    }

}


