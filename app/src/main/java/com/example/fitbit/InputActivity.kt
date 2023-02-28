package com.example.fitbit

import android.os.Bundle
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InputActivity : AppCompatActivity() {
    private lateinit var sleepTypeTextView: EditText
    private lateinit var sleepHrsTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_item)


        sleepTypeTextView = findViewById(R.id.SleepAmountInput)
        sleepHrsTextView = findViewById(R.id.SleepTypeInput)

        var sleeptypetext: String = sleepTypeTextView.text.toString()
        var sleephrsnum: String = sleepTypeTextView.text.toString()


        lifecycleScope.launch(Dispatchers.IO) {
            (application as InputApplication).db.inputDao().insert(
                InputEntity(
                    sleepType = sleeptypetext,
                    sleepHours = sleephrsnum,
                )
            )}
    }
}