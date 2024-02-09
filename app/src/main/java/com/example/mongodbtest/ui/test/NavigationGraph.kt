package com.example.mongodbtest.ui.test

sealed class NavigationGraph( val route: String){
    object ComprehensiveForm : NavigationGraph("comprehensive_form")
    object VehicleInfo : NavigationGraph("vehicle_info")
}