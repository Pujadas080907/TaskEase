package com.example.taskease.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskease.ViewModel.AuthViewModel
import com.example.taskease.authenticatioin.SignInPage
import com.example.taskease.authenticatioin.SignUpPage
import com.example.taskease.intropages.SplashScreen
import com.example.taskease.intropages.WelcomePage
import com.example.taskease.taskpage.HomePage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskEaseApp(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController,authViewModel = authViewModel)
        }
        composable("welcomepage") {
            WelcomePage(navController = navController)
        }
        composable("signup"){
            SignUpPage(navController = navController,authViewModel = authViewModel)
        }

        composable("signin"){
            SignInPage(navController = navController,authViewModel = authViewModel)
        }
        composable("home"){
            HomePage()
        }

    }
}