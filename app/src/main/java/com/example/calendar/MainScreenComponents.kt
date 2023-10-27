package com.example.calendar

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.calendar.database.tables.Event
import com.example.calendar.database.tables.Task
import com.example.calendar.enums.EventTaskLength
import com.example.calendar.helper.util.LocalDateTimeConverter
import com.example.calendar.helper.util.dayOfMonth
import com.example.calendar.sampleData.SampleData
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import kotlin.math.abs

@Composable
fun EventsToday(events: List<Event>, width: Float) {
    Column(
        modifier = Modifier.width(width.dp)
    ) {
        if (events.isEmpty()) {
            Text(text = "Keine Termine heute")
            return
        }
        LazyColumn() {
            items(events) {
                event -> EventTodayRow(event = event)
            }
        }
    }
}

@Composable
fun EventTodayRow(event: Event) {
    Row(
        modifier = Modifier
            .border(width = 1.dp, color = Color.DarkGray)
            .height(intrinsicSize = IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = 0.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
    ) {
        Box(modifier = Modifier
            .width(10.dp)
            .fillMaxHeight()
            .background(color = event.specialEvent.eventColor))
        Column() {
            Text(text = event.eventTitle, fontSize = 2.5.em)

            val eventLengthText = if(event.eventLength == EventTaskLength.ALL_DAY) "Ganztaegig"
            else "${LocalDateTimeConverter().convertToHourMinuteString(event.from)} - ${LocalDateTimeConverter().convertToHourMinuteString(event.to)} Uhr"
            Text(text = eventLengthText, fontSize = 2.em)
        }
    }
}

@Composable
fun TasksToday(tasks: List<Task>, width: Float) {
    Column(
        modifier = Modifier.width(width.dp)
    ) {
        if (tasks.isEmpty()) {
            Text(text = "Keine Tasks heute")
            return
        }
        LazyColumn(
//            modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max)
        ) {
            items(tasks) {
                    task -> TaskTodayRow(task = task)
            }
        }
    }
}

@Composable
fun TaskTodayRow(task: Task) {
    Row(
        modifier = Modifier
            .border(width = 1.dp, color = Color.DarkGray)
            .height(intrinsicSize = IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = 0.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
    ) {
        Box(modifier = Modifier
            .width(10.dp)
            .fillMaxHeight()
            .background(color = if (task.isCompleted) Color.Green else Color.Red)
        )
        Column() {
            Text(text = task.taskTitle, fontSize = 2.5.em)

            val taskLengthText = if(task.taskLength == EventTaskLength.ALL_DAY) "Ganztaegig"
            else "${LocalDateTimeConverter().convertToHourMinuteString(task.dueOn)} Uhr"
            Text(text = taskLengthText, fontSize = 2.em)
        }
    }
}

@Composable
fun UpcomingEvents(events: List<Event>) {
    Column {
        if (events.isEmpty()) {
            Text(text = "Keine Termine heute")
            return
        }
        LazyColumn(
//            modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max)
        ) {
            items(events) {
                    event -> UpcomingEventRow(event = event)
            }
        }
    }
}

@Composable
fun UpcomingEventRow(event: Event) {
    Row(
        modifier = Modifier
            .border(width = 1.dp, color = Color.DarkGray)
            .height(intrinsicSize = IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(start = 0.dp, end = 1.dp, top = 0.dp, bottom = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .width(10.dp)
                .fillMaxHeight()
                .background(color = event.specialEvent.eventColor)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = event.eventTitle, fontSize = 2.5.em)
            val stringBuilder = StringBuilder()

            val eventDateText = LocalDateTimeConverter().convertToMonthDayString(event.from)
            stringBuilder.append(eventDateText)
            stringBuilder.append(", ")

            val eventLengthText = if (event.eventLength == EventTaskLength.ALL_DAY) "Ganztaegig"
            else "${LocalDateTimeConverter().convertToHourMinuteString(event.from)} - ${
                LocalDateTimeConverter().convertToHourMinuteString(
                    event.to
                )
            } Uhr"
            stringBuilder.append(eventLengthText)
            Text(text = stringBuilder.toString(), fontSize = 2.em)

            val localDate = LocalDate.of(event.from.year, event.from.month, event.from.dayOfMonth)
            val localTime = LocalTime.of(event.from.hour, event.from.minute)

            val periodDate = Period.between(LocalDate.now(), localDate)
            val differenceTime = LocalTime.of(
                abs(LocalTime.now().hour - localTime.hour),
                abs(LocalTime.now().minute - localTime.minute)
            )

            val upcomingEventCountDownString =
                if (periodDate.years > 0) "In ${periodDate.years} Jahren"
                else if (periodDate.months > 0) "In ${periodDate.months} Monaten"
                else if (periodDate.days > 0) "In ${periodDate.days} Tagen"
                else if (differenceTime.hour > 0) "In ${differenceTime.hour} Stunden"
                else if (differenceTime.minute > 0) "In ${differenceTime.minute} Minuten"
                else ""

            Text(text = upcomingEventCountDownString, fontSize = 2.em)

            val paint = Paint()
            paint.measureText("FDFDFDFDFDFD")

        }
    }
}

@Composable
fun TwoWeeksPreview(events: List<Event>, tasks: List<Task>) {
    Column() {
        val weekStartDay = DayOfWeek.MONDAY.dayOfMonth()
        val localDateNow = LocalDate.now()
        var currentDate = if(localDateNow.dayOfMonth < weekStartDay && localDateNow.month.value == 1) LocalDate.of(localDateNow.year - 1, 12, weekStartDay)
        else if (localDateNow.dayOfMonth < weekStartDay) LocalDate.of(localDateNow.year, localDateNow.month.value - 1, weekStartDay)
        else LocalDate.of(localDateNow.year, localDateNow.month.value, weekStartDay)
        val isCurrentDay = Modifier.background(Color.LightGray)
        val isNotCurrentMonth = Modifier.background(Color.Gray)
        Row() {
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Mo")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Di")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Mi")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Do")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Fr")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Sa")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.dayOfMonth == localDateNow.dayOfMonth) isCurrentDay
                else if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "So")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
        }
        Row() {
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Mo")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Di")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Mi")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Do")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Fr")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "Sa")
                Text(text = "${currentDate.dayOfMonth}")
            }
            currentDate = currentDate.plusDays(1)
            Column(
                modifier = if(currentDate.month != localDateNow.month) isNotCurrentMonth else Modifier
            ) {
                Text(text = "So")
                Text(text = "${currentDate.dayOfMonth}")
            }
        }
    }
}

@Preview
@Composable
fun PreviewEventsToday() {
    val data = SampleData().events
    EventsToday(events = data, 300f)
}

@Preview
@Composable
fun PreviewTasksToday() {
    val data = SampleData().tasks
    TasksToday(tasks = data, 300f)
}

@Preview
@Composable
fun PreviewUpcomingEvents() {
    val data = SampleData().upcomingEvents
    UpcomingEvents(events = data)
}

@Preview
@Composable
fun PreviewTwoWeekPreview() {
    val tasks = SampleData().tasks
    val events = SampleData().events
    TwoWeeksPreview(events = events, tasks = tasks)
}