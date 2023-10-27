package com.example.calendar.enums

import androidx.compose.ui.graphics.Color

enum class SpecialEvent(val eventColor: Color) {
    VACATION(Color.Green),
    BIRTHDAY(Color.Cyan),
    MEETING(Color.Yellow),
    NONE(Color.Blue)
}