
package com.example.taskease.intropages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskease.R
import com.example.taskease.ViewModel.AuthState
import com.example.taskease.ViewModel.AuthViewModel
import com.example.taskease.ui.theme.Appfontfamily
import com.example.taskease.ui.theme.Lancelot
import com.example.taskease.ui.theme.laila

@Composable
fun SplashScreen(navController: NavController, authViewModel: AuthViewModel) {
    // Observe the authentication state
    val authState = authViewModel.authState.observeAsState()

    // Check authentication state and navigate accordingly
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                // Navigate directly to the home page if already authenticated
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            else -> {
                // Stay on splash screen or perform actions for unauthenticated users
            }
        }
    }

    // Render splash UI only if user is not yet authenticated
    if (authState.value != AuthState.Authenticated) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1A2433)) // Background color
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Title
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFF46D75),
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = Appfontfamily,
                                fontSize = 20.sp
                            )
                        ) {
                            append("TaskEase")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = Appfontfamily,
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        ) {
                            append("  your ultimate hub for managing tasks and notes effortlessly.")
                        }
                    },
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )

                // Illustration
                Image(
                    painter = painterResource(id = R.drawable.slashscreenimg),
                    contentDescription = "Clock Illustration",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(vertical = 16.dp)
                )

                // Quote
                Text(
                    text = "When you are overwhelmed by the amount of work you have on your plate, stop and rethink.",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Lancelot,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Start Button
            Button(
                onClick = {
                    navController.navigate("welcomepage")
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF46D75))
            ) {
                Text(
                    text = "Let's Start",
                    color = Color.White,
                    fontFamily = laila,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
