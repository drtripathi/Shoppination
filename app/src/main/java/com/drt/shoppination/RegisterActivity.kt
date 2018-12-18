package com.drt.shoppination

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private var createAccountButton: Button? = null
    private var inputName: EditText? = null
    private var inputNumberPhone: EditText? = null
    private var inputPassword: EditText? = null
    var loadingBar: ProgressDialog?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        createAccountButton = findViewById<Button>(R.id.registerButton) as Button
        inputName = findViewById<EditText>(R.id.register_username_input) as EditText
        inputNumberPhone = findViewById<EditText>(R.id.register_phone_number_input) as EditText
        inputPassword = findViewById<EditText>(R.id.register_password_input) as EditText
       // loadingBar = AlertDialog.Builder(this@RegisterActivity).create()
        loadingBar = ProgressDialog(this)



        createAccountButton?.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount(){
        var name: String? = inputName?.text.toString()
        var phoneNumber: String? = inputNumberPhone?.text.toString()
        var password: String? = inputPassword?.text.toString()

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please input your name", Toast.LENGTH_LONG).show()
        }
        else if(TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this, "Please input your phoneNumber", Toast.LENGTH_LONG).show()
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please input your password", Toast.LENGTH_LONG).show()
        }
        else{
            loadingBar?.setTitle("Create Account")
            loadingBar?.setMessage("Please Wait while your account is getting created!!!")
            loadingBar?.setCancelable(false)
            loadingBar?.show()
            validatePhoneNumber(name!!, phoneNumber!!, password!!)

        }

    }

    private fun validatePhoneNumber(uname: String,phoneNumber: String,password: String){

        val rootRef: DatabaseReference? = FirebaseDatabase.getInstance().reference
        rootRef?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(!(p0.child("Users").child(phoneNumber).exists())) {

                    val userDataMap: HashMap<String, String>? = HashMap<String,String>()
                    userDataMap?.put("name", uname)
                    userDataMap?.put("phoneNumber", phoneNumber)
                    userDataMap?.put("password", password)

                    rootRef?.child("Users").child(phoneNumber).updateChildren(userDataMap as Map<String, String>).
                            addOnCompleteListener { task ->
                                if(task.isSuccessful) {
                                    Toast.makeText(
                                        this@RegisterActivity, "Your account has been created!!", Toast.LENGTH_LONG
                                    ).show()
                                    loadingBar?.dismiss()
                                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                                }
                                else{
                                    loadingBar?.dismiss()
                                    Toast.makeText(
                                        this@RegisterActivity, "Network Error!!", Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                }
                else {
                    Toast.makeText(this@RegisterActivity, "This" + phoneNumber +
                    "already exists", Toast.LENGTH_LONG).show()
                    loadingBar?.dismiss()
                    Toast.makeText(this@RegisterActivity, "Please try using another phone number", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                }
            }
        })
    }
}
