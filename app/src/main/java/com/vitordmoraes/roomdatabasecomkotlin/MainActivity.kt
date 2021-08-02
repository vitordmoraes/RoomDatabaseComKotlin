package com.vitordmoraes.roomdatabasecomkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.vitordmoraes.roomdatabasecomkotlin.database.AppDatabase
import com.vitordmoraes.roomdatabasecomkotlin.database.daos.UserDao
import com.vitordmoraes.roomdatabasecomkotlin.databinding.ActivityMainBinding
import com.vitordmoraes.roomdatabasecomkotlin.databinding.ActivityNewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private lateinit var binding: ActivityMainBinding
private lateinit var database: AppDatabase
private lateinit var userDao: UserDao

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)

        userDao = database.userDao()
    }


    override fun onStart() {
        super.onStart()

        loadTotalUsers()

        binding.button.setOnClickListener{
            val inflate = Intent(this,NewUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadTotalUsers() {
        binding.textView.text = "Carregando..."
        CoroutineScope(Dispatchers.IO).launch {
            val total = userDao.getTotalItems()

            withContext(Dispatchers.Main) {

            binding.textView.text = "Total de usuários: $total"
            }
        }
    }
}