package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.product.Product
import services.ServiceRepository


@Composable
fun AddProductForm() {

    var productModel by remember { mutableStateOf("") }
    var productSerialNumber by remember { mutableStateOf("") }
    var productOwner by remember { mutableStateOf("") }
    var productService by remember { mutableStateOf("") }
    /*var product by remember {
        mutableStateOf(Product(productSerialNumber, productModel, productOwner, productService))
    }*/
    var product = Product(productSerialNumber, productModel, productOwner, productService)
    var products by remember { mutableStateOf(ServiceRepository.productRepository.list()) }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Entry new product",
            //style = MaterialTheme.typography.titleLarge,
        )
        OutlinedTextField(
            value = productModel,
            label = { Text("Product model:") },
            singleLine = true,
            onValueChange = {productModel = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = productSerialNumber,
            label = { Text("Product S/N") },
            singleLine = true,
            onValueChange = { productSerialNumber = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = productOwner,
            label = { Text("Owner company:") },
            singleLine = true,
            onValueChange = { productOwner = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = productService,
            label = { Text("Service type:") },
            singleLine = true,
            onValueChange = { productService = it},
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Button(
            onClick = {
                ServiceRepository.productRepository.insert(product)
                products = ServiceRepository.productRepository.list()
                      },
            ) {
            Text("Add Product")
        }
        LazyColumn {
            items(products){product ->
                Text(product.toString())
            }
        }

    }
}

@Composable
fun ProductsList() {
    val products = ServiceRepository.productRepository.list() //TODO how to update at moment but getting from db.
    //val products : List<Product> by remember { mutableStateOf(ServiceRepository.productRepository.list()) }
    LazyColumn {
        items(products){product ->
            Text(product.toString())
        }
    }
}