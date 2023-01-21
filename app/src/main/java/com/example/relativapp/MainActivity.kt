package com.example.relativapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
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