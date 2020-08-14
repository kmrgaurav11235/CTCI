# Horizontal vs Vertical Scaling
* Horizontal scaling means that you scale by adding more machines into your pool of resources whereas Vertical scaling means that you scale by adding more power (CPU, RAM) to an existing machine.
* In a database world horizontal-scaling is often based on the partitioning of the data i.e. each node contains only part of the data, in vertical-scaling the data resides on a single node and scaling is done through multi-core i.e. spreading the load between the CPU and RAM resources of that machine.
* With horizontal-scaling it is often easier to scale dynamically by adding more machines into the existing pool - Vertical-scaling is often limited to the capacity of a single machine, scaling beyond that capacity often involves downtime and comes with an upper limit.
* Good examples of horizontal scaling are Cassandra, MongoDB, Google Cloud Spanner. And a good example of vertical scaling is MySQL - Amazon RDS. It provides an easy way to scale vertically by switching from small to bigger machines. This process often involves downtime.

## Comparison
| Horizontal Scaling | Vertical Scaling |
| ------------- | ---------- | 
|  Load Balancing required | Not Required | 
| Resilient | Single point of failure |
| Network calls (RPCs) | Inter-process communication |
| Data Inconsistency problem needs to be solved | Data is Consistent |
| Scales well as Users increase | There is a hardware limit|
  
## Advantages of Horizontal Scaling
* Horizontal scaling is almost always more desirable than vertical scaling because you don’t get caught in a resource deficit. Instead of taking your server offline while you’re scaling up to a better one, horizontal scaling lets you keep your existing pool of computing resources online while adding more to what you already have. When your app is scaled horizontally, you have the benefit of elasticity.
* Instant and continuous availability
* No limit to hardware capacity
* Cost can be tied to use
* You’re not stuck always paying for peak demand
* Built-in redundancy
* Easy to size and resize properly to your needs

## How To Achieve Effective Horizontal Scaling
Best practices to keep in mind to make your service offering super compatible with horizontal scaling:
* The first is to make your application stateless on the server side as much as possible. Any time your application has to rely on server-side tracking of what it’s doing at a given moment, that user session is tied inextricably to that particular server. If, on the other hand, all session-related specifics are stored browser-side, that session can be passed seamlessly across literally hundreds of servers.
* The second goal is to develop your app with a service-oriented architecture. The more your app is comprised of self-contained but interacting logical blocks, the more you’ll be able to scale each of those blocks independently as your use load demands. Be sure to develop your app with independent web, application, caching and database tiers. This is critical for realizing cost savings – because without this Micro-service architecture, you’re going to have to scale up each component of your app to the demand levels of services tier getting hit the hardest.

## Sources
* https://www.youtube.com/watch?v=xpDnVSmNFX0&list=PLMCXHnjXnTnvo6alSjVkgxV-VH6EPyvoX&index=2
* https://stackoverflow.com/questions/11707879/difference-between-scaling-horizontally-and-vertically-for-databases
* https://www.missioncloud.com/blog/horizontal-vs-vertical-scaling-which-is-right-for-your-app