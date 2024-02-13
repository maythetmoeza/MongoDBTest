package com.example.mongodbtest.ui.test

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mongodbtest.ui.test.consumer.ConsumerForm
import com.example.mongodbtest.ui.test.proposal.Proposal
import com.example.mongodbtest.ui.test.vehicle.Vehicle


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComprehensiveForm(
    navController: NavController,
    realmModel: RealmViewModel = hiltViewModel(),
) {

    val page = rememberSaveable {
        mutableStateOf(1)
    }


    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    if (page.value > 0) {
                        Box(modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                onClick = { page.value-- },
                                modifier = Modifier.align(Alignment.CenterStart)
                            ) {
                                Text("Back", modifier = Modifier.padding(5.dp))
                            }

                            if (page.value == 3) {
                                Button(
                                    onClick = {

                                },
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                ) {
                                    Text("Save", modifier = Modifier.padding(5.dp))
                                }
                            } else {
                                Button(
                                    onClick = {
                                        realmModel.getConsumer("123")
                                        val info = Information(
                                            name = "mtm",
                                            fatherName = "U Aung",
                                            nrc = "123",
                                            paymentType = null,
                                            currencyType = null,
                                        )

                                        if(realmModel.alreadyExist.value){
                                            val update = Information(
                                                name = "mtm",
                                                fatherName = "U Aung",
                                                nrc = "123",
                                                paymentType = "bank",
                                                currencyType = "mmk",
                                            )
                                            realmModel.updateInfo(update)
                                        }else{
                                            realmModel.insert(info)
                                        }

                                        page.value++
                                              },
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                ) {
                                    Text("Next")


                                }
                            }
                        }
                    }else{
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                onClick = { page.value++ },
                                modifier = Modifier.align(Alignment.CenterEnd)
                            ) {
                                Text("Next")
                            }
                        }
                    }

                },
                tonalElevation = 5.dp
                )
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(Color.White)
        ) {
            when (page.value) {

                1 -> ConsumerForm()

                2 -> Proposal()

                3 -> Vehicle(navController)
            }

        }
    }


}