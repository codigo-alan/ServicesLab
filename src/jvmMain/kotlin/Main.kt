// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

/**
 * Program to have register of the income products to repair or maintenance.
 * There are only one table in DB, Products.
 * It uses 'exposed' like intermediate between postgresSql.
 */

@Composable
@Preview
fun App() {

    MaterialTheme() {
        MyForm()
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
fun MyForm() {

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Entry new product",
            //style = MaterialTheme.typography.titleLarge,
        )
        OutlinedTextField(
            value = "Fusionadora Fiberfox",
            label = { Text("Product model:") },
            singleLine = true,
            onValueChange = {
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "123456Aa",
            label = { Text("Product S/N") },
            singleLine = true,
            onValueChange = {
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "123456Aa",
            label = { Text("Owner company:") },
            singleLine = true,
            onValueChange = {
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = "123456Aa",
            label = { Text("Service type:") },
            singleLine = true,
            onValueChange = {
            },
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Button(
            onClick = {},

        ) {
            Text("Add")
        }
    }
}
