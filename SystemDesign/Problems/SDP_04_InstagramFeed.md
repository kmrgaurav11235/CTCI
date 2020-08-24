# Instagram: Design of News Feed

## Features required

### Functional Requirements
1. Users should be able to upload/download/view photos.
2. Users can perform searches based on photo/video titles.
3. Users can follow other users.
4. The system should be able to generate and display a user’s News Feed consisting of top photos from all the people the user follows.

### Non-functional Requirements
1. Our service needs to be highly available.
2. Low latency is expected for viewing photos and News Feed generation.
3. Consistency can take a hit (in the interest of availability), if a user doesn’t see a photo for a while; it should be fine.
4. The system should be highly reliable; any uploaded photo or video should never be lost.

**Not in scope:** Adding tags to photos, searching photos on tags, commenting on photos, tagging users to photos, who to follow, etc.

## Design Considerations
* The system would be read-heavy, so we will focus on building a system that can retrieve photos quickly.
* Practically, users can upload as many photos as they like. Efficient management of storage should be a crucial factor while designing this system.

## Capacity Estimation and Constraints
* Let's assume we have 500M total users, with 1M daily active users.
* 2M new photos every day, 23 new photos every second.
* Average photo file size => 200KB
* Total space required for 1 day of photos: 2M * 200KB => 400 GB
* Total space required for 10 years: 400GB * 365 (days a year) * 10 (years) ~= 1425TB 

## High Level System Design
At a high-level, we need to support two scenarios, one to upload photos and the other to view/search photos. Our service would need some object storage servers to store photos and also some database servers to store metadata information about the photos.

## Database Schema
* We need to store data about users, their uploaded photos, and people they follow. 
* ***Photo table*** will store all data related to a photo; we need to have an *index* on (PhotoID, CreationDate) since we need to fetch recent photos first.
* A straightforward approach for storing the above schema would be to use an RDBMS like MySQL since we require joins. But relational databases come with their challenges, especially when we need to scale them.
* We can store photos in a distributed file storage like HDFS or S3.
* We can store the above schema in a distributed key-value store to enjoy the benefits offered by NoSQL. All the metadata related to photos can go to a table where the 'key' would be the *PhotoID* and the 'value' would be an object containing *PhotoLocation*, *UserLocation*, *CreationTimestamp*, etc.
* We need to store relationships between users and photos, to know who owns which photo. We also need to store the list of people a user follows. 
* For both of these tables, we can use a wide-column datastore like Cassandra. For the *UserPhoto* table, the 'key' would be *UserID* and the 'value' would be the list of *PhotoIDs* the user owns, stored in different columns. We will have a similar scheme for the *UserFollow* table.
* Cassandra or key-value stores in general, always maintain a certain number of replicas to offer reliability. Also, in such data stores, deletes don’t get applied instantly, data is retained for certain days (to support un-deleting) before getting removed from the system permanently.

![Database Schema](../Images/InstagramDatabase.png)

## Component Design
* Photo uploads (or writes) can be slow as they have to go to the disk, whereas reads will be faster, especially if they are being served from cache.
* We should keep in mind that web servers have a connection limit before designing our system. If we assume that a web server can have a maximum of 500 connections at any time, then it can’t have more than 500 concurrent uploads or reads. 
* Uploading users can consume all the available connections, as uploading is a slow process. This means that 'reads' cannot be served if the system gets busy with all the write requests. 
* To handle this bottleneck we can split reads and writes into separate services. We will have dedicated servers for reads and different servers for writes to ensure that uploads don’t hog the system.
* Separating photos’ read and write requests will also allow us to scale and optimize each of these operations independently.
* Losing files is not an option for our service. Therefore, we will store multiple copies of each file so that if one storage server dies we can retrieve the photo from the other copy present on a different storage server. Redundancy removes the single point of failure in the system.

![Component Design](../Images/InstagramDesign.jpg)

## Data Sharding
We can use two different schemes for metadata sharding: Based on UserID and Based on PhotoID.

### Partitioning based on UserID

* Let’s assume we shard based on the *UserID* so that we can keep all photos of a user on the same shard. 
* If one DB shard is 1TB, we will need four shards to store 3.7TB of data. 
* Let's assume for better performance and scalability we keep 10 shards.
* So we’ll find the Shard number by `UserID % 10` and then store the data there. 
* To uniquely identify any photo in our system, we can append shard number with each PhotoID.
* How can we generate PhotoIDs? Each DB shard can have its own auto-increment sequence for PhotoIDs and since we will append ShardID with each PhotoID, it will make it unique throughout our system.

**What are the different issues with this partitioning scheme?**
* How would we handle hot users? Several people follow such hot users and a lot of other people see any photo they upload.
* Some users will have a lot of photos compared to others, thus making a non-uniform distribution of storage.
* What if we cannot store all pictures of a user on one shard? If we distribute photos of a user onto multiple shards will it cause higher latencies?
* Storing all photos of a user on one shard can cause issues like unavailability of all of the user’s data if that shard is down or higher latency if it is serving high load etc.

### Partitioning based on PhotoID 
* If we can generate unique PhotoIDs first and then find a shard number through `PhotoID % 10`, the above problems will have been solved. We would not need to append ShardID with PhotoID in this case as PhotoID will itself be unique throughout the system.
* How can we generate PhotoIDs? Here we cannot have an auto-incrementing sequence in each shard to define PhotoID because we need to know PhotoID first to find the shard where it will be stored. 
* One solution could be that we dedicate a separate database instance to generate auto-incrementing IDs. If our PhotoID can fit into 64 bits, we can define a table containing only a 64 bit ID field. 
* So whenever we would like to add a photo in our system, we can insert a new row in this table and take that ID to be our PhotoID of the new photo.

**Wouldn’t this key generating DB be a single point of failure?**
* Yes, it would be. A workaround for that could be defining two such databases with one generating even numbered IDs and the other odd numbered.
* We can put a load balancer in front of both of these databases to round robin between them and to deal with downtime. Both these servers could be out of sync with one generating more keys than the other, but this will not cause any issue in our system. 
* We can extend this design by defining separate ID tables for Users, Photo-Comments, or other objects present in our system.
* Alternately, we can implement a 'key' generation scheme similar to what we have discussed in `URL shortener`: Zookeeper + Worker Threads.

## News Feed Generation
* For simplicity, let’s assume we need to fetch top 100 photos for a user’s News Feed. 
* Our application server will first get a list of people the user follows and then fetch metadata info of latest 100 photos from each user. 
* In the final step, the server will submit all these photos to our ranking algorithm which will determine the top 100 photos (based on recency, likeness, etc.) and return them to the user. 
* A possible problem with this approach would be higher latency as we have to query multiple tables and perform sorting/merging/ranking on the results. To improve the efficiency, we can pre-generate the News Feed and store it in a separate table.

### Pre-generating the News Feed
* We can have dedicated servers that are continuously generating users’ News Feeds and storing them in a *UserNewsFeed* table. So whenever any user needs the latest photos for their News Feed, we will simply query this table and return the results to the user.
* Whenever these servers need to generate the News Feed of a user, they will first query the UserNewsFeed table to find the last time the News Feed was generated for that user. Then, new News Feed data will be generated from that time onwards.

**What are the different approaches for sending News Feed contents to the users?**
1. ***Pull:*** Clients can pull the News Feed contents from the server on a regular basis or manually whenever they need it. Possible problems with this approach are: 
   1. New data might not be shown to the users until clients issue a pull request.
   2. Most of the time pull requests will result in an empty response if there is no new data.
2. ***Push:*** Servers can push new data to the users as soon as it is available. To efficiently manage this, users have to maintain a Long Poll request with the server for receiving the updates. A possible problem with this approach is, a user who follows a lot of people or a celebrity user who has millions of followers. In this case, the server has to push updates quite frequently.
3. ***Hybrid:*** We can adopt a hybrid approach. We can move all the users who have a high number of follows to a pull-based model and only push data to those users who have a few hundred (or thousand) follows. Another approach could be that the server pushes updates to all the users not more than a certain frequency, letting users with a lot of follows/updates to regularly pull data.

## Cache and Load balancing
* Our service would need a massive-scale photo delivery system to serve the globally distributed users. Our service should push its content closer to the user using a large number of geographically distributed photo cache servers and use ***CDNs***.
* We can introduce a cache for metadata servers to cache hot database rows. The Application servers can quickly check if the cache has desired rows before hitting database. 
* Least Recently Used (LRU) can be a reasonable cache eviction policy for our system. Under this policy, we discard the least recently viewed row first.
* How can we build more intelligent cache? If we go with 80-20 rule, i.e., 20% of daily read volume for photos is generating 80% of traffic which means that certain photos are so popular that the majority of people read them. This dictates that we can try caching 20% of daily read volume of photos and metadata.

## Source
* https://www.educative.io/courses/grokking-the-system-design-interview/m2yDVZnQ8lG
* https://www.youtube.com/watch?v=QmX2NPkJTKg
