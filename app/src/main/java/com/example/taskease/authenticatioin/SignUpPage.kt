
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
fun SignUpPage(modifier: Modifier = Modifier,  navController: NavController,authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    //Navigation
    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error-> Toast.makeText(context,
                (authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    //UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A2433))
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
                    text = "Sign Up",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = laila,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                var fullName by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var passwordVisibility by remember { mutableStateOf(false) }

                //Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = {
                        Text(
                            "Full Name",
                            fontFamily = laila,
                            color = Color.White
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        ,
                    singleLine = true,
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = "Full Name Icon",
                            modifier = Modifier.size(24.dp),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Black)
                        )
                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(

                        focusedBorderColor = Color(0xFFF46D75),
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )

               //Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            "Email",
                            fontFamily = laila,
                            color = Color.White
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    singleLine = true,
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "email",
                            modifier = Modifier.size(24.dp),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.Black)
                        )
                    },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.outlinedTextFieldColors(


                        focusedBorderColor = Color(0xFFF46D75),
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            "Password",
                            fontFamily = laila,
                            color = Color.White
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

                        focusedBorderColor = Color(0xFFF46D75),
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White
                    )
                )

                // Create Account Button
                Button(
                    onClick = {
                        authViewModel.signup(email,password)
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF46D75))
                ) {
                    Text(
                        text = "Create Account",
                        color = Color.White,
                        fontFamily = laila
                    )
                }

                // Sign In Text
                TextButton(
                    onClick = { navController.navigate("signin") },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Existing member? ")
                            withStyle(
                                style = androidx.compose.ui.text.SpanStyle(
                                    color = Color(0xFFF46D75),
                                    fontFamily = laila,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Sign In")
                            }
                        },
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
