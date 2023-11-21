# ServicesLab

This is a project aimed at showcasing the usage of Kotlin Multiplatform 📱. It is also part of my ongoing learning process. The development has been structured in a way that allows us to abstract the database used from the code ☑

## Funcionality of project

Program to keep a record of products entering for repair or maintenance at a electronic laboratory.

## Getting Started

Follow these instructions to run on your local machine for development and testing purposes.
* Create a database, and create a table 'Products' on it.
* Establish a connection to the created database using the DatabaseConnection class.
```
Database.connect("jdbc:postgresql://localhost:5432/yourdbname", driver = "org.postgresql.Driver", user = "yourdbuser", password = "yourdbpassword")
```

### Prerequisites

* Install PostgreSQL ✔
* Install Java JDK ✔
* Install de Kotlin compiler, or use Gradle or Maven instead ✔


### Technologies

* Kotlin multiplatform
* Gradle
* PostgreSQL
* ORM: Exposed
