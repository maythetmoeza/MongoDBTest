package com.example.mongodbtest.ui.testTwo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mongodbtest.ui.test.ComprehensiveForm
import com.example.mongodbtest.ui.test.NavigationGraph
import com.example.mongodbtest.ui.test.vehicle.Vehicle

@Composable
fun  NavGraphTwo() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ){
        composable("main_screen"){
            MainScreen(navController = navController)
        }
        composable("second_screen"){
            SecondScreen(navController = navController)
        }
        composable("third_screen"){
            ThirdScreen(navController = navController)
        }

    }
}