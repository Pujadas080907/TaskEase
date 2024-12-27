package com.example.taskease.taskpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskease.R
import com.example.taskease.ui.theme.laila

@Composable
fun BottomNavigationBar() {
    val items = listOf("Task", "Completed", "Reminders", "Notes")
    val icons = listOf(
        R.drawable.task,
        R.drawable.checkmark,
        R.drawable.notification,
        R.drawable.note
    )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items.zip(icons).forEach { (item, iconRes) ->
            IconButton(onClick = { /* Navigate to respective page */ },
                modifier = Modifier.weight(1f)

            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,


                    ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = item,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = item,
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontFamily = laila,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
