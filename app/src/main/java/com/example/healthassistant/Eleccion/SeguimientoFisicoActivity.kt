package com.example.healthassistant.Eleccion

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.healthassistant.Eleccion.SeguimientoFisico.CalculoPesoActivity
import com.example.healthassistant.Eleccion.SeguimientoFisico.HorasSuenoActivity
import com.example.healthassistant.MainActivity
import com.example.healthassistant.R

class SeguimientoFisicoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seguimiento_fisico)

        // Recuperar el nombre de usuario del intent
        val nombreUsuario = intent.getStringExtra("nombreUsuario")
        val botonCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        val botonCalcularPeso = findViewById<Button>(R.id.botonCalculoPeso)
        val botonSueno = findViewById<Button>(R.id.botonHorasSueno)
        val botonAtras = findViewById<Button>(R.id.B_atras_Seguimiento)

        // Verificar si el nombre de usuario no es nulo y actualizar el TextView
        if (nombreUsuario != null) {
            val nombreUsuarioTextView = findViewById<TextView>(R.id.nombreUsuarioTextView2)
            nombreUsuarioTextView.text = "Usuario: $nombreUsuario"
        }

        botonCerrarSesion.setOnClickListener {
            mostrarDialogoConfirmacion()
        }

        botonCalcularPeso.setOnClickListener {
            val intent = Intent(this@SeguimientoFisicoActivity, CalculoPesoActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)
        }

        botonSueno.setOnClickListener {
            val intent = Intent(this@SeguimientoFisicoActivity, HorasSuenoActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)
        }

        botonAtras.setOnClickListener {
            val intent = Intent(this@SeguimientoFisicoActivity, EleccionActivity::class.java)
            startActivity(intent)
        }



    }

    private fun mostrarDialogoConfirmacion() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar Sesión")
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")

        builder.setPositiveButton("Sí") { dialog, which ->
            cerrarSesion()
        }

        builder.setNegativeButton("No") { dialog, which ->
            // No hacer nada, el usuario se queda en la actividad actual
        }

        val dialog = builder.create()
        dialog.show()
    }

    // Función para cerrar la sesión y redirigir al MainActivity
    private fun cerrarSesion() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()  // Cierra la actividad actual para evitar que el usuario vuelva atrás.
    }
}