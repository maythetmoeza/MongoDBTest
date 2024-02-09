package com.example.mongodbtest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mongodbtest.ui.test.NavGraph
import com.example.mongodbtest.ui.theme.MongoDBTestTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MongoDBTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting()
                    NavGraph()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(
    viewModel: HomeViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getAllItem()
        viewModel.getAllTownship()
    })
    var isComplete by remember{ mutableStateOf("")}
    var summary by remember { mutableStateOf("")}
    var owner_id by remember { mutableStateOf("")}
    val itemList = viewModel.allItem.value
    val townListResponse = viewModel.townList.value
    var searchItem by remember { mutableStateOf(Item()) }

    val townList = listOf(Township("1", "Yangon"), Township("2", "Mandalay"), Township("3", "Naypyitaw"))


    viewModel.summary.value = summary
    viewModel.ownerId.value = owner_id
    viewModel.isComplete.value = isComplete


    Log.d("Data list", itemList.toString())
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            ){
            Text(text = "isComplete", color = Color.Black)
            OutlinedTextField(
                value = isComplete,
                onValueChange = {
                    isComplete = it
                },

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    textColor = Color.Black
                )
            )

        }

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "summary", color = Color.Black)
                OutlinedTextField(
                    value = summary,
                    onValueChange = {
                        summary = it
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    )
                )

            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "owner_id", color = Color.Black)
                OutlinedTextField(
                    value = owner_id,
                    onValueChange = {
                        owner_id = it
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    )
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Button(onClick = {
                    viewModel.insertItem()
                }) {
                    Text(text = "Add")
                }
                Button(onClick = {
                    viewModel.updateItem()
                }) {
                    Text(text = "Update")
                }
                Button(onClick = {
                    viewModel.deleteItem()
                }) {
                    Text(text = "Delete")
                }
                Button(onClick = {
                    viewModel.getAllItem()
                }) {
                    Text(text = "GetAll")
                }

            }

            Row(modifier = Modifier.fillMaxWidth()){
                Button(onClick = {
                    viewModel.deleteAll()
                }) {
                    Text(text = "DeleteAll")
                }
                Button(onClick = {
                    viewModel.filterItem()
                }) {
                    Text(text = "FilterByIsComplete")
                }

            }
            Row(modifier = Modifier.fillMaxWidth()){
                Button(onClick = {
                    viewModel.insertTownships(townList)
                }) {
                    Text(text = "InsertTownships")
                }
                Button(onClick = {
                    viewModel.getAllTownship()
                }) {
                    Text(text = "GetAllTownships")
                }

            }


            Column {
                if (searchItem.summary.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = "Result")
                        Text(text = searchItem.isComplete.toString())
                        Text(text = searchItem.summary)
                        Text(text = searchItem.owner_id)
                    }
                }

                itemList.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
//                        Text(text = it._id.toString(), color = Color.Black)
                        Text(text = it.isComplete.toString(), color = Color.Black)
                        Text(text = it.summary, color = Color.Black)
                        Text(text = it.owner_id, color = Color.Black)

                    }
                }

                townListResponse.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = it.id, color = Color.Black)
                        Text(text = it.name, color = Color.Black)
                        Text(text = it.description, color = Color.Black)

                    }
                }

            }
        }

    }
}
