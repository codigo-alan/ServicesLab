package models.product.exposed

import org.jetbrains.exposed.dao.id.IntIdTable

object ProductDaoTable: IntIdTable() {
    var serialNumber = varchar("serialNumber", 20)
    var model = varchar("model", 20)
    var owner = varchar("owner", 20)
    var service = varchar("service", 20)
}