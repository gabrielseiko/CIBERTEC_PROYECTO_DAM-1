package com.example.applibreria


import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class PantallaMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu)

        //BOTONES
        val btnCerrarSesion: Button = findViewById(R.id.cerrarSesion)
        val btnListado: Button = findViewById(R.id.listarLibros)


        //Llamando al Modal Cerrar
        btnCerrarSesion.setOnClickListener{
            val titleMsg: String = "Confirmación"
            val bodyMsg: String = "¿Esta seguro que desea salir de la App?"
            showModalConfirmExit(titleMsg, bodyMsg);
        }
        //llamando al recylcer (listado)
        btnListado.setOnClickListener{
            val listadoScreen = Intent(this, ListadoLibros::class.java)
            startActivity(listadoScreen)
        }

    }



    //modal
    private fun showModalConfirmExit(titleMsg: String, bodyMsg: String) {
        val dialogConfirm = Dialog(this)
        dialogConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogConfirm.setCancelable(false)
        dialogConfirm.setContentView(R.layout.activity_modal_mensager_salir)

        dialogConfirm.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //INICIO

        val titleModal : TextView = dialogConfirm.findViewById(R.id.modalTitle)
        val bodyMsgModal : TextView = dialogConfirm.findViewById(R.id.modalMessage)

        val btnAceptar : Button = dialogConfirm.findViewById(R.id.btnModalAceptar)
        val btnCancelar: Button = dialogConfirm.findViewById(R.id.btnModalCancelar)

        titleModal.text = titleMsg
        bodyMsgModal.text = bodyMsg

        btnAceptar.setOnClickListener {
            val pantallPrincipal = Intent(this, PantallaPrincipal::class.java)
            startActivity(pantallPrincipal)
        }

        btnCancelar.setOnClickListener {
            Toast.makeText(this, "Seguir Navegando", Toast.LENGTH_LONG).show()
            dialogConfirm.dismiss()
        }

        dialogConfirm.show()
    }
}