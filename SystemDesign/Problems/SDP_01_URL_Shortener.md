# Design a tiny URL or URL shortener
URL shortening service - A web service that provides short aliases for redirection of long URLs.

## Solution
Watch this video first: https://www.youtube.com/watch?v=JQDHz72OA3c

## Traffic:
Consider we will have 30M service calls per month (1M calls/day). If we are going to keep our service for 5 years, our service will generate about 1.8B records.

## URL length:
Shortened URL can be combinations of numbers(0–9) and characters(a-Z) of length 7.

## Data capacity modeling:
1. Considering average long URL size of 2KB ie for 2048 characters
2. short URL size of about 17 Bytes for 17 character
3. created_at — 10 bytes
4. expiration_length_in_minutes — 10 bytes
5. which gives a total of 2.037 K Bytes

So have 30 M active users so total size = 30000000 * 2.031 = 60780000 KB = 60.78 GB per month
In a Year 0.7284 TB and in 5 year 3.642 TB of data

Think about the number of reads and writes happens to the system!!!

## Shortening logic:

Given a long URL, how can we find hash function F that maps URL to a short alias

Let’s use base62encoder? Base62 encoder contains A-Z , a-z, 0–9 (total: 26 + 26 + 10 = 62) basically converting a base10 number to hash, whenever we get a long URL -> get a random number and convert to base62 and use the hash as short URL id.
Or,
You can use the first few characters of MD5(long_url) hash output.

```java
// Method to generate a short url from integer ID  
public String idToShortURL(int n)  
{  
    // Map to store 62 possible characters  
    char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
    
    StringBuffer shorturl = new StringBuffer();  
    
    // Convert given integer id to a base 62 number  
    while (n > 0)  
    {  
        // use above map to store actual character  
        // in short url  
        shorturl.append(map[n % 62]); 
        n = n / 62;  
    }  
    
    // Reverse shortURL to complete base conversion  
    return shorturl.reverse().toString();  
}  
    
// Method to get integer ID back from a short url  
public int shortURLtoID(String shortURL)  
{  
    int id = 0; // initialize result  
    
    // A simple base conversion logic  
    for (int i = 0; i < shortURL.length(); i++)  
    {  
        if ('a' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'z') {
            id = id * 62 + shortURL.charAt(i) - 'a';  
        } else if ('A' <= shortURL.charAt(i) && shortURL.charAt(i) <= 'Z') {
            id = id * 62 + shortURL.charAt(i) - 'A' + 26;
        } else if ('0' <= shortURL.charAt(i) && shortURL.charAt(i) <= '9') {
            id = id * 62 + shortURL.charAt(i) - '0' + 52;
        }
    }  
    return id;  
}  
```

## Database

### SQL:
We can use RDBMS which provides ACID properties but the problem is scaling (yes you can shard but that increases the complexity of design). As we have 30M active users so there will be conversions and a lot of Short URL resolution and re-directions — so hell lot of reads and writes. Am not sure RDBMS will scale to this traffic.

### NoSQL:
The data is eventually consistent, but it is easy to scale and it is a highly available database.

## Techniques to store Tiny URL

### Technique 1
* Checks whether generated TinyURL is present in DB (doing get(tiny) on DB).
* If tinyURL isn’t present in DB then put longURL and tinyURL in DB (`put(tinyURL, longURL)`).

**Problem:** This technique creates race condition because it’s possible that two threads may be simultaneously adding same data to DB and may end up corrupting data. If it was simply one server service then it might work, but we are talking about scale!!
**Solution:** Adding TinyURL and long URL in DB, if there is no key whose value is equal to TinyURL i.e.
`putIfAbsent(TinyURL, long URL)` or `INSERT-IF-NOT-EXIST` and it Requires support from DB support
In this case, we might can’t use NoSQL as `putIfAbsent` feature support might not be available as the data is eventual consistency, even DB cant perform this operation

### Technique 2 (MD5 Approach)
* Calculate MD5 of long URL
* Take the first 7 chars and use that to generate TinyURL
* Now check the DB (using technique 1 discussed above) to verify that TinyURL is not used already

**Advantages of MD5 approach:** For two users using same long URL, in a random generation, we generate two random TinyURL, so 2 rows in DB. In MD5 technique, MD5 of long URL will be same for both the users and hence same first 43 bits of URL will be same and hence this will save some space as we will only create one row, saving space

**Disadvantages of MD5 approach:** Again we cant use NoSQL as there is no support for putIfAbsent

### Technique 3 — Counters
* A single server is responsible for maintaining a counter e.g. database,
* When the worker host receives request they talk to counter, which returns a unique number and increments the counter.
* Every worker host gets a unique number which is used to generate TinyURL.

**Disadvantages:** The Single point of failure and Single point of bottleneck

**Before learning about the next approach lets learn something about Zookeeper**
ZooKeeper is a distributed coordination service to manage a large set of hosts. Coordinating and managing a service in a distributed environment is a complicated process. ZooKeeper solves this issue with its simple architecture and API. ZooKeeper allows developers to focus on core application logic without worrying about the distributed nature of the application.

A distributed application can run on multiple systems in a network at a given time (simultaneously) by coordinating among themselves to complete a particular task in a fast and efficient manner. more workers more work done faster

The challenges of using a distributed system to get work done are

* **Race condition** − Two or more machines trying to perform a particular task, and leads to race around condition
* **Deadlock** — two or more operations waiting for each other to complete indefinitely
* **Inconsistency** − Partial failure of data.

Now let’s talk about how to maintain a counter(which we discussed earlier) when we have distributed hosts using Zookeeper.

* We take 1st billion combinations from 3.5 trillion combination. We divide the 1st billion into 1000 ranges of 1 million each which is maintained by Zookeeper. 1 -> 1,000,000 (range 1) 1,000,000 -> 2,000,000 (range 1) …. (range n)999,000,000 -> 1,000,000,000 ((range 1000))
* Worker thread will come to Zookeeper and ask for an unused range. Suppose W1 is assigned range 1, W1 will keep incrementing the counter and generate the unique number and generate TinyURL based on that.
* When a worker thread exhausts their range they will come to Zookeeper and take a fresh range.
* This guarantees No Collision of tiny URL.
* Addition of new worker threads is easy.
* Worst case is we will lose a million combinations (if a worker dies) but that’s not as severe as we have 3.5 trillion combinations.
* When 1st billion is exhausted, we will take 2nd billion and generate ranges to work with.

## Sources
* https://medium.com/@narengowda/url-shortener-system-design-3db520939a1c
* https://www.youtube.com/watch?v=JQDHz72OA3c
* https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
* https://www.youtube.com/watch?v=fMZMm_0ZhK4