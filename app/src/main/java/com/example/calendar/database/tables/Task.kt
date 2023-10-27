package com.example.calendar.database.tables

import com.example.calendar.enums.EventTaskLength
import java.time.LocalDateTime

data class Task(
    val id: Int = 0,
    val taskTitle: String,
    val description: String,
    val taskLength: EventTaskLength,
    val dueOn: LocalDateTime,
    val repeats: Boolean = false,
    val interval: Int = 0,
    val isCompleted: Boolean = false
)
