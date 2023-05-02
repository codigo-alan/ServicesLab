package repositories.exposed

import interfaces.ProductRepository
import models.product.Product
import models.product.exposed.ProductDaoTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepositoryExposed : ProductRepository {

    private var products = listOf<Product>()

    override fun list(): List<Product> {
        transaction {
            products  = ProductDaoTable.selectAll().map(::dbToModel)
        }
        return products
    }

    override fun insert(product: Product) {
        transaction {
            ProductDaoTable.insert {
                it[serialNumber] = product.serialNumber
                it[model] = product.model
                it[owner] = product.owner
                it[service] = product.service
            }
        }
    }

    override fun delete(serialNumber: String) {
        transaction {
            ProductDaoTable.deleteWhere { ProductDaoTable.serialNumber eq serialNumber }
        }
    }

    override fun searchByOwner(owner: String): List<Product> {
        transaction {
            products = ProductDaoTable.select { ProductDaoTable.owner.lowerCase() eq owner.lowercase() }.map(::dbToModel)
        }
        return products
    }

    override fun update(productSerialNumber: String, newProduct: Product) {
        transaction {
            ProductDaoTable.update({ ProductDaoTable.serialNumber eq productSerialNumber }){
                it[serialNumber] = newProduct.serialNumber
                it[model] = newProduct.model
                it[owner] = newProduct.owner
                it[service] = newProduct.service
            }
        }
    }

    private fun dbToModel(resultRow: ResultRow): Product =
        Product(resultRow[ProductDaoTable.serialNumber],
                resultRow[ProductDaoTable.model],
                resultRow[ProductDaoTable.owner],
                resultRow[ProductDaoTable.service]
        )
}