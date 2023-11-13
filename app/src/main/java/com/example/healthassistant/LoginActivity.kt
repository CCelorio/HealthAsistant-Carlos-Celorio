package com.example.healthassistant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthassistant.Eleccion.EleccionActivity
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val nombreLoginEditText = findViewById<EditText>(R.id.nombreLogin)
        val contraseñaLoginEditText = findViewById<EditText>(R.id.contraseñaRegis3)
        val botonLogin = findViewById<Button>(R.id.boton_Login)

        botonLogin.setOnClickListener {
            val nombreIngresado = nombreLoginEditText.text.toString()
            val contraseñaIngresada = contraseñaLoginEditText.text.toString()

            // Llamar a una tarea para verificar las credenciales
            VerificarCredencialesTask(nombreIngresado).execute(nombreIngresado, contraseñaIngresada)
        }


    }

    private inner class VerificarCredencialesTask (private val nombreUsuario: String) : AsyncTask<String, Void, Boolean>() {
        override fun doInBackground(vararg datos: String): Boolean {
            try {
                Class.forName("com.mysql.jdbc.Driver")
                val conexion: Connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1/health_asistant", "celorio", "celorio")

                val sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?"
                val statement: PreparedStatement = conexion.prepareStatement(sql)
                statement.setString(1, datos[0]) // usuario
                statement.setString(2, datos[1]) // contraseña

                val resultSet = statement.executeQuery()

                // Si hay al menos un resultado, las credenciales son correctas
                if (resultSet.next()) {
                    return true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return false
        }

        override fun onPostExecute(credencialesCorrectas: Boolean) {
            if (credencialesCorrectas) {
                // Credenciales válidas, abrir la actividad de inicio
                val intent = Intent(this@LoginActivity, EleccionActivity::class.java)
                intent.putExtra("nombreUsuario", nombreUsuario)
                startActivity(intent)
            } else {
                // Llamando a la función para mostrar un mensaje de error
                mostrarMensajeDeError(this@LoginActivity, "Has introducido mal los datos.")
            }
        }
    }



    fun mostrarMensajeDeError(context: Context, mensaje: String) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

}