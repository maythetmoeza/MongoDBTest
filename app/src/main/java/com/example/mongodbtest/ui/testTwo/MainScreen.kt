package com.example.mongodbtest.ui.testTwo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun MainScreen(
    navController: NavController,
    realmModel: RealmViewModel = hiltViewModel(),
) {

    var name by remember { mutableStateOf("")}
    var nrc by remember { mutableStateOf("")}
    var paymentType by remember { mutableStateOf("")}
    var currency by remember { mutableStateOf("")}
    var driverName by remember { mutableStateOf("")}
    var driverNrc by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name" , color = Color.Black) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black
            )
        )
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
        val consumer = Customer(
            name = name,
            nrc = nrc
        )
        Button(onClick = {
            realmModel.insertOrUpdateCustomer(
                consumer
            )

        }) {
            Text(text = "ConsumerInsert", modifier = Modifier.padding(horizontal = 5.dp))
        }

        OutlinedTextField(
            value = paymentType,
            onValueChange = { paymentType = it },
            label = { Text(text = "Payment Type" , color = Color.Black) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black
            )
        )
        OutlinedTextField(
            value = currency,
            onValueChange = { currency = it },
            label = { Text(text = "Currency" , color = Color.Black) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black
            )
        )

        val proposal = Proposal(
            paymentType = paymentType,
            currencyType = currency
        )

        Button(onClick = {
            realmModel.updateProposal(proposal, nrc = nrc)
        }) {
            Text(text = "ProposalInfo", modifier = Modifier.padding(horizontal = 5.dp))
        }
        OutlinedTextField(
            value = driverName,
            onValueChange = { driverName = it },
            label = { Text(text = "Driver Name" , color = Color.Black) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black
            )
        )
        OutlinedTextField(
            value = driverNrc,
            onValueChange = { driverNrc = it },
            label = { Text(text = "Driver NRC" , color = Color.Black) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black
            )
        )

        val driver = Driver(
            driverName = driverName,
            driverNrc = driverNrc
        )

        Button(onClick = {
//            realmModel.updateDriver(driver, nrc = nrc)
            realmModel.insertOrUpdateDriver(driver, nrc = nrc)
        }) {
            Text(text = "DriverInfo", modifier = Modifier.padding(horizontal = 5.dp))
        }

        Button(onClick = {
            navController.navigate("second_screen")
        }) {
            Text(text = "View Screen", modifier = Modifier.padding(horizontal = 5.dp))
        }



    }

}