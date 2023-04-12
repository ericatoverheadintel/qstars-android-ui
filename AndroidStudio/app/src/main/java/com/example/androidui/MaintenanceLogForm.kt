package com.example.androidui

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MaintenanceLogFormScreen(
    navController: NavController,
    maintenanceFormViewModel: MaintenanceLogFormViewModel,
    maintenanceTableViewModel: MaintenanceTableViewModel
) {
    Column {

        //Title and Close Button
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {

                    //clear textfields
                    maintenanceFormViewModel.ID = ""
                    maintenanceFormViewModel.assetID = ""
                    maintenanceFormViewModel.employeeID = ""
                    maintenanceFormViewModel.employeeName = ""
                    maintenanceFormViewModel.dateOfMaintenance = ""
                    maintenanceFormViewModel.typeOfMaintenane = ""
                    maintenanceFormViewModel.additionalDetails = ""

                    navController.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .width(55.dp)
            ) {
                Icon(Icons.Filled.Close, "")
            }
            Text(
                "Create Maintenance Log",
                Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }

        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(text = "ID")
                AppTextField(
                    text = maintenanceFormViewModel.ID,
                    onChange = { maintenanceFormViewModel.onIDChange(it) },
                    placeholder = "ID")
            }

            item {
                Text(text = "Asset ID")
                AppTextField(
                    text = maintenanceFormViewModel.assetID,
                    onChange = { maintenanceFormViewModel.onAssetIDChange(it) },
                    placeholder = "Asset ID")
            }

            item {
                Text(text = "Employee ID")
                AppTextField(
                    text = maintenanceFormViewModel.employeeID,
                    onChange = { maintenanceFormViewModel.onEmployeeIDChange(it) },
                    placeholder = "Employee ID")
            }

            item {
                Text(text = "Employee Name")
                AppTextField(
                    text = maintenanceFormViewModel.employeeName,
                    onChange = { maintenanceFormViewModel.onEmployeeNameChange(it) },
                    placeholder = "Employee Name")
            }

            item {
                val context = LocalContext.current

                Text(text = "Date of Maintenance")
                AppTextField(
                    modifier = Modifier.clickable {
                        maintenanceFormViewModel.showDatePickerDialog(context)
                    },
                    text = maintenanceFormViewModel.dateOfMaintenance,
                    placeholder = "MM-DD-YYYY",
                    onChange = {
                        maintenanceFormViewModel.dateOfMaintenance = it
                    },
                    isEnabled = false
                )
            }

            item {
                Text(text = "Maintenance Type")
                AppTextField(
                    text = maintenanceFormViewModel.typeOfMaintenane,
                    onChange = { maintenanceFormViewModel.onTypeChange(it) },
                    placeholder = "Maintenance Type")
            }

            item {
                Text(text = "Additional Details")
                AppTextField(
                    text = maintenanceFormViewModel.additionalDetails,
                    onChange = { maintenanceFormViewModel.onDetailsChange(it) },
                    placeholder = "Additional Details")
            }

            item{
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    //checks whether any of the required fields are empty
                    var requiredFieldsFilled = maintenanceFormViewModel.ID.isNotEmpty() &&
                            maintenanceFormViewModel.assetID.isNotEmpty() &&
                            maintenanceFormViewModel.employeeID.isNotEmpty() &&
                            maintenanceFormViewModel.employeeName.isNotEmpty() &&
                            maintenanceFormViewModel.dateOfMaintenance.isNotEmpty() &&
                            maintenanceFormViewModel.typeOfMaintenane.isNotEmpty() &&
                            maintenanceFormViewModel.additionalDetails.isNotEmpty()


                    Button( modifier = Modifier.align(Alignment.CenterHorizontally),
                        enabled = requiredFieldsFilled,
                        onClick = {

                            //pass all values to create new flight log
                            maintenanceTableViewModel.newMaintenanceLog(
                                maintenanceFormViewModel.ID,
                                maintenanceFormViewModel.assetID,
                                maintenanceFormViewModel.employeeID,
                                maintenanceFormViewModel.employeeName,
                                maintenanceFormViewModel.dateOfMaintenance,
                                maintenanceFormViewModel.typeOfMaintenane,
                                maintenanceFormViewModel.additionalDetails
                            )

                            //clear textfields
                            maintenanceFormViewModel.ID = ""
                            maintenanceFormViewModel.assetID = ""
                            maintenanceFormViewModel.employeeID = ""
                            maintenanceFormViewModel.employeeName = ""
                            maintenanceFormViewModel.dateOfMaintenance = ""
                            maintenanceFormViewModel.typeOfMaintenane = ""
                            maintenanceFormViewModel.additionalDetails = ""

                            //navigate to asset screen
                            navController.navigate("maintenance-logs")


                        }) {
                        Text("Create Maintenance Log")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }




        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}