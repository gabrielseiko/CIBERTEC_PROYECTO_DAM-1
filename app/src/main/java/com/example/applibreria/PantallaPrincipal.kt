package com.example.applibreria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.applibreria.LoginBDSQLite.BDHelper

class PantallaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        //BOTONNES
        val btnRegistro:TextView = findViewById(R.id.txt_registro)
        val imputUsuario: EditText = findViewById(R.id.loginUsuario)
        val impitContrasenia : EditText = findViewById(R.id.loginContrasenia)
        val btnIngresar : Button = findViewById(R.id.btn_ingresar)

        //metodo para Logueo
        btnIngresar.setOnClickListener{
            val correo = imputUsuario.text.toString()
            val contrasenia = impitContrasenia.text.toString()

            val bd = BDHelper(this , null)
            val cursor = bd.Acceder(correo, contrasenia)

            if(cursor!!.count <= 0){
                Toast.makeText(this, "Usuario y/o Contrasenia incorrectas", Toast.LENGTH_LONG).show()
            } else {
                cursor!!.moveToFirst()
                val usuarioIndex = cursor.getColumnIndex("CORREO");
                val usuario = cursor.getString(usuarioIndex)

                val menuScreen = Intent(this, PantallaMenu::class.java)
                startActivity(menuScreen)

            }

        }


        //FUNCION PARA IR A LA VISTA DE REGISTRO
        btnRegistro.setOnClickListener{
            val registroScreen = Intent(this, PantallaRegistro:: class.java)
            startActivity(registroScreen)
        }
    }
}