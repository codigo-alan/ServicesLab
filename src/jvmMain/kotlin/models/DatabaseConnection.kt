package models

import interfaces.ProductRepository
import models.product.exposed.ProductDaoTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import repositories.exposed.ProductRepositoryExposed

class DatabaseConnection(private val productRepository: ProductRepository) {

    fun connect(){
        if (productRepository is ProductRepositoryExposed){
            Database.connect("jdbc:postgresql://localhost:5432/serviceslab", driver = "org.postgresql.Driver", user = "serviceslab", password = "serviceslab") //Alan.
            //Database.connect("jdbc:postgresql://localhost:5432/serviceslab", driver = "org.postgresql.Driver", user = "sjo") //ITB.

            transaction {
                addLogger(StdOutSqlLogger)
                //createTable if not exists
                SchemaUtils.create(ProductDaoTable)
            }
        }
    }

}