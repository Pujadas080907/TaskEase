package com.example.taskease.taskpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskease.ui.theme.laila

@Composable
fun PrioritySelection() {
    val selectedPriority = remember { mutableStateOf<String?>(null) }
    val priorities = listOf("Now", "Soon", "Later")

    Column {
        Text(
            text = "Priority",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            priorities.forEach { label ->
                val isSelected = selectedPriority.value == label
                Surface(
                    shape = RoundedCornerShape(50),
                    color = if (isSelected) Color(0xFFFB6D5D) else Color(0xFFDDDDDD),
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(32.dp)
                        .clickable { selectedPriority.value = label }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = label,
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 14.sp,
                            fontFamily = laila,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}



