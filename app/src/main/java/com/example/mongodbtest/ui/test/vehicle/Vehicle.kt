package com.example.mongodbtest.ui.test.vehicle

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mongodbtest.ui.test.ConsumerRealm
import com.example.mongodbtest.ui.test.NavigationGraph
import com.example.mongodbtest.ui.test.RealmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vehicle(navController: NavController,
            realmModel: RealmViewModel = hiltViewModel()
            ) {

    var formList: MutableList<ConsumerRealm> = mutableListOf()
    Scaffold(
        floatingActionButton = {
            Button(onClick = {
                navController.navigate(NavigationGraph.ComprehensiveForm.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Next",
                    modifier = Modifier.size(30.dp)
                )

            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TextButton(onClick = {
                    realmModel.deleteAll()
                }) {
                    Text(text = "Delete All")
                }

                TextButton(onClick = {
                    realmModel.getAllForm()
                   formList = realmModel.allForm

                    Log.d("consumerForm", "${formList.toString()}")
                }
                ) {
                    Text(text = "Get All")
                }



                formList?.let { consumerRealm ->
                    for (form in consumerRealm) {
                        display(form = form)
                    }

                }

            }



        }
    }
}

@Composable
fun display(form: ConsumerRealm) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Name: ${form.name}")
        Text(text = "NRC: ${form.nrc}")
        Text(text = "Payment: ${form.proposal?.paymentType}")
    }
}


