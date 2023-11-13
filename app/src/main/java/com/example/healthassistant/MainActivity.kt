package com.example.healthassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonLogin: Button = findViewById(R.id.botonLogin)
        val botonRegistro: Button = findViewById(R.id.botonRegistro)
        val textDescripcion: TextView = findViewById(R.id.textViewDescripcion)

        botonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        botonRegistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val originalText =
            "Health Asistant es la aplicacion que te ayudara a llevar un registro de tu salud y bienestar de manera fácil y efectiva."
        val spannableText = SpannableStringBuilder(originalText)
        textDescripcion.text = spannableText

        val delayMillis: Long = 100 // Ajusta este valor según tus preferencias
        val textColor = resources.getColor(android.R.color.black) // Ajusta el color del texto

        val handler = Handler()
        val runnable = Runnable {
            animateText(spannableText, 0, textColor, textDescripcion)
        }

        textDescripcion.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                textDescripcion.viewTreeObserver.removeOnPreDrawListener(this)
                handler.postDelayed(runnable, delayMillis)
                return true
            }
        })
    }

    private fun animateText(text: SpannableStringBuilder, index: Int, textColor: Int, textDescripcion: TextView) {
        if (index < text.length) {
            text.setSpan(ForegroundColorSpan(textColor), index, index + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

            runOnUiThread {
                // Actualizar la interfaz de usuario en el hilo principal
                textDescripcion.text = text
            }

            val delayMillis: Long = 100 // Ajusta este valor según tus preferencias
            Handler().postDelayed({ animateText(text, index + 1, textColor, textDescripcion) }, delayMillis)
        }
    }
}
