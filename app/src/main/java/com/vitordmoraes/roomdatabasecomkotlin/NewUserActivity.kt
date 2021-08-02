package com.vitordmoraes.roomdatabasecomkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vitordmoraes.roomdatabasecomkotlin.database.AppDatabase
import com.vitordmoraes.roomdatabasecomkotlin.database.daos.UserDao
import com.vitordmoraes.roomdatabasecomkotlin.database.models.User
import com.vitordmoraes.roomdatabasecomkotlin.databinding.ActivityNewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewUserBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
    }

    override fun onStart() {
        super.onStart()

        binding.btnUser.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                val result = saveUser(
                    binding.edtFirstName.text.toString(),
                    binding.edtLastName.text.toString()
                )
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        this@NewUserActivity,
                        if (result) "User saved!"
                        else "Error, cannot save this type of user!",
                        Toast.LENGTH_LONG).show()

                    if (result)
                        finish()
                }

            }

        }
    }

    private suspend fun  saveUser(firsName: String, lastName: String): Boolean {
        if (firsName.isBlank() || firsName.isEmpty())
            return false

        if (lastName.isBlank() || lastName.isEmpty())
            return false

        userDao.insert(User(firsName, lastName))
        return true
    }

}