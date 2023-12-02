package com.example.applibreria.LibreriaREST.LISTADO

import retrofit2.Response
import retrofit2.http.GET

interface LibroApi {

    @GET("mocks/cb2c84db-433b-4259-bc84-ac6a4e2fb604/book")
    suspend fun getLibros(): Response<LibroList>
}