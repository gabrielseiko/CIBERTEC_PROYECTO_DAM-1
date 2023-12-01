package com.example.applibreria.LoginBDSQLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    //creando BD
companion object{
    private val DATABASE_NAME = "BD_LIBRERIA"
    private val DATABASE_VERSION = 1
    private val TABLA_USUARIO = "USUARIO"
    private val COLUMN_ID = "ID"
    private val COLUMN_NOMBRE = "NOMBRE"
    private val COLUMN_CORREO = "CORREO"
    private val COLUMN_CONTRASENIA = "CONTRASENIA"
}

    //creando tabla
    override fun onCreate(db: SQLiteDatabase) {
        var queryCreateTable =
            (" CREATE TABLE " + TABLA_USUARIO + " ( " +
                               COLUMN_ID + " INT PRIMARY KEY, " +
                               COLUMN_NOMBRE + " TEXT, " +
                               COLUMN_CORREO + " TEXT, " +
                               COLUMN_CONTRASENIA + " TEXT " + " ) "
                    )
        db.execSQL(queryCreateTable)
    }

    //funcion para registrar usuario
    fun CrearRegistro(nombre:String, correo:String, contrasenia:String){
        val values =ContentValues();
        values.put(COLUMN_NOMBRE, nombre)
        values.put(COLUMN_CORREO, correo)
        values.put(COLUMN_CONTRASENIA, contrasenia)
        val db = this.writableDatabase
        db.insert(TABLA_USUARIO, null,values)
        db.close()
    }

    //Funcion para listar todos los registros
    fun listaTodos(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLA_USUARIO, null)
    }

    //funcion para acceder inicio de sesion
    fun Acceder(correo:String, contrasenia: String ): Cursor?{
        val db = this.readableDatabase
        val sql = " SELECT * FROM " + TABLA_USUARIO + " WHERE " + COLUMN_CORREO + " = '" + correo + "' AND " +
                                        COLUMN_CONTRASENIA + " = '" + contrasenia + "' "
        return db.rawQuery(sql, null)
    }

    //?????????????????
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}