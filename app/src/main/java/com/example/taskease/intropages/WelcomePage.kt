package com.example.taskease.intropages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskease.ui.theme.Lancelot
import com.example.taskease.ui.theme.laila


@Composable
fun WelcomePage(modifier: Modifier = Modifier,  navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A2433)) // Dark Blue Background
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .background(
                    color = Color(0xFF5B6166),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp) // Padding inside the card
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Welcome Text
                Text(
                    text = "Welcome",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = laila,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(modifier = Modifier.weight(0.5f))

                // Subtitle Text
                Text(
                    text = "Welcome! Are you ready to manage your days? Let's start your journey to productivity!",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = Lancelot
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))

                // Buttons Row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Sign Up Button
                    Button(
                        onClick = { navController.navigate("signup") },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF46D75))
                    ) {
                        Text(text = "Sign up", color = Color.Black,
                            fontFamily = laila,
                            fontWeight = FontWeight.ExtraBold)
                    }

                    // Sign In Button
                    Button(
                        onClick = { navController.navigate("signin")  },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),

                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(text = "Sign in", color = Color.Black,
                            fontFamily = laila,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

