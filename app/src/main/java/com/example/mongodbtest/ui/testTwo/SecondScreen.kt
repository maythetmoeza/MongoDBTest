package com.example.mongodbtest.ui.testTwo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    navController: NavController,
    realmModel: RealmViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val allCustomer = realmModel.allCustomer
        var nrc by remember { mutableStateOf("")}

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TextButton(onClick = {
                realmModel.getAllCustomer()
            }) {
                Text(text = "Get All Customer")
            }
            TextButton(onClick = {
                realmModel.deleteAllCustomer()
            }) {
                Text(text = "Delete All Customer")
            }

        }

        allCustomer.let {
             for (info in it) {
                simpleView(info = info)
            }
        }

        OutlinedTextField(
            value = nrc,
            onValueChange = { nrc = it },
            label = { Text(text = "NRC" , color = Color.Black) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black
            )
        )

        TextButton(onClick = {
            realmModel.getDriverList(nrc)
        }) {
            Text(text = "Get Driver List")
        }

        Text(text = "Drivers ")
        realmModel.drivers.let {
            for (driver in it.value) {
                driverView(driver = driver)
            }
        }


    }

}

@Composable
fun simpleView(info: Info){

    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Text(text = "Name: ${info.name}")
            Text(text = "NRC: ${info.nrc}")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Text(text = "Payment Type: ${info.proposal?.paymentType}")
            Text(text = "Currency Type: ${info.proposal?.currencyType}")
        }
        info.driver?.forEach {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Text(text = "Driver Name: ${it.driverName}")
                Text(text = "Driver NRC: ${it.driverNrc}")
            }
        }
    }

}

@Composable
fun driverView(driver: Driver){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)){
        Text(text = "Name: ${driver.driverName}")
        Text(text = "NRC: ${driver.driverNrc}")
    }

}