package com.example.applibreria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
class LibroDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libros_datos)

        val tituloDatos: TextView = findViewById(R.id.tituloDatos)
        val subtituloDatos: TextView = findViewById(R.id.subtituloDatos)
        val precioDatos: TextView = findViewById(R.id.precioDatos)
        // Referenciar el ImageView
        val imageView: ImageView = findViewById(R.id.imgUrl)

        //obtener datos del intent
        val libTitulo = intent.getStringExtra("libroTitulo")
        val libSub = intent.getStringExtra("subtitulo")
        val libPrecio = intent.getStringExtra("price")
        // Obtener la URL de la imagen desde el Intent
        val imgUrl = intent.getStringExtra("imagen")

        //configurar los textview
        tituloDatos.text = libTitulo
        subtituloDatos.text = libSub
        precioDatos.text = libPrecio



        // Cargar la imagen con Picasso
        Picasso.get().load(imgUrl).into(imageView)


    }
}
