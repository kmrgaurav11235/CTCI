# Database Sharding

## What is Sharding or Data Partitioning?
* ***Sharding*** (also known as ***Data Partitioning***) is the process of splitting a large dataset into many small partitions which are placed on different machines. Each partition is known as a ***Shard***.
* Each Shard has the same database schema as the original database. Most data is distributed such that each row appears in exactly one shard. The combined data from all shards is the same as the data from the original database.
* The Sharded architecture is transparent to the client application. The client application keeps on talking to the database shards(partitions) as if it was talking to a single database. 

## What scalability problems are solved by Sharding?
* As more users are onboarded on your system, you'll experience performance degradation with a single database server architecture. Your read queries and updates will start become slower and your network bandwidth may be start to saturate . You'll probably start running out of disk space on your database server at some point.
* Sharding helps to fix all the above issues by distributing data across a cluster of machines. In theory, you can have a huge number of shards thereby providing virtually unlimited horizontal scaling for your database.

## What are some common Sharding or Data Partitioning Schemes?
There are four common sharding strategies:

### Horizontal or Range Based Sharding 
* In this case, the data is split based on the value ranges that are inherent in each entity. 
* For example, if you store the contact info for your online customers, you might choose to store the info for customers whose last name starts with A-H on one shard, while storing the rest on another shard.
* The disadvantage of this scheme is that the last names of the customers may not be evenly distributed. You might have a lot more customers whose names fall in the range of A-H than customers whose last name falls in the range I-Z. In that case, your first shard will be experiencing a much heavier load than the second shard and can become a system bottleneck.
* Nevertheless, the benefit of this approach is that it's the simplest sharding scheme available. Each shard also has the same schema as the original database. 
* Your application layer is relatively simple because in most scenarios, you'll not need to combine data from multiple shards to answer any query.
* It works well for relative non static data -- for example to store the contact info for students in a college because the data is unlikely to see huge modifications. 

### Vertical Sharding
* In this case, different features of an entity will be placed in different shards on different machines. 
* For example, in a LinkedIn like application, an user might have a profile, a list of connection and a set of articles he has authored. In Vertical sharding scheme, we might place the various user profiles on one shard, the connections on a second shard and the articles on a third shard.
* The main benefit of this scheme is that you can handle the critical part of your data (for examples User Profiles) differently from the not so critical part of your data (for example, blog posts) and build different replication and consistency models around it.
* The two main ***disadvantages*** of vertical sharding scheme are as follows:
  1. Depending on your system, your application layer might need to combine data from multiple shards to answer a query. For example, a profile view request will need to combine data from the User Profile, Connections and Articles shard. This increases the development and operational complexity of the system.
  2. If your Site/system experiences additional growth then it may be necessary to further shard a feature specific database across multiple servers.

### Key or Hash based sharding
* In this case, an entity with a value (e.g. IP address of a client application) can be used as an input to a hash function and a resultant hash value generated. This hash value determines which database server(shard) to use.
* As a simple example, imagine you have 4 database servers and each request contained an application id which was incremented by 1 every time a new application is registered.
* In this case, you can simply perform a modulo operation on the application id with the number 4 and take the remainder to determine which server the application data should be placed on. 
* The main drawback of this method is that Elastic Load Balancing (dynamically adding/removing database servers) becomes very difficult and expensive. For example, if we wanted to add 6 more servers, majority of the keys would need to be remapped and migrated to new servers. Also, the hash function will need to be changed from modulo 4 to modulo 10.
* While the migration of data is in effect , neither the new nor the old hash function is fully valid. So in effect, a large number of the requests cannot be serviced and you'll incur a downtime till the migration completes.
* This problem is easily solved by ***Consistent Hashing***. 

### Directory based sharding
* Directory based shard partitioning involves placing a lookup service in front of the sharded databases. The lookup service knows the current partitioning scheme and keeps a map of each entity and which database shard it is stored on. The lookup service is usually implemented as a Web-Service.
* The client application first queries the lookup service to figure out the shard (database partition) on which the entity resides/should be placed. Then it queries / updates the shard returned by the lookup service.

## What are the common problems with Sharding?

### Database Joins become more expensive and not feasible in certain cases
* When all the data is located in a single database, joins can be performed easily. Now, when you shard the database, joins have to be performed across multiple networked servers which can introduce additional latency for your service. 
* Additionally, the application layer also needs additional level of asynchronous code and exception handling which increases the development and maintenance cost.

### Sharding can compromise database referential integrity
* Most RDBMS do not support foreign keys across databases on different database servers. This means that applications that require referential integrity often have to enforce it in application code and run regular SQL jobs to clean up dangling references once they move to using database shards.
* If an application must modify data across shards, evaluate whether complete data consistency is actually required. Instead, a common approach in the cloud is to implement ***Eventual Consistency***. 

### Database schema changes can become extremely expensive
* In some situations as your user base grows, the schema might need to evolve. 
* For example, you might have been storing user picture and user emails in the same shard and now need to put them on different shards. This means that all your data will need to be relocated to new location. This can cause down-times in your system.

### Auto-increment key management
* Typical auto-increment functionality provided by database management systems generate a sequential key for each new row inserted into the database. 
* This is fine for a single database application, but when using Database Sharding, keys must be managed across all shards in a coordinated fashion.

### Reliability
The database tier is often the single most critical element in any reliability design, and therefore an implementation of Database Sharding is no exception. In fact, due to the distributed nature of multiple shard databases, the criticality of a well-designed approach is even greater. To ensure a fault-tolerant and reliable approach, the following items are required:
* ***Automated backups*** of individual Database Shards.
* ***Database Shard redundancy***, ensuring at least 2 "live" copies of each shard are available in the event of an outage or server failure. This requires a high-performance, efficient, and reliable replication mechanism.
* Cost-effective hardware redundancy, both within and across servers.
* Automated failover when an outage or server failure occurs.
* Disaster Recovery site management.

## When to use the Sharding in a System Design Interview?
* Use this pattern when a data store is likely to need to scale beyond the resources available to a single storage node, or to improve performance by reducing contention in a data store.
* For example, if you're designing the next Netflix, you'll need to store and provide low latency reads to a huge number of video files. In this case you might want to shard by the genre of the movies. You'll also want to create replicas of the individual shards to provide high availability.

## Sources
* https://www.acodersjourney.com/database-sharding/
* https://www.youtube.com/watch?v=5faMjKuB9bc&list=PLMCXHnjXnTnvo6alSjVkgxV-VH6EPyvoX&index=7
* https://medium.com/system-designing-interviews/system-design-chapter-2-sharding-484960c18f
