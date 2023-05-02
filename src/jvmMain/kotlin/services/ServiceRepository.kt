package services

import interfaces.ProductRepository
import repositories.exposed.ProductRepositoryExposed

object ServiceRepository {
    val productRepository : ProductRepository = ProductRepositoryExposed()
}