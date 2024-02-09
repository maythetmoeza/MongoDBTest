package com.example.mongodbtest.ui.test.vehicle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mongodbtest.ui.test.NavigationGraph
import com.example.mongodbtest.ui.test.RealmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vehicle(navController: NavController,
            realmModel: RealmViewModel = hiltViewModel()
            ) {
    Scaffold(
        floatingActionButton = {
            Button(onClick = {
                navController.navigate(NavigationGraph.ComprehensiveForm.route)
//                realmModel.deleteAllForm()
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

            val formList = realmModel.allForm
            formList?.let {list ->
                Column{
                    for (form in list) {
                        Text(text = form.toString(), color = Color.Black)
                    }
                }

            }

        }
    }
}


