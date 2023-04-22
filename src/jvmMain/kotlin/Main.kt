// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import models.DatabaseConnection
import services.ServiceRepository
import ui.AddProductForm
import ui.ProductsList

/**
 * Program to have register of the income products to repair or maintenance.
 * There are only one table in DB, Products.
 * It uses 'exposed' like intermediate between postgresSql.
 */

@Composable
@Preview
fun App() {

    MaterialTheme() {
        LazyRow() {
            item { AddProductForm() }
            //item { ProductsList() }
        }
    }
}

fun main() = application {
    //connect to database
    DatabaseConnection(ServiceRepository.productRepository).connect()

    //ui
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}


