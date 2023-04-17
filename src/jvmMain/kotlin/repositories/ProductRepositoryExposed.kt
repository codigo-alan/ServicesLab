package repositories

import interfaces.ProductRepository
import models.product.Product
import models.product.exposed.ProductDaoTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
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
        TODO("Not yet implemented")
    }

    private fun dbToModel(resultRow: ResultRow): Product =
        Product(resultRow[ProductDaoTable.serialNumber],
                resultRow[ProductDaoTable.model],
                resultRow[ProductDaoTable.owner],
                resultRow[ProductDaoTable.service]
        )
}