package com.example.tablettracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val login_btn: Button = findViewById(R.id.login)
        login_btn.setOnClickListener(){
            startActivity(Intent(this,Home_Page::class.java))
        }
        val sign_up_btn: Button = findViewById(R.id.sign_Up)
        sign_up_btn.setOnClickListener(){
            startActivity(Intent(this,Sign_up::class.java))
        }
    }
}