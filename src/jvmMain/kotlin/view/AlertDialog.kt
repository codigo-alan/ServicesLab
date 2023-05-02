package view

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyAlertDialog(state: Boolean, content: List<String>, onDismiss: () -> Unit, onConfirm: () -> Unit){

    if (state) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = content.first()) },
            text = { Text(text = content.last()) },
            confirmButton = {
                Button(onClick = { onConfirm() }) {
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