package com.example.calendar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendar.database.tables.Event
import com.example.calendar.database.tables.Task
import com.example.calendar.sampleData.SampleData

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val events = SampleData().events
        val tasks = SampleData().tasks
        val upcomingEvents = SampleData().upcomingEvents
        val displayMetrics = this.resources.displayMetrics
        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val displayWidth = displayMetrics.widthPixels/displayMetrics.density
            MainScaffold(displayWidth, events = events, tasks = tasks, upcomingEvents = upcomingEvents)
        }
    }
    
    @Composable
    fun MainScaffold(displayWidth: Float, events: List<Event>, tasks: List<Task>, upcomingEvents: List<Event>) {
        val a = WindowWidthSizeClass.Compact
        Scaffold(
            bottomBar = {
                BottomNavigationBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        Log.d("FloatingActionButton", "Pressed")
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "New calendar entry")
                }
            }
        ) { innerPadding ->
            Column (modifier = Modifier.padding(innerPadding)) {
                Row(

                ) {
                    EventsToday(events = events, width = displayWidth/2)
                    TasksToday(tasks = tasks, width = displayWidth/2)
                }
                UpcomingEvents(events = upcomingEvents)
                TwoWeeksPreview(events = events, tasks = tasks)
            }
        }
    }

    @Composable
    fun BottomNavigationBar() {
        val selectedIndex = remember { mutableIntStateOf(0) }
        BottomNavigation(elevation = 10.dp) {
            BottomNavigationItem(
                selected = selectedIndex.intValue == 0,
                onClick = { selectedIndex.intValue = 0 },
                icon = { R.drawable.baseline_home_24 }
            )
            BottomNavigationItem(
                selected = selectedIndex.intValue == 1,
                onClick = { selectedIndex.intValue = 1 },
                icon = { R.drawable.baseline_calendar_month_24 }
            )
            BottomNavigationItem(
                selected = selectedIndex.intValue == 2,
                onClick = { selectedIndex.intValue = 2 },
                icon = { R.drawable.baseline_settings_24 }
            )
        }
    }

}