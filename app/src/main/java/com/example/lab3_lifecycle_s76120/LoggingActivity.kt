package com.example.lab3_lifecycle_s76120

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class LoggingActivity : AppCompatActivity() {
    private val tag = "LC_${this::class.java.simpleName}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate (savedState=${savedInstanceState != null})")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy (isFinishing=$isFinishing, changingConfig=$isChangingConfigurations)")
    }
}