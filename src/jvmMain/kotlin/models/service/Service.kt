package models.service

/**
 * Not in use yet.
 * Prepare for implement in the future.
 */
data class Service(
    val id: Int,
    val type: EnumService,
    var problem: String,
    var solution: String? = null,
    var income: String, //could be Date type
    var delivery: String //could be Date type
)

enum class EnumService{
    MAINTENANCE,
    INCIDENCE
}
