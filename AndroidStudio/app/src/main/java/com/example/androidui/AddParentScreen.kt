package com.example.androidui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AddParentScreen(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    searchViewModel: SearchViewModel = SearchViewModel()
){

    Column{
        Box(modifier = Modifier
            .fillMaxWidth()
            .offset(x = 10.dp)
        ) {
            //Close Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(50.dp)
                    .offset(x = -15.dp)
            ) {
                Icon(Icons.Filled.ArrowBackIos, "")
            }
        }
        AssetTable(width = 400,
            height = 600,
            navController = navController,
            assetTableViewModel,
            searchViewModel
        )
    }
}


@Composable
fun AddChildScreen(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    searchViewModel: SearchViewModel = SearchViewModel()
){

    Column{
        Box(modifier = Modifier
            .fillMaxWidth()
            .offset(x = 10.dp)
        ) {
            //Close Button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(50.dp)
                    .offset(x = -15.dp)
            ) {
                Icon(Icons.Filled.ArrowBackIos, "")
            }
        }
        AssetTable(width = 400,
            height = 600,
            navController = navController,
            assetTableViewModel,
            searchViewModel = searchViewModel
        )
    }
}