package interfaces

import models.product.Product

interface ProductRepository {
    fun list(): List<Product>
    fun insert(product: Product)
    fun delete(serialNumber: String)
}