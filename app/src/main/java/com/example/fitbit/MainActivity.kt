package com.example.fitbit

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitbit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val inputs = mutableListOf<DisplayInput>()
    private lateinit var inputsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        inputsRecyclerView = findViewById(R.id.inputslist)
        val inputAdapter = InputAdapter(inputs)
        inputsRecyclerView.adapter = inputAdapter

        lifecycleScope.launch {
            (application as InputApplication).db.inputDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayInput(
                        entity.sleepType,
                        entity.sleepHours
                    )
                }.also { mappedList ->
                    inputs.clear()
                    inputs.addAll(mappedList)
                    inputAdapter.notifyDataSetChanged()
                }
            }
        }

        inputsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            inputsRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        var inputbutton = findViewById<Button>(R.id.inputbutton)

        inputbutton.setOnClickListener(){
            val intent = Intent(this, InputActivity::class.java)
            this.startActivity(intent)
        }

    }
}