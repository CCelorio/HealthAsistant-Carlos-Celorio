package com.example.healthassistant.Eleccion.SeguimientoFisico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.healthassistant.R

class HorasSuenoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horas_sueno)

        val nombreUsuario = intent.getStringExtra("nombreUsuario")

        // Verificar si el nombre de usuario no es nulo y actualizar el TextView
        if (nombreUsuario != null) {
            val nombreUsuarioTextView = findViewById<TextView>(R.id.nombreUsuarioTextView)
            nombreUsuarioTextView.text = "Usuario: $nombreUsuario"
        }
    }
}