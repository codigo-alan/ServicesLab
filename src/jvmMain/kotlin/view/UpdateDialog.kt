package view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.product.Product

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UpdateDialog(state: Boolean, product: Product, onDismiss: () -> Unit, onConfirm: (product: Product) -> Unit){

    var productUpdated = Product("","","","")

    if (state){
        AlertDialog(
            onDismissRequest = {onDismiss()},
            text = {
                UpdateForm(product) {
                    //onConfirm(it)
                    productUpdated = it
                }
            },
            confirmButton = {
                Button(onClick = { onConfirm(productUpdated) }) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "Cancel")
                }
            },
        )
    }
}

@Composable
fun UpdateForm(product: Product, onChangeField: (productUpdated: Product) -> Unit){

    var productModel by remember { mutableStateOf(product.model) }
    var productSerialNumber by remember { mutableStateOf(product.serialNumber) }
    var productOwner by remember { mutableStateOf(product.owner) }
    var productService by remember { mutableStateOf(product.service) }

    var productUpdated = Product(productSerialNumber, productModel, productOwner, productService)

    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Update product",
            style = MaterialTheme.typography.titleLarge,
        )
        OutlinedTextField(
            value = productModel,
            label = { Text("Product model:") },
            singleLine = true,
            onValueChange = {
                                productModel = it
                                onChangeField(productUpdated)
                            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = productSerialNumber,
            label = { Text("Product S/N") },
            singleLine = true,
            onValueChange = {
                productSerialNumber = it
                onChangeField(productUpdated)
                            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = productOwner,
            label = { Text("Owner company:") },
            singleLine = true,
            onValueChange = {
                productOwner = it
                onChangeField(productUpdated)
                            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = productService,
            label = { Text("Service type:") },
            singleLine = true,
            onValueChange = {
                onChangeField(productUpdated)
                productService = it
                            },
            modifier = Modifier.fillMaxWidth()
        )
        /*Button(
            onClick = {
                onSave(productUpdated)
            },
        ) {
            Text("Add Product")
        }*/
    }
}