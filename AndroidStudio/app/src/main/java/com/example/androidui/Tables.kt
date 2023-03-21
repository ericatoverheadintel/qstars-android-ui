package com.example.androidui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController

//function for an individual tablecell
@Composable
fun RowScope.TableCell(text: String, weight: Float)
{
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(4.dp)
    )
}

//sticky headers is an experimental feature
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Table(
    tableId: String,
    tableTitle: String,
    column1Title: String,
    column2Title: String,
    column3Title: String,
    column4Title: String,
    width: Int, height: Int,
    navController: NavController
) {

    val tableData = (1..100).mapIndexed { index, i -> index to "Item $index" }
    val column1Weight = .3f
    val column2Weight = .3f
    val column3Weight = .2f
    val column4Weight = .2f

    var popupControl by remember { mutableStateOf(false) }

    var dataRemember by remember { mutableStateOf("") }


    Column()
    {
        Text(
            text = tableTitle,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(x = 15.dp)
                .offset(y = 10.dp)
        )

        LazyColumn(
            Modifier
                .size(width = width.dp, height = height.dp)
                .padding(15.dp)
                .border(2.dp, Color.Gray))
        {
            stickyHeader {
                Row(Modifier.background(Color.Gray))
                {
                    TableCell(text = column1Title, weight = column1Weight)
                    TableCell(text = column2Title, weight = column2Weight)
                    TableCell(text = column3Title, weight = column3Weight)
                    TableCell(text = column4Title, weight = column4Weight)
                }
            }

            items(tableData) {
                val (id, text) = it
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit)
                    {
                        detectTapGestures(
                            onDoubleTap = {
                                dataRemember = id.toString()

                                popupControl = true
                                if (popupControl) {
                                    if (tableId == "asset") {
                                        navController.navigate(
                                            ScreenRoutes.AssetDetails.withArgs(
                                                dataRemember
                                            )
                                        )
                                    } else if (tableId == "flight_log") {
                                        navController.navigate(
                                            ScreenRoutes.FlightLogDetails.withArgs(
                                                dataRemember
                                            )
                                        )
                                    } else if (tableId == "check_in_out") {
                                        navController.navigate(
                                            ScreenRoutes.CheckInOutDetails.withArgs(
                                                dataRemember
                                            )
                                        )
                                    } else if (tableId == "maintenance_log") {
                                        navController.navigate(
                                            ScreenRoutes.MaintenanceLogDetails.withArgs(
                                                dataRemember
                                            )
                                        )
                                    }
                                }
                            }
                        )
                    }
                )
                {
                    TableCell(text = id.toString(), weight = column1Weight)
                    TableCell(text = text, weight = column2Weight)
                    TableCell(text = text, weight = column3Weight)
                    TableCell(text = text, weight = column4Weight)

                }
            }
        }
    }
}