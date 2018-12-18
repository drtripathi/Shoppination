package com.drt.shoppination

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private var inputNumber: EditText? = null
    private var inputPassword: EditText? = null
    private var loginButton: Button? = null
    var loadingBar: ProgressDialog?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById<Button>(R.id.loginButton) as Button
        inputNumber = findViewById<EditText>(R.id.login_phone_number_input) as EditText
        inputPassword = findViewById<EditText>(R.id.login_password_input) as EditText

        loginButton?.setOnClickListener {
            loginUser()
        }


    }

    private fun loginUser(){
        var phoneNumber: String? = inputNumber?.text.toString()
        var password: String? = inputPassword?.text.toString()

        if(TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this, "Please input your phone number", Toast.LENGTH_LONG).show()
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please input your password", Toast.LENGTH_LONG).show()
        }
        else{
            loadingBar?.setTitle("Create Account")
            loadingBar?.setMessage("Please Wait while your account is getting created!!!")
            loadingBar?.setCancelable(false)
            loadingBar?.show()

            allowAccessToAccount()

        }


    }

    private fun allowAccessToAccount(){

    }
}
