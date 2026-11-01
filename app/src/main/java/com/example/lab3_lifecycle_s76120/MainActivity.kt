package com.example.lab3_lifecycle_s76120

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : LoggingActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnPlus = findViewById<Button>(R.id.btnOnPlus)
        val btnDialog = findViewById<Button>(R.id.btnOnDialog)
        val btnSecond = findViewById<Button>(R.id.btnOnSecond)
        val btnTransparent = findViewById<Button>(R.id.btnOnTransparent)
        val btnClose = findViewById<Button>(R.id.btnOnFinish)

        btnPlus.setOnClickListener {
            count ++
            tvCount.text = "Count : ${count}"
        }

        btnDialog.setOnClickListener {
            AlertDialog.Builder(this).setTitle("Dialog")
                .setMessage("See Logcat Area").setPositiveButton("OK", null).show()
        }

        btnSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btnTransparent.setOnClickListener {
            val intent = Intent(this, TransparentActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            finish()
        }

    }


}