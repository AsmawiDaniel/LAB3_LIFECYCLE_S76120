package com.example.lab3_lifecycle_s76120

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

open class LoggingActivity : AppCompatActivity(){
    private val tag get() = "LC " + javaClass.simpleName

    override fun onCreate(
        savedInstanceState: Bundle?,
    ) {
        super.onCreate(savedInstanceState)
        Log.d(tag,"On create (saved state = ${savedInstanceState != null}")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "On Start")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "On Restart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "On Pause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "On Resume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "On Destroy (isFinishing) = $isFinishing, changingConfig = $isChangingConfigurations")
    }
}