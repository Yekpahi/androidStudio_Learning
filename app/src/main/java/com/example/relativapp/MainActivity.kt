package com.example.relativapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        //val error = findViewById<TextView>(R.id.error)
        
        connect.setOnClickListener {
            val txtEmail = email.text.toString()
            val txtPassword = password.text.toString()
            if (txtEmail.trim().isEmpty() || txtPassword.trim().isEmpty()) {
                Toast.makeText(this, "Vous devez remplir tous les champs",  Toast.LENGTH_SHORT).
                show()
            }
            else  {
                val correctEmail = "prince@gmail.com"
                val correctPassword = "azerty"
                if (correctEmail == txtEmail && correctPassword == txtPassword) {
                    Toast.makeText(this, "Bravo vous pouvez maintenant utiliser Spyfee", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}