package ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyAlertDialog(state: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit){

    if (state) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Edit product") },
            text = { Text(text = "Not implemented yet") },
            confirmButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

}