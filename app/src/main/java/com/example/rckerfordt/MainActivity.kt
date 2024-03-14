package com.example.rckerfordt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rckerfordt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val myAdapter = HabitsAdapter()
    private val contract = registerForActivityResult(CreateHabitContract()){
        myAdapter.addHabit(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.habitsRecycler.adapter = myAdapter
        binding.fab.setOnClickListener{
            contract.launch(Unit)
        }
    }
}