package models.product

/**
 * Product that needs a service.
 */
data class Product(
    val serialNumber: String,
    val model: String,
    val owner: String, //could be the name of the company or could have a table of clients in BD and use the id for this field.
    val service: String //could be another table with data type Service.
)