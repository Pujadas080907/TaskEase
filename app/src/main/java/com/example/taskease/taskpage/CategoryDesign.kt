package com.example.taskease.taskpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskease.R

@Composable
fun CategorySelection() {
    val selectedCategory = remember { mutableStateOf<String?>(null) }

    val categories = listOf(
        Pair("Education", R.drawable.book),
        Pair("Health", R.drawable.health),
        Pair("Office", R.drawable.office),
        Pair("Personal", R.drawable.man)
    )

    Column {
        Text(
            text = "Category",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        categories.chunked(2).forEach { rowCategories ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowCategories.forEach { (label, iconRes) ->
                    CategoryChip(
                        label = label,
                        iconRes = iconRes,
                        isSelected = selectedCategory.value == label,
                        onClick = { selectedCategory.value = label }
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

