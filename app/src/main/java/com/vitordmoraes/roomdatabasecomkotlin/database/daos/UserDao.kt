package com.vitordmoraes.roomdatabasecomkotlin.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vitordmoraes.roomdatabasecomkotlin.database.models.User

@Dao //"Data Acess objective" = responsavel por inserir e remover os itens no database, tipo um adapter
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT COUNT(uid) FROM user")
    suspend fun getTotalItems(): Long
}