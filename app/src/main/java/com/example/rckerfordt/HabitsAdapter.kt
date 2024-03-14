package com.example.rckerfordt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rckerfordt.databinding.HabitItemBinding

class HabitsAdapter : RecyclerView.Adapter<HabitsAdapter.HabitHolder>() {
    private val habitsList = ArrayList<Habit>()

    class HabitHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HabitItemBinding.bind(item)

        fun bind(habit: Habit) = with(binding) {
            habitName.text = habit.name
            habitDescription.text = habit.description
            times.text = ""
            deadline.text = ""
            iconPic.setImageResource(habit.type.res)
            habit.countOfTimes?.let {
                times.text = "0/$it"
            }
            habit.deadline?.let{
                deadline.text = "за $it"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habit_item, parent, false)
        return HabitHolder(view)
    }

    override fun onBindViewHolder(holder: HabitHolder, position: Int) {
        holder.bind(habitsList[position])
    }

    override fun getItemCount() = habitsList.size

    fun addHabit(habit: Habit){
        if (habit.priority == -1 || habitsList.size == 0) {
            habitsList.add(habit)
            notifyItemInserted(habitsList.size-1)
        }else{
            for(i in habitsList.indices){
                if (habitsList[i].priority > habit.priority || habitsList[i].priority == -1){
                    habitsList.add(i, habit)
                    break
                }
            }
            notifyDataSetChanged()
        }
    }}

