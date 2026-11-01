package com.example.lab3_lifecycle_s76120

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

// Part C3 & D1: ViewModel dengan SavedStateHandle
class CounterViewModel(private val state: SavedStateHandle) : ViewModel() {
    var count: Int
        get() = state["count"] ?: 0
        set(value) { state["count"] = value }

    init {
        Log.d("LC_VM", "ViewModel created")
    }

    override fun onCleared() {
        Log.d("LC_VM", "ViewModel destroyed")
    }
}

// Part E1: Lifecycle Observer
class LogObserver : DefaultLifecycleObserver {
    override fun onStart(owner: LifecycleOwner) {
        Log.d("LC_OBS", "start")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d("LC_OBS", "stop")
    }
}

class MainActivity : LoggingActivity() {

    private val vm: CounterViewModel by viewModels()

    // Part E2: Flow Ticker
    private fun ticker() = flow {
        var i = 0
        while (true) {
            emit(i++)
            delay(1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tvCount)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDialog = findViewById<Button>(R.id.btnDialog)
        val btnTranslucent = findViewById<Button>(R.id.btnTranslucent)
        val btnSecond = findViewById<Button>(R.id.btnSecond)
        val btnClose = findViewById<Button>(R.id.btnClose)

        // Papar nilai semasa daripada ViewModel
        tv.text = "Count: ${vm.count}"

        // Part D2: Log nilai SharedPreferences semasa onCreate
        Log.d("LC_PREF", "saved=${getSharedPreferences("lab", MODE_PRIVATE).getInt("saved", -1)}")

        // Part E1: Daftar Observer
        lifecycle.addObserver(LogObserver())

        // Part E3: Collect Flow dengan selamat menggunakan repeatOnLifecycle
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ticker().collect { Log.d("LC_FLOW", "tick $it") }
            }
        }

        // Part C: Butang Add
        btnAdd.setOnClickListener {
            vm.count++
            tv.text = "Count: ${vm.count}"
        }

        // Part B1: Butang Open Dialog
        btnDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Dialog")
                .setMessage("Testing Lifecycle Dialog")
                .setPositiveButton("OK", null)
                .show()
        }

        // Part B2: Butang Translucent Screen
        btnTranslucent.setOnClickListener {
            startActivity(Intent(this, TransparentActivity::class.java))
        }

        // Part B3: Butang Second Screen
        btnSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        // Part A5: Butang Close
        btnClose.setOnClickListener {
            finish()
        }
    }

    // Part D2: Simpan nilai ke dalam SharedPreferences semasa onStop
    override fun onStop() {
        super.onStop()
        getSharedPreferences("lab", MODE_PRIVATE).edit().putInt("saved", vm.count).apply()
    }
}