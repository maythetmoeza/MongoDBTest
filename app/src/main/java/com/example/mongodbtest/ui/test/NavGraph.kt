package com.example.mongodbtest.ui.test

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mongodbtest.ui.test.consumer.ConsumerForm
import com.example.mongodbtest.ui.test.vehicle.Vehicle

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationGraph.VehicleInfo.route
    ){
        composable(NavigationGraph.VehicleInfo.route){
            Vehicle(navController = navController)
        }
        composable(NavigationGraph.ComprehensiveForm.route){
            ComprehensiveForm(navController)
        }

    }
}