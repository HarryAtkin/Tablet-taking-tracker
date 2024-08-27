package com.example.tablettracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.remove_Edit)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bttn_Login: Button = findViewById(R.id.login)
        bttn_Login.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
        val bttn_Create_Account:Button = findViewById(R.id.create_Account)
        bttn_Create_Account.setOnClickListener(){
            startActivity(Intent(this, Home_Page::class.java))
        }
    }
}