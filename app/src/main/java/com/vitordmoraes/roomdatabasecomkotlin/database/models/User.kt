package com.vitordmoraes.roomdatabasecomkotlin.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //Informa que é uma "gaveta" especifica a ser salva na database
data class User (
    @ColumnInfo(name = "first_name")val firstName: String, //nomeclatura de banco de dados ñ utiliza camelcase.
    @ColumnInfo(name = "last_name")val lastName: String    //quando o banco for gerado ele sabe que vai ser com _
    ){
    @PrimaryKey(autoGenerate = true)
    val uid : Int = 0 //informa que o banco tem que ter 1 ID unico, e pra ser autogerado.
}