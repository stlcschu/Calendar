package com.example.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.calendar.database.tables.Event
import com.example.calendar.helper.util.LocalDateTimeConverter
import com.example.calendar.sampleData.SampleData
import java.time.LocalDate

@Composable
fun CalendarField(
    events: List<Event>,
    fieldDate: LocalDate,
    backgroundColor: Color = Color.White,
    width: Dp = 50.dp
) {
    // TODO: Fix for not yet sorted events.
    // Probably fix/remove this later once I parse in the Events for only this day
    var eventsOnDay = mutableListOf<Event>()
    events.forEach { event ->
        if(
            fieldDate.compareTo(event.from.toLocalDate()) == 0 ||
            (fieldDate.compareTo(event.from.toLocalDate()) < 0 &&
                    fieldDate.compareTo(event.to.toLocalDate()) > 0)) {
            eventsOnDay.add(event)
        }
    }
    eventsOnDay = eventsOnDay.sortedBy { e -> e.rowIndex }.toMutableList()
    val shortenedEventsOnDay = mutableListOf<Event>()
    for (i in 0 until 5) {
        if (eventsOnDay.size <= i) break
        shortenedEventsOnDay.add(eventsOnDay[i])
    }
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .width(width)
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .height(20.dp)
        ) {
            Text(text = "${fieldDate.dayOfMonth}")
            Text(text = LocalDateTimeConverter().shortenWeekDayName(fieldDate.dayOfWeek))
        }

        LazyColumn {
            items(shortenedEventsOnDay) {
                event -> EventInTile(event = event, fieldDate = fieldDate, width = width)
            }
        }
        if (eventsOnDay.size > 5) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Show more Events",
                modifier = Modifier.height(10.dp)
            )
        }
    }
}

@Composable
fun EventInTile(event: Event, fieldDate: LocalDate, width: Dp) {
    Box(
        modifier = Modifier
            .height(10.dp)
            .background(event.specialEvent.eventColor)
            .width(width)
    ) {
        if (fieldDate.compareTo(event.from.toLocalDate()) == 0 ||
            (fieldDate.compareTo(event.from.toLocalDate()) < 0 &&
            fieldDate.compareTo(event.to.toLocalDate()) > 0)) {
            Text(text = event.eventTitle, fontSize = 1.em)
        }
    }
}

@Preview
@Composable
fun PreviewCalendarFields() {
    val events = SampleData().events
    val localDateNow = LocalDate.now()
    var currentDate = localDateNow
    Row() {
        var color =  if(currentDate.dayOfMonth == localDateNow.dayOfMonth) Color.LightGray
        else if(currentDate.month != localDateNow.month) Color.Gray else Color.White
        CalendarField(events = events, fieldDate = currentDate, backgroundColor = color)
        currentDate = currentDate.plusDays(1)
        color =  if(currentDate.dayOfMonth == localDateNow.dayOfMonth) Color.LightGray
        else if(currentDate.month != localDateNow.month) Color.Gray else Color.White
        CalendarField(events = events, fieldDate = currentDate, backgroundColor = color)
        currentDate = currentDate.plusMonths(1)
        color =  if(currentDate.dayOfMonth == localDateNow.dayOfMonth) Color.LightGray
        else if(currentDate.month != localDateNow.month) Color.Gray else Color.White
        CalendarField(events = events, fieldDate = currentDate, backgroundColor = color)
    }
}