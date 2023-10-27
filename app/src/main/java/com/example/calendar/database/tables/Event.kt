package com.example.calendar.database.tables

import com.example.calendar.enums.EventTaskLength
import com.example.calendar.enums.EventNotification
import com.example.calendar.enums.SpecialEvent
import com.example.cardplayingcalculator.helper.dataclasses.Location
import com.example.cardplayingcalculator.helper.dataclasses.Person
import java.time.LocalDateTime

data class Event(
    val id : Int = 0,
    val eventTitle: String,
    val eventLength: EventTaskLength,
    val from: LocalDateTime,
    val to: LocalDateTime,
    val repeats: Boolean = false,
    val interval: Int = 0,
    val involvedPeople: List<Person> = listOf(),
    val location: Location? = null,
    val notification: Pair<Int, EventNotification>? = null,
    val description: String,
    val specialEvent: SpecialEvent = SpecialEvent.NONE,
    val rowIndex: Int = 0
)
