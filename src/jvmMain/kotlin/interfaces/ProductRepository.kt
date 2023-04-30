package interfaces

import models.product.Product

interface ProductRepository {
    fun list(): List<Product>
    fun insert(product: Product)
    fun delete(serialNumber: String)
    fun searchByOwner(owner: String) : List<Product>
    fun update(productSerialNumber: String, newProduct: Product)
}