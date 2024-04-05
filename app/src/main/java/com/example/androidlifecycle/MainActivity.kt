package com.example.androidlifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.textfield.TextInputEditText

lateinit var result:String
class MainActivity : AppCompatActivity() {

    private lateinit var textInputEditPass: TextInputEditText
    private lateinit var textInputEditEmail: TextInputEditText
    private lateinit var login: Button
    private lateinit var tvValues:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initializeViews()


        if (savedInstanceState != null) {
            checkForSavedValueInBundle(savedInstanceState)
        }


    }



    private fun initializeViews(){
        textInputEditEmail = findViewById(R.id.textInputEditEmail)
        textInputEditPass = findViewById(R.id.textInputEditPass)
        login = findViewById(R.id.login)
        tvValues = findViewById(R.id.tvValues)
        setonClickListeners()
    }

     fun setonClickListeners() {
        login.setOnClickListener {
            val email = textInputEditEmail.text.toString()
            val password = textInputEditPass.text.toString()

            Log.d(TAG, "Inside_setupclicklisteners email = $email")
            Log.d(TAG, "Inside_setupclicklisteners email = $password")

            val result = "${getString(R.string.inputted_values)} \n Email = $email\n Password = $password"
            tvValues.apply {
                this.visibility = View.VISIBLE
                this.text = result
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Inside_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Inside_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Inside_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Inside_onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Inside_onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Inside_onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(result.isEmpty()) return
        outState.putString("result", result)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private fun checkForSavedValueInBundle(savedInstanceState:Bundle?) {

        savedInstanceState.also {
            result = it?.getString("result")?:""
            if(result.isEmpty()){
                tvValues.visibility = View.GONE
            }
            else{
                tvValues.apply {
                    this.visibility = View.VISIBLE
                    text = result
                }
            }
        }
    }

}

