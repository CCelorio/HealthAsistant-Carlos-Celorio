package com.example.healthassistant

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val nombreUsuarioEditText = findViewById<EditText>(R.id.nombreUsuario)
        val contraseñaEditText = findViewById<EditText>(R.id.contraseñaRegis3)
        val registroButton = findViewById<Button>(R.id.B_Registro)
        val atrasButton = findViewById<Button>(R.id.B_atras_Seguimiento)

        registroButton.setOnClickListener {
            val nombreUsuario = nombreUsuarioEditText.text.toString()
            val contraseña = contraseñaEditText.text.toString()

            // Llamar a la AsyncTask para insertar los datos
            InsertAsyTask(this).execute(nombreUsuario, contraseña)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        atrasButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}

class InsertAsyTask(context: Context) : AsyncTask<String, Void, Void>() {

    private val context: Context = context

    override fun doInBackground(vararg datos: String): Void? {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            val conexion: Connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1/health_asistant", "celorio", "celorio")

            val sql = "INSERT INTO usuarios (usuario, contraseña) VALUES (?, ?)"
            val statement: PreparedStatement = conexion.prepareStatement(sql)
            statement.setString(1, datos[0]) // usuario
            statement.setString(2, datos[1]) // contraseña

            statement.executeUpdate()

            statement.close()
            conexion.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}

