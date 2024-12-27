
package com.example.taskease.taskpage

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.taskease.R
import com.example.taskease.ui.theme.Lancelot
import com.example.taskease.ui.theme.laila
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage() {
    var showAddTask by remember { mutableStateOf(false) }
    BackHandler {
        System.exit(0)
    }

    val currentDate = remember { LocalDate.now() }
    val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy")
    val formattedDate = currentDate.format(formatter)
    val firstDayOfWeek = currentDate.with(WeekFields.of(Locale.getDefault()).firstDayOfWeek)
    val daysOfWeek = (0..6).map { firstDayOfWeek.plusDays(it.toLong()) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFEFEFEF),
                modifier = Modifier.height(64.dp)
            ) {
                BottomNavigationBar()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1A2433))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = formattedDate,
                    fontFamily = laila,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    daysOfWeek.forEach { date ->
                        val dayOfWeek = date.dayOfWeek.toString().substring(0, 2)
                        val dayOfMonth = date.dayOfMonth.toString()

                        Box(
                            modifier = Modifier
                                .size(40.dp, 70.dp)
                                .background(
                                    color = if (date == currentDate) Color(0xFFF46D75) else Color.Gray,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = dayOfWeek,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontFamily = laila
                                )
                                Box(
                                    modifier = Modifier
                                        .size(32.dp) // Size of the inner white circle
                                        .background(
                                            color = Color.White,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = dayOfMonth,
                                        color = if (date == currentDate) Color(0xFFF46D75) else Color.Gray,
                                        fontSize = 12.sp,
                                        fontFamily = laila
                                    )
                                }
                            }
                        }
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A2433)) // Background color similar to the image
                        .padding(horizontal = 12.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Search Bar
                    var searchQuery by remember { mutableStateOf("") }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                Color.White,
                                shape = CircleShape
                            ) // Rounded shape for the search bar
                            .clickable { /* Implement search bar click action if needed */ }
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.search), // Replace with actual search icon resource
                                contentDescription = "Search Icon",
                                modifier = Modifier
                                    .size(20.dp), // Icon size
                                colorFilter = ColorFilter.tint(Color.Gray)

                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            BasicTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                textStyle = TextStyle(color = Color.Gray, fontSize = 14.sp),
                                decorationBox = { innerTextField ->
                                    if (searchQuery.isEmpty()) {
                                        Text(
                                            text = "Search Tasks",
                                            color = Color.Gray,
                                            fontSize = 14.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // First Circular Button (Bell Icon)
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .background(Color.White, shape = CircleShape) // Circular button
                            .clickable { /* Implement bell icon button action here */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bell), // Replace with actual bell icon resource
                            contentDescription = "Bell Icon",
                            modifier = Modifier.size(24.dp) // Icon size
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Second Circular Button (List Icon)
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .background(Color.White, shape = CircleShape) // Circular button
                            .clickable { /* Implement list icon button action here */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cate), // Replace with actual list icon resource
                            contentDescription = "category Icon",
                            modifier = Modifier.size(28.dp) // Icon size
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize() // Fill the whole screen
                        .padding(16.dp), // Optional padding around the content
                    contentAlignment = Alignment.Center // Align content in the center of the Box
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center) // Align the Column in the center of the Box
                            .fillMaxWidth(), // Ensure Column fills the width of the screen
                        horizontalAlignment = Alignment.CenterHorizontally, // Center align all child elements horizontally
                        verticalArrangement = Arrangement.Center // Space out children evenly vertically
                    ) {
                        // No Task Yet Text
                        Text(
                            text = "No Task Yet!",
                            color = Color(0xFFF46D75),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = laila,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        // Description Text
                        Text(
                            text = "Add a Task and set your reminder to stay on top of your tasks.",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = Lancelot,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        // Add Task Button
                        Button(
                            onClick = {showAddTask = true},
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 16.dp)
                                .height(45.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF46D75))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.circleplus),
                                    contentDescription = "Add Task",
                                    modifier = Modifier.size(25.dp)
                                )

                                Text(
                                    text = "Add",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontFamily = laila,
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
        if (showAddTask) {
            AddTaskDialog(onDismiss = { showAddTask = false })
        }
    }
}


