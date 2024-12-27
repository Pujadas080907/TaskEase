package com.example.taskease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.taskease.ViewModel.AuthViewModel

import com.example.taskease.Navigation.TaskEaseApp
import com.example.taskease.ui.theme.TaskEaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            TaskEaseTheme {
                Scaffold (modifier = Modifier.fillMaxSize()){ innerPadding->
                    TaskEaseApp(modifier = Modifier.padding(innerPadding), authViewModel = authViewModel)
                }

            }
        }
    }
}

