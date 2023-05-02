package models.product.exposed

import org.jetbrains.exposed.sql.Table

object ProductDaoTable: Table() {
    var serialNumber = varchar("serialNumber", 20)
    var model = varchar("model", 20)
    var owner = varchar("owner", 20)
    var service = varchar("service", 20)
    override val primaryKey: PrimaryKey = PrimaryKey(serialNumber)
}