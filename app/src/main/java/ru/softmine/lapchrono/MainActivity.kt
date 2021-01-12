package ru.softmine.lapchrono

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private lateinit var startButton: Button
    private lateinit var lapButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button

    private lateinit var lastLapTextView: TextView

    private var lastLapMills: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lastLapTextView = findViewById(R.id.lastLapTextView)
        setLastLapTime(0L)

        chronometer = findViewById(R.id.chronometer)

        startButton = findViewById(R.id.startButton)
        lapButton = findViewById(R.id.lapButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)

        startButton.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            lastLapMills = chronometer.base

            chronometer.start()
        }

        lapButton.setOnClickListener {
            setLastLapTime(SystemClock.elapsedRealtime() - lastLapMills)
            lastLapMills = SystemClock.elapsedRealtime()
        }

        stopButton.setOnClickListener {
            chronometer.stop()
            setLastLapTime(SystemClock.elapsedRealtime() - lastLapMills)
        }

        resetButton.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            setLastLapTime(0L)
        }
    }

    private fun setLastLapTime(currentLap: Long) {
        val min: Long = currentLap / 1000 / 60
        val sec = (currentLap - 60 * min) / 1000
        val mill: Long  = currentLap % 1000

        lastLapTextView.text = String.format("%02d:%02d.%03d", min, sec, mill)
    }
}