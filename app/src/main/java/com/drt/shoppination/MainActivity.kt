package com.drt.shoppination

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    private var joinNowButton: Button? = null
    private var loginButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        joinNowButton = findViewById<Button>(R.id.btnLandingPage) as Button
        loginButton = findViewById<Button>(R.id.btn_Login) as Button


        joinNowButton?.setOnClickListener {


            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        loginButton?.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }
    }
}
