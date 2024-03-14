package com.example.rckerfordt

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class CreateHabitContract : ActivityResultContract<Unit, Habit>() {
    override fun createIntent(context: Context, input: Unit) = Intent(context, CreateHabitActivity::class.java)

    override fun parseResult(resultCode: Int, result: Intent?): Habit {
        return Habit(
            result?.getStringExtra(CreateHabitActivity.EXTRA_NAME) ?: "null",
            result?.getStringExtra(CreateHabitActivity.EXTRA_DESCRIPTION) ?: "null",
            result?.getIntExtra(CreateHabitActivity.EXTRA_PRIORITY, -1) ?: -1,
            result?.getSerializableExtra(CreateHabitActivity.EXTRA_TYPE)!! as HabitTypes,
            result?.getStringExtra(CreateHabitActivity.EXTRA_COUNTS)?.toInt(),
            result?.getStringExtra(CreateHabitActivity.EXTRA_DEADLINE)
        )
    }

}