package com.example.calendar.helper.util

import com.example.calendar.database.tables.Event
import com.example.calendar.database.tables.Task
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters

fun DayOfWeek.dayOfMonth(dateTime: LocalDate = LocalDate.now()) : Int {
    val firstDateOfWeek = dateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    val dateOfDayOfWeek = firstDateOfWeek.with(TemporalAdjusters.nextOrSame(this))
    return dateOfDayOfWeek.dayOfMonth
}

fun getEventsForDate(datetime: LocalDateTime, events: List<Event>) : List<Event> {
    if (events.isEmpty()) return events
    val eventsForDate = mutableListOf<Event>()
    for (event in events) {
        if (datetime.compareTo(event.to) < 0) continue
        eventsForDate.add(event)
    }
    return eventsForDate
}

fun getTasksForDate(datetime: LocalDateTime, tasks: List<Task>) : List<Task> {
    if (tasks.isEmpty()) return tasks
    val tasksForDate = mutableListOf<Task>()
    for (task in tasks) {
        if (datetime.compareTo(task.dueOn) < 0) continue
        tasksForDate.add(task)
    }
    return tasksForDate
}

