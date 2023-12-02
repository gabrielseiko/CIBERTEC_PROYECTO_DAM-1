package com.example.applibreria
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applibreria.LibreriaREST.LISTADO.ItemViewLibro
import com.example.applibreria.LibreriaREST.LISTADO.LibroAdapter
import com.example.applibreria.LibreriaREST.LISTADO.LibroApi
import com.example.applibreria.LibreriaREST.RetroFitHelper
import com.example.applibreria.LibroDatos
import com.example.applibreria.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListadoLibros : AppCompatActivity() {

    private lateinit var libroAdapter: LibroAdapter
    private val data = ArrayList<ItemViewLibro>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_libros)

        //Llamando a la Api
        val libroApi = RetroFitHelper.getRetrofitInstance().create(LibroApi::class.java)

        //inicializando el recycler view y su adaptador
        val libroRecycler: RecyclerView = findViewById(R.id.recyclerListado)
        libroRecycler.layoutManager = LinearLayoutManager(this)

        //evento onclick
        libroAdapter = LibroAdapter(data, object : LibroAdapter.OnItemClickListener {
            override fun onItemClick(selectedItem: ItemViewLibro) {
                showLibroDatoScreen(selectedItem)
            }
        })

        libroRecycler.adapter = libroAdapter



        //REALIZAMOS LA SOLICUTUD DE LA API WEB
        GlobalScope.launch {
            val libroResponse = libroApi.getLibros()

            if(libroResponse != null && libroResponse.isSuccessful){
                val responseBody = libroResponse.body()

                //verifica si la respuesta no es nula
                if(responseBody != null && responseBody.books.isNotEmpty()){
                    // Agregando todos los libros
                    for (book in responseBody.books) {
                        data.add(ItemViewLibro(book.title, book.subtitle, book.price, book.image))
                    }
                    // Actualizar el RecyclerView en el hilo principal
                    runOnUiThread {
                        libroAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
        private fun showLibroDatoScreen(selectedItem: ItemViewLibro){
                val intent = Intent(this, LibroDatos::class.java)
                intent.putExtra("libroTitulo", selectedItem.titule)
                intent.putExtra("subtitulo", selectedItem.subtitule)
                intent.putExtra("price", selectedItem.price)
                intent.putExtra("imagen", selectedItem.image)
            startActivity(intent)
        }
}
