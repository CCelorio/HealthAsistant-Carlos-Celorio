package com.example.healthassistant.Eleccion.Notificaciones

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.healthassistant.MainActivity
import com.example.healthassistant.R

class NotificacionesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)

        // Recuperar el nombre de usuario del intent
        val nombreUsuario = intent.getStringExtra("nombreUsuario")
        val botonCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        // Verificar si el nombre de usuario no es nulo y actualizar el TextView
        if (nombreUsuario != null) {
            val nombreUsuarioTextView = findViewById<TextView>(R.id.nombreUsuarioTextView4)
            nombreUsuarioTextView.text = "Usuario: $nombreUsuario"
        }

        botonCerrarSesion.setOnClickListener {
            mostrarDialogoConfirmacion()
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