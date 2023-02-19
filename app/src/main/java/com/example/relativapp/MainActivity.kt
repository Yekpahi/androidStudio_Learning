package com.example.relativapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Declarer une variable sharepreferences
    lateinit var sharedPreferences : SharedPreferences
    // Fin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initier la variable sharepreferences
        sharedPreferences = this.getSharedPreferences("app_state", Context.MODE_PRIVATE)
        val emailSharedPreferences = sharedPreferences.getString("email", null)
        val isAuthentificated = sharedPreferences.getBoolean("is_authentificated", false)
        if (isAuthentificated) {
            Intent(this, HomeActivity::class.java).also {
                it.putExtra("email", emailSharedPreferences)
                startActivity(it)
            }
        }
        //fin

        val connect = findViewById<Button>(R.id.connect)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<TextView>(R.id.password)
        val error = findViewById<TextView>(R.id.error)


        connect.setOnClickListener {
            error.visibility = View.GONE
            val txtEmail = email.text.toString()
            val txtPassword = password.text.toString()
            if (txtEmail.trim().isEmpty() || txtPassword.trim().isEmpty()) {
                error.text= "Vous devez remplir tous les champs"
                error.visibility = View.VISIBLE
            }
            else  {
                val correctEmail = "prince@gmail.com"
                val correctPassword = "azerty"
                if (correctEmail == txtEmail && correctPassword == txtPassword) {
                    email.setText("")
                    password.setText("")

                    // Enregistrer dans sharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("is_authentificated", true)
                    editor.putString("email", txtEmail)
                    editor.apply()
                    //fin

                    //Intent Explicite
                    Intent(this, HomeActivity::class.java).also {
                        it.putExtra("email", txtEmail)
                        startActivity(it)
                    }

                } else {
                    error.text = "Email ou password n'est pas correct"
                    error.visibility = View.VISIBLE
                }
            }
        }
    }
}