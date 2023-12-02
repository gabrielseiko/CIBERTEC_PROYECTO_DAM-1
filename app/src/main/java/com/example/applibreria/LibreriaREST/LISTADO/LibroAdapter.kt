package com.example.applibreria.LibreriaREST.LISTADO

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applibreria.R
class LibroAdapter(
    private var books: List<ItemViewLibro> ,
    private val itemClickListener: OnItemClickListener
): RecyclerView.Adapter<LibroAdapter.ViewHolder>() {


        // Método para establecer los datos en el adaptador
        fun setLibros(libros: List<ItemViewLibro>){
             this.books = libros
             notifyDataSetChanged()
        }
        //PARA VISUALIZAR LOS LIBROS DE MANERA INDIVIDUAL
        class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
            val nomTitulo : TextView = ItemView.findViewById(R.id.titulo)
        }
         //LLENAMOS LA VISTA LLAMADA item_libro
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_libro, parent, false)
            return ViewHolder(view)
        }
        //LLENAMOS CON POR LA CANTIDAD DE LIBROS DE LA LISTA
        override fun getItemCount(): Int {
            return books.size
        }

        //LLENAMOS CON LOS DATOS DE LOS LIBROS
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val itemViewModel = books[position]
            holder.nomTitulo.text = itemViewModel.titule

            holder.itemView.setOnClickListener{
                itemClickListener.onItemClick(itemViewModel)
            }
        }


        interface OnItemClickListener {
            fun onItemClick(selectedItem: ItemViewLibro )
        }

}