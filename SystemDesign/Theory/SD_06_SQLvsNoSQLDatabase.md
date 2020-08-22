# SQL vs NoSQL Databases

## SQL/Relational Database
* The data is well structured, and records are kept into tables. 
* Tables consist of rows, primary keys, unique keys and tables can join between each other. 
* Uses Structured Query Language (***SQL***) for defining and manipulating data.
* It supports is transactions and its properties are termed as ***ACID***. 
* Relational databases are mostly vertically scaled. You need to add more memory, CPU or storage to the same server to process more data.
* Most suitable for a system where data is structured and data format is known e.g Transactional System, Auditing System etc.

### ACID Property
ACID properties ensure that once a transaction is complete, It’s data is consistent and stable on the disk. It follows a ***Strong Consistency Model***.

1. ***Atomicity (A):*** A transaction consists of many operations. All operation in a transaction succeeds or every operation is rolled back.
2. ***Consistency (C):*** After a transaction completes database must be in a consistent state. A transaction can only bring the database from one consistent state to another.
3. ***Isolation (I):*** There can be many transactions in the system executing concurrently but each transaction execution is isolated from another and looks like they were executed in sequential order.
4. ***Durability (D):*** The result of applying transaction is permanent, even in the presence of failures.

### Disadvantages of Relational Databases:
1. ***Schema:*** The schema of the relational database is fixed, and you have to design it before-hand. For rapidly growing applications it’s tough to assume the complexity of the data and then design it.
2. ***Data Structure:*** As the data structures supported by the relational databases are limited, we need to do a lot more join to get the desired data and joins are always costly.
3. ***Scaling:*** It is difficult to scale the relational database as the data grows very rapidly.

### Workarounds
* ***Denormalization:***  Expand a single table and add more columns to it, so that join can be avoided while fetching results. This improves the read performance but introduces data anomalies.
* ***Sharding:*** In this technique, the database can be divided and pieces of it can be stored in different servers. This improves both read and write performance, but it is very difficult to manage.

## NoSQL databases
* Data is stored in different formats like key-value pair, document, graph, column etc.
* NoSQL databases are used to store unstructured data. There is no need to define the schema. Add fields as you go.
* No standard language for defining and manipulating data. Different NoSQL databases use different syntax.
* NoSQL databases are horizontally scalable. To scale the database, need to add more cheap commodity servers.
* NoSQL databases relax on the ***ACID constraint***. Though ACID is an important constraint for some applications but not for all. Most NoSQL databases provide ***BASE Consistency model (Eventual consistency)***. They choose availability over consistency.
* Most suitable for the use cases where data is unstructured and the format is unknown e.g. social media, real-time streaming systems etc.

### BASE Consistency Model
ACID is less fashionable in the NoSQL world. BASE provides eventual consistency over immediate consistency for scalability, availability, and resiliency. Databases with BASE consistency model (NoSQL databases) prefers availability over the consistency of replicated data at write time. BASE is less strict than ACID. Data will be consistent in the future either at the read time or it will be always consistent.

1. ***Basic Availability (BA):*** The database is available most of the time.
2. ***Soft-State (S):*** Stores don’t have to be write consistent. No two different replicas have to be mutually consistent all the time.
3. ***Eventual Consistency (E):*** In the absence of updates, data will eventually reflect the last updated value across the system.

### Advantages of NoSQL Databases:
1. ***Flexible Schema:*** It typically provides a very flexible schema. Very easily the schema can be changed, based on requirements.
2. ***Horizontal scaling:*** It allows to add cheaper, commodity server whenever required. Whereas SQL databases needed scale-up vertically whenever exceeds the capacity (Migrate to a larger server).
3. ***Faster Queries:*** One key principle of NoSQL databases is `Data that is accessed together should be stored together`. So, queries typically work without join which makes the queries faster.

## Sources
* https://www.youtube.com/watch?v=xQnIN9bW0og
* https://medium.com/swlh/everything-you-should-know-about-nosql-database-system-design-b3716a3663cf
* https://medium.com/system-design-blog/sql-vs-nosql-databases-6896a8cb1800
