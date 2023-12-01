package com.example.applibreria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.applibreria.LoginBDSQLite.BDHelper

class PantallaRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_registro)

        //BOTONES
        //boton cancelar
        val btnCancelar: Button = findViewById(R.id.btn_cancelar)
        val btnRegistrar: Button = findViewById(R.id.btn_registro)

        //metodo para guardar usuario nuevo
        btnRegistrar.setOnClickListener{
            val usuarioRe :EditText = findViewById(R.id.registroUsuario)
            val correoRe : EditText = findViewById(R.id.registroCorreo)
            val contraseniaRe : EditText = findViewById(R.id.registroContrasenia)

            val nombre = usuarioRe.text.toString()
            val correo = correoRe.text.toString()
            val contrasenia = contraseniaRe.text.toString()

            val db = BDHelper(this, null)

            db.CrearRegistro(nombre, correo, contrasenia)
            Toast.makeText(this, "SE REGISTRO CORRECTAMENTE", Toast.LENGTH_LONG).show()

            usuarioRe.text.clear()
            correoRe.text.clear()
            contraseniaRe.text.clear()
        }





        //fun Cancelar registro
        btnCancelar.setOnClickListener{
            val principalScreen = Intent(this, PantallaPrincipal:: class.java)
            startActivity(principalScreen)
        }

    }
}