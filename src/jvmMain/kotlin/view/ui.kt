package view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    var product = Product(productSerialNumber, productModel, productOwner, productService)
    var products by remember { mutableStateOf(ServiceRepository.productRepository.list()) }
    var productsFiltered by remember { mutableStateOf(ServiceRepository.productRepository.list()) }

    LazyRow() {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Entry new product",
                    style = MaterialTheme.typography.titleLarge,
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
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        ServiceRepository.productRepository.insert(product)
                        products = ServiceRepository.productRepository.list()
                        productsFiltered = ServiceRepository.productRepository.list()
                              },
                    ) {
                    Text("Add Product")
                }
            }
        }
        item() {
            ProductsList(
                productsFiltered,
                {
                    ServiceRepository.productRepository.delete(it.serialNumber)
                    products = ServiceRepository.productRepository.list()
                    productsFiltered = ServiceRepository.productRepository.list()
                },
                {
                    productsFiltered = if (it.isEmpty()) {
                        products
                    } else {
                        products.filter { product: Product -> it.lowercase() in product.owner.lowercase() }
                    }
                },
                { serial, productUpdated ->
                    ServiceRepository.productRepository.update(serial, productUpdated)
                    products = ServiceRepository.productRepository.list()
                    productsFiltered = ServiceRepository.productRepository.list()
                },
            )
        }
    }
}

@Composable
fun SearchBar(
    onTextChange: (String) -> Unit
){
    var searchText by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.width(240.dp)
    ){
            OutlinedTextField(
                value = searchText,
                label = {Icon( Icons.Filled.Search, contentDescription = "Search product")},
                singleLine = true,
                onValueChange = {
                                    searchText = it
                                    onTextChange(it)
                                },
                modifier = Modifier.fillMaxWidth()
            )
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ProductsList(
    products: List<Product>,
    onDelete: (Product) -> Unit,
    onSearchText: (String) -> Unit,
    onEdit: (String, Product) -> Unit
) {

    var showDialogEdit by remember { mutableStateOf(false) }
    var showDialogDelete by remember { mutableStateOf(false) }
    var productSelected by remember { mutableStateOf(Product("","","","")) }

    val deleteDialogContent = listOf("Delete Product", "Do you want to delete this product?")

    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.width(480.dp)
        ) {
            Text(
                text = "Products list",
                style = MaterialTheme.typography.titleLarge,
            )
            SearchBar { onSearchText(it) }
        }
        LazyColumn() {
            items(products){product ->

                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(4.dp).width(480.dp).height(148.dp),
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillParentMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = product.owner,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Row(horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                //Edit button
                                IconButton(
                                    onClick = {
                                        showDialogEdit = true
                                        productSelected = product
                                    }
                                ){
                                    Icon( Icons.Filled.Edit, contentDescription = "Edit product")
                                }

                                //Delete button
                                IconButton(
                                    onClick = {
                                        showDialogDelete = true
                                        productSelected = product
                                    }
                                ){
                                    Icon( Icons.Filled.Clear, contentDescription = "Delete product")
                                }

                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = product.model, style = MaterialTheme.typography.headlineSmall)
                            Text(text = product.serialNumber, style = MaterialTheme.typography.bodyLarge)
                            Text(text = product.service, style = MaterialTheme.typography.bodyLarge)
                        }

                    }
                }
                //Delete dialog
                MyAlertDialog(showDialogDelete, deleteDialogContent,
                    {
                        showDialogDelete = false
                    },
                    {
                        showDialogDelete = false
                        onDelete(productSelected) //call lambda with product to delete
                    }
                )
                //Edit dialog
                UpdateDialog(showDialogEdit, productSelected,
                    {showDialogEdit = false},
                    {
                        showDialogEdit = false
                        onEdit(productSelected.serialNumber, it)
                    }
                )
            }
        }
    }


}