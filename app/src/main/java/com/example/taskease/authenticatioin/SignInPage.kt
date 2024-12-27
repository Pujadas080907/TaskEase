package com.example.taskease.authenticatioin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskease.R
import com.example.taskease.ViewModel.AuthState
import com.example.taskease.ViewModel.AuthViewModel
import com.example.taskease.ui.theme.laila

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInPage(modifier: Modifier = Modifier,  navController: NavController,authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error-> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }





    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A2433)) // Dark background color
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF5B6166)),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = laila,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var confirmpassword by remember { mutableStateOf("") }
                var passwordVisibility by remember { mutableStateOf(false) }
                var confirmPasswordVisibility by remember { mutableStateOf(false) }


                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            "Email",
                            fontFamily = laila,
                            color = Color.White // Set label color to white
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    singleLine = true,
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.email), // Replace with your vector drawable resource for "Full Name"
                            contentDescription = "Full Name Icon",
                            modifier = Modifier.size(24.dp),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Black) // Adjust tint color as needed
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.outlinedTextFieldColors(


                        focusedBorderColor = Color(0xFFF46D75), // Set focused border color to white
                        unfocusedBorderColor = Color.White, // Set unfocused border color to white
                        focusedLabelColor = Color.White, // Set focused label color to white
                        unfocusedLabelColor = Color.White // Set unfocused label color to white
                    )
                )

                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            "Password",
                            fontFamily = laila,
                            color = Color.White // Set label color to white
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,

                    visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordVisibility) R.drawable.openeye else R.drawable.eyeoff
                        val description = if (passwordVisibility) "Hide password" else "Show password"
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = description,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { passwordVisibility = !passwordVisibility }
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(

                        focusedBorderColor = Color(0xFFF46D75), // Set focused border color to white
                        unfocusedBorderColor = Color.White, // Set unfocused border color to white
                        focusedLabelColor = Color.White, // Set focused label color to white
                        unfocusedLabelColor = Color.White // Set unfocused label color to white
                    )
                )
                OutlinedTextField(
                    value = confirmpassword,
                    onValueChange = { confirmpassword = it },
                    label = {
                        Text(
                            "Confirm Password",
                            fontFamily = laila,
                            color = Color.White // Set label color to white
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    visualTransformation = if(confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (confirmPasswordVisibility) R.drawable.openeye else R.drawable.eyeoff
                        val description = if (confirmPasswordVisibility) "Hide password" else "Show password"
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = description,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { confirmPasswordVisibility = !confirmPasswordVisibility}
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(

                        focusedBorderColor = Color(0xFFF46D75), // Set focused border color to white
                        unfocusedBorderColor = Color.White, // Set unfocused border color to white
                        focusedLabelColor = Color.White, // Set focused label color to white
                        unfocusedLabelColor = Color.White // Set unfocused label color to white
                    )
                )

                // Create Account Button
                Button(
                    onClick = {
                        if (password != confirmpassword) {
                            Toast.makeText(context, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                        } else {
                            authViewModel.login(email, password)
                        }

                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF46D75))
                ) {
                    Text(
                        text = "Proceed",
                        color = Color.White,
                        fontFamily = laila
                    )
                }

                // Sign In Text
                TextButton(
                    onClick = { navController.navigate("signup") },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Newbie? ")
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    color = Color(0xFFF46D75),
                                    fontFamily = laila,
                                    fontWeight = FontWeight.Bold // Optional: Add bold style
                                )
                            ) {
                                append("Sign Up")
                            }
                        },
                        color = Color.White, // Default color for other text
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
