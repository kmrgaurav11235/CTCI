# Twitter

## Features required
* The user should be able to tweet as fast as possible.
* The user should be able to see Tweet Timelines:
  1. __User timeline:__ Displaying user's tweets and retweets.
  2. __Home timeline:__ Displaying Tweets from people user follow.
  3. __Search timeline:__ Display search results based on hashtags or search keyword.
* The user should be able to follow another user.
* Users should be able to tweet millions of followers within a few seconds.
* The user should be able to see the trending hashtags.

## Design Considerations
* Twitter now has 300M worldwide active users. Every second on an average, around 6000 tweets are tweeted on Twitter. Also, every second 6,00,000 Queries made to get the timelines.
* So, Twitter is read heavy.
* It is ok to have Eventual consistency. If the User sees the tweets a bit delayed, it is not a big issue.
* Space is not a problem as tweets are limited to 140 characters.

## Data Storage
* We will use Redis to access most of the information faster and also to store data.
* We will also store a copy of tweet and other User related info in Database.
* Basic Architecture of Twitter service consists of a User Table, Tweet Table, and Followers Table
  * User Information is stored in ***User Table***.
  * When a User tweets, it gets stored in the ***Tweet Table*** along with _UserID_. User Table will have One-to-Many relationships with Tweet Table. 
  * Save retweets as tweets with original tweet reference.
  * When a user follows another user, it gets stored in ***Followers Table***.
* Gizzard is Twitter’s distributed data storage framework built on top of MySQL (InnoDB). 
* Gizzard handles sharding, replicating N copies of the data, and job scheduling.
* Cassandra is used for high velocity writes and lower velocity reads. The advantage is Cassandra can run on cheaper hardware than MySQL, it can expand easier.
* Hadoop is used to process unstructured and large datasets, hundreds of billions of rows.
* Vertica is being used for analytics and large aggregations and joins so they don’t have to write MapReduce jobs.

## User timeline
* Fetch all the tweets from Tweet Table/Redis for a particular user. This also includes retweets.
* Display it on user timeline, order by date time.
* Save the timelines of popular users in the cache for Optimization. e.g. Celebrities whose timelines are accessed million times.

## Home timeline
This is same as `News Feed Generation` in Instagram.
1. Pre-compute the Home timeline of User A with everyone (using ***Push-based approach***) except celebrity tweets.
2. When User A access his timeline his tweet feed is merged with celebrity tweet at load time using ***Pull-based approach***.
3. Don’t pre-compute timeline for inactive users who don't log in to the system for more than X days (e.g. 7 days).

## Search timeline
* _Early Bird_ uses ***Inverted Full-text Index***. This means that it takes all the documents, splits them into words, and then builds an index for each word. 
* Since the index is an exact string-match and unordered, it can be extremely fast. An unordered SQL index on a varchar field could be very fast.
* It has to ***Scatter-n-Gather*** across the data-centers. It queries every Early Bird shard and asks -- do you have content that matches this query? All shards are queried, the results are returned, sorted, merged, and re-ranked. _Re-rank_ is by social proof, which means looking at the number of retweets, favorites, and replies.

## Trending topics
* Twitter uses Apache Storm and Heron framework to compute trending topics.
* These tasks run on many containers. The applications perform a real-time analysis of all tweets sent on Twitter which can be used to determine the _Trending Topics_.
* Basically, it involves the counting of the most mentioned terms in tweets. The method is known in the domain of data analysis for the social network as _Trending Hashtags_ method. 
* Important criteria in the selection of _Trending Hashtags_:
  1. The number of mentions of a subject.
  2. Total time taken to generate that volume of tweets

![Twitter Trending Topics](../Images/TwitterTrendingTopics.png)

* __TweetSpout:__ The component used for issuing the tweets in the topology.
* __TweetFilterBolt:__ Reads the tweets issued by the _TweetSpout_ and executes the filtering. Only tweets that contain coded messages using the standard Unicode. Also, Adult topic filtering, Copyright violation checks etc. are made.
* __ParseTweetBolt:__ Processes the filtered tweets issued as tuples (immutable lists) by _TweetFilterBolt_. At this level, we have the guarantee that each tweet contains at least one hashtag.
* __CountHashtagBolt:__ Takes the tweets that are parsed through _ParseTweetBolt_ and counts each hashtag. This is to get the hashtag and number of references to it.
* __TotalRankerBolt:__ Creates a ranking of all the counted hashtags. It converts count to ranks in one more pipeline.
* __GeoLocationBolt:__ It takes the hashtag issued by the _ParseTweetBolt_, with the location of the tweet. e.g. a very popular Rugby game in Australia might not be relevant in India.
* __CountLocationHashtagBolt:__ Presents a functionality similar to the component CountHashtagBolt uses one more dimension ie. Location
* __RedisBolt:__ Inserts data into Redis.

## Full Design
![Twitter Design](../Images/TwitterDesign.jpg)

## Sources
* https://medium.com/@narengowda/system-design-for-twitter-e737284afc95
* https://www.youtube.com/watch?v=KmAyPUv9gOY
