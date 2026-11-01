// Fail: TransparentActivity.kt
package com.example.lab3_lifecycle_s76120

import android.os.Bundle
import android.widget.Button

class TransparentActivity : LoggingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)

        findViewById<Button>(R.id.btnClose)?.setOnClickListener {
            finish()
        }
    }
}