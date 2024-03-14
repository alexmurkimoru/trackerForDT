package com.example.rckerfordt

import androidx.annotation.ColorRes
import com.example.rckerfordt.HabitTypes

data class Habit(
    val name: String,
    val description: String,
    val priority: Int,
    val type: HabitTypes,
    val countOfTimes: Int? = null,
    val deadline: String? = null
)
