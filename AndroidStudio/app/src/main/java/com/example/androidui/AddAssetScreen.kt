package com.example.androidui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


// This file contains that add asset screen
// The screen that appears when "+ Drone" is clicked for a new flight log form
// Also the screen that appears when "+ Asset" is clicked for a check out form
// or maintenance log form
@Composable
fun AddAssetScreen(
    navController: NavController,
    assetTableViewModel: AssetTableViewModel,
    assetSearchViewModel: AssetSearchViewModel
) {

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 10.dp)
        ) {
            //Close Button
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .width(50.dp)
                    .offset(x = -15.dp)
            ) {
                Icon(Icons.Filled.ArrowBackIos, "")
            }
        }
        // Box that contains the search bar and filters
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Row {
                AssetSearchBar(assetSearchViewModel)
                IconButton(
                    modifier = Modifier.offset(x = 20.dp, y = 15.dp),
                    onClick = {
                        navController.navigate("asset-filters")
                    }
                ) {
                    Icon(
                        Icons.Outlined.FilterAlt,
                        "",
                        modifier = Modifier.size(50.dp),
                        tint = Color.DarkGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        AssetTable(
            width = 375,
            height = 600,
            navController = navController,
            assetTableViewModel = assetTableViewModel,
            assetSearchViewModel = assetSearchViewModel
        )
    }
}