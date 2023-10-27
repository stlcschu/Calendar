package com.example.calendar.sampleData

import com.example.calendar.database.tables.Event
import com.example.calendar.database.tables.Task
import com.example.calendar.enums.EventTaskLength
import com.example.calendar.enums.SpecialEvent
import java.time.LocalDateTime

class SampleData {

    val events = listOf<Event>(
        Event(
            eventTitle = "Test",
            eventLength = EventTaskLength.TIME_SPAN,
            description = "",
            from = LocalDateTime.now(),
            to = LocalDateTime.now()
        ),
        Event(
            eventTitle = "Anna Geburtstag",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.now(),
            to = LocalDateTime.now(),
            specialEvent = SpecialEvent.BIRTHDAY
        ),
        Event(
            eventTitle = "Dieter Kino",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.now(),
            to = LocalDateTime.now(),
            specialEvent = SpecialEvent.MEETING
        ),
        Event(
            eventTitle = "Test TEst",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.now(),
            to = LocalDateTime.now()
        ),
        Event(
            eventTitle = "Urlaub Berlin",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.now(),
            to = LocalDateTime.now(),
            specialEvent = SpecialEvent.VACATION
        ),
        Event(
            eventTitle = "Zug fahren",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.now(),
            to = LocalDateTime.now()
        ),
    )

    val upcomingEvents = listOf<Event>(
        Event(
            eventTitle = "Test",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.of(2024, 10, 10, 10, 10),
            to = LocalDateTime.of(2024, 11, 10, 10, 10)
        ),
        Event(
            eventTitle = "Anna Geburtstag",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.of(2023, 10, 25, 23, 10),
            to = LocalDateTime.of(2023, 10, 25, 23, 11)
        ),
        Event(
            eventTitle = "Dieter Kino",
            eventLength = EventTaskLength.ALL_DAY,
            description = "",
            from = LocalDateTime.of(2023, 11, 10, 10, 10),
            to = LocalDateTime.of(2023, 11, 10, 10, 11)
        )
    )

    val tasks = listOf<Task>(
        Task(
            taskTitle = "Rasen maehen",
            description = "",
            taskLength = EventTaskLength.ALL_DAY,
            dueOn = LocalDateTime.now()
        ),
        Task(
            taskTitle = "Test",
            description = "Test",
            taskLength = EventTaskLength.ALL_DAY,
            dueOn = LocalDateTime.now()
        ),
        Task(
            taskTitle = "test test",
            description = "t",
            taskLength = EventTaskLength.ALL_DAY,
            dueOn = LocalDateTime.now()
        ),
        Task(
            taskTitle = "putzen",
            description = "putzen",
            taskLength = EventTaskLength.ALL_DAY,
            dueOn = LocalDateTime.now()
        ),
    )

}