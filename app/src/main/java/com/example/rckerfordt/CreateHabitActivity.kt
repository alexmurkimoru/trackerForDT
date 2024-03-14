package com.example.rckerfordt

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.rckerfordt.databinding.ActivityCreateHabitBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CreateHabitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateHabitBinding
    private var priority = -1
    private var habitType: HabitTypes = HabitTypes.USER_TYPE
    private var counts: String? = null
    private var deadline: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            typesRadio.setOnClickListener(::onStartDialog)
            doneButton.setOnClickListener(::onStartIntent)

            periodCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    deadlineSpinner.visibility = View.VISIBLE
                    countsField.visibility = View.VISIBLE
                    periodText.visibility = View.VISIBLE
                } else {
                    deadlineSpinner.visibility = View.GONE
                    countsField.visibility = View.GONE
                    periodText.visibility = View.GONE
                    counts = null
                    deadline = null
                }
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    val priorityString = parent?.getItemAtPosition(position).toString()
                    priority = if (priorityString == "Нет приоритетности") {
                        -1
                    } else {
                        priorityString.toInt()
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            deadlineSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    deadline = parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun onStartIntent(view: View) = with(binding) {
        if (nameField.text.toString() != "" && !(periodCheckBox.isChecked && countsField.text.toString() == "0")) {
            if (periodCheckBox.isChecked){
                counts = countsField.text.toString()
            }
            val intent = Intent()
            intent.putExtra(EXTRA_NAME, nameField.text.toString())
            intent.putExtra(EXTRA_DESCRIPTION, descriptionField.text.toString())
            intent.putExtra(EXTRA_PRIORITY, priority)
            intent.putExtra(EXTRA_TYPE, habitType)
            intent.putExtra(EXTRA_COUNTS, counts)
            intent.putExtra(EXTRA_DEADLINE, deadline)
            setResult(RESULT_OK, intent)
            finish()
        } else {
            val myToast = Toast.makeText(
                this@CreateHabitActivity,
                R.string.warning,
                Toast.LENGTH_SHORT
            )
            myToast.show()
        }
    }

    private fun onStartDialog(view: View) {
        var chosenType: HabitTypes = habitType
        var checkedItem = 0
        var items: Array<String> = emptyArray()
        for (i in HabitTypes.values()) {
            items += i.habit
        }
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_title)
            .setPositiveButton("Ok") { _, _ ->
                val myToast = Toast.makeText(this, items[checkedItem], Toast.LENGTH_SHORT)
                myToast.show()
                habitType = chosenType
                binding.typeField.text = habitType.habit
            }
            .setSingleChoiceItems(items, checkedItem) { _, selectedItemIndex ->
                checkedItem = selectedItemIndex
                chosenType = HabitTypes.values()[selectedItemIndex]
            }
            .setCancelable(false)
            .show()
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PRIORITY = "extra_priority"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_COUNTS = "extra_counts"
        const val EXTRA_DEADLINE = "extra_deadline"
    }
}

