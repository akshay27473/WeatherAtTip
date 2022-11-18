package com.example.weatherattip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.sign_up_window.view.*

class LoginSignup : AppCompatActivity() {
    var sharedPreference:SharedPreference? = null

    lateinit var edt_email: EditText
    lateinit var edt_password: EditText
    lateinit var btn_login: Button
    lateinit var btn_sign_up: Button
    lateinit var btn_frgt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)
        sharedPreference =SharedPreference(this)
        edt_email = findViewById(R.id.edt_email)
        edt_password = findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
        btn_sign_up = findViewById(R.id.btn_sign_up)
        btn_frgt=findViewById(R.id.forget)

        val str_login_status = sharedPreference!!.getPreferenceString("login_status")
        if (str_login_status!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_login.setOnClickListener {
            val str_email_id = edt_email.text.toString()
            val str_password = edt_password.text.toString()

            if(str_email_id.equals("") || str_password.equals("")){
                Toast.makeText(this,"Please Enter Details.", Toast.LENGTH_SHORT).show()
            }else {
                val email_id = sharedPreference!!.getPreferenceString("email_id")
                val password = sharedPreference!!.getPreferenceString("password")

                if(email_id.equals(str_email_id) && password.equals(str_password)){
                    sharedPreference!!.save_String("login_status","1")

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else {
                    Toast.makeText(this,"User Not Available.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btn_sign_up.setOnClickListener {
            fun_SignUp_PopupWindow()
        }

        //write forget password
        btn_frgt.setOnClickListener {
            fun_update_pwd()
        }
    }
    private fun fun_SignUp_PopupWindow() {
        val layoutInflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popView: View = layoutInflater.inflate(R.layout.sign_up_window, null)
        val popupWindow: PopupWindow
        popupWindow = PopupWindow(popView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT,true)
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0)

        val btn_dia_submit = popView.findViewById(R.id.btn_dia_submit) as Button
        btn_dia_submit.setOnClickListener {

            val str_dia_email_id = popView.edt_dia_email_id.text.toString()
            val str_dia_password = popView.edt_dia_password.text.toString()

            if(str_dia_email_id.equals("") || str_dia_password.equals("")){
                Toast.makeText(this,"Please Enter Details.",Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(str_dia_email_id).matches()){
                Toast.makeText(this,"Please Enter Valid Email",Toast.LENGTH_SHORT).show()

            }
            else if(str_dia_password.length<6){
                Toast.makeText(this,"Please Enter Password of 6 0r more length",Toast.LENGTH_SHORT).show()
            }
            else {
                popupWindow.dismiss()
                sharedPreference!!.save_String("email_id",str_dia_email_id)
                sharedPreference!!.save_String("password",str_dia_password)
                Toast.makeText(this,"Data Saved Successfully.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun fun_update_pwd() {
        val layoutInflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popView: View = layoutInflater.inflate(R.layout.forget_pwd, null)
        val popupWindow: PopupWindow
        popupWindow = PopupWindow(popView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT,true)
        popupWindow.showAtLocation(popView, Gravity.CENTER, 0, 0)

        val btn_dia_submit = popView.findViewById(R.id.btn_dia_submit) as Button
        btn_dia_submit.setOnClickListener {

            val str_dia_email_id = popView.edt_dia_email_id.text.toString()
            val str_dia_password = popView.edt_dia_password.text.toString()

            if(str_dia_email_id.equals("") || str_dia_password.equals("")){
                Toast.makeText(this,"Please Enter Details.",Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(str_dia_email_id).matches()){
                Toast.makeText(this,"Please Enter Valid Email",Toast.LENGTH_SHORT).show()

            }
            else if(str_dia_password.length<6){
                Toast.makeText(this,"Please Enter Password of 6 0r more length",Toast.LENGTH_SHORT).show()
            }
            else {
                popupWindow.dismiss()
                sharedPreference!!.save_String("email_id",str_dia_email_id)
                sharedPreference!!.save_String("password",str_dia_password)
                Toast.makeText(this,"Data Saved Successfully.",Toast.LENGTH_SHORT).show()
            }
        }
    }

}