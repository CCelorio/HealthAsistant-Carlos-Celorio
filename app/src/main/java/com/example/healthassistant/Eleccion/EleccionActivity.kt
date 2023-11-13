package com.example.healthassistant.Eleccion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.healthassistant.Eleccion.Notificaciones.NotificacionesActivity
import com.example.healthassistant.Eleccion.SaludMental.SaludMentalActivity
import com.example.healthassistant.MainActivity
import com.example.healthassistant.R

class EleccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eleccion)

        // Recuperar el nombre de usuario del intent
        val nombreUsuario = intent.getStringExtra("nombreUsuario")

        // Verificar si el nombre de usuario no es nulo y actualizar el TextView
        if (nombreUsuario != null) {
            val nombreUsuarioTextView = findViewById<TextView>(R.id.nombreUsuarioTextView)
            nombreUsuarioTextView.text = "Usuario: $nombreUsuario"
        }

        val botonSeguimientoFisico= findViewById<Button>(R.id.btnSeguimientoFisico)
        val botonSaludMental= findViewById<Button>(R.id.btnSaludMental)
        val botonNotificaciones= findViewById<Button>(R.id.btnNotificaciones)
        val botonCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Establecer un clic del botón "Cerrar Sesión"
        botonCerrarSesion.setOnClickListener {
            mostrarDialogoConfirmacion()
        }

        botonSeguimientoFisico.setOnClickListener {

            val intent = Intent(this@EleccionActivity, SeguimientoFisicoActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)

        }

        botonSaludMental.setOnClickListener {

            val intent = Intent(this@EleccionActivity, SaludMentalActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)

        }

        botonNotificaciones.setOnClickListener {

            val intent = Intent(this@EleccionActivity, NotificacionesActivity::class.java)
            intent.putExtra("nombreUsuario", nombreUsuario)
            startActivity(intent)

        }


        // ...



    }

    // Función para mostrar el cuadro de diálogo de confirmación
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


