# System Design Concepts
Let us understand this using an example of a Pizza Shop. Say you have a Pizza shop with one Chef. If number of Orders start to increase, there will a point where that chef cannot handle all orders.

## Vertical Scaling
The first thing you can do to handle more Orders is to ask the chef to work harder:
Optimize processes and increase throughput using the same resource. This is **Vertical Scaling**.

## Preprocessing using cron jobs
You can prepare the ingredients of the Pizza Order beforehand at non-Peak hours. This is **Preprocessing** and it is done generally using **Cron jobs**.

## Back-up Servers
If the chef is sick for a day, you will not do any business that day. The one chef represents a **Single Point of Failure**. So, you can hire a Back-up chef. In case the main chef cannot work, you use the Back-up chef. This makes our System **Resilient**.

## Horizontal Scaling
If your business keeps growing, you can hire more chefs and keep several more in the back-up. This hiring of more resources is **Horizontal Scaling**. It means buying more machines of similar type to get more work done.

## Microservices
Let us say we have 3 chefs - 2 are good at making Pizza while the other one is good at making Garlic bread. If you are receiving two types of incoming orders - Pizza and Garlic Bread, how do you assign them to the chefs?
* You can randomly assign any type of order to any chef. But this is clearly not the best way to use your employees.
* Instead you should build on their strengths and route all Garlic Bread orders to Chef-3 and Pizza orders to Chef-1 or Chef-2. This is also a better solution because:
  * If you need to change the recipe of Pizza you need only talk to Chef-1 and Chef-2. Chef-3 doesn't needs to be bothered. 
  * If you need to know status of a Garlic Bread order, you need to go to Chef-3 only.
* In fact instead of just one chef for Garlic Bread, you can have a team of chefs. Similar teams for Pizza. This will allow you to scale the Garlic Bread and the Pizza teams at a different rate in accordance with the requirements.
* This is a ***Microservice Architecture***.

## Distributed Systems
* What if you have a Power outage for a day -- you will not do any business that day. So, you want to buy a separate Pizza shop at another place which can also deliver Pizza. This takes back-up to another level.
* We have just introduced a lot of complexity to the system. We need to be able to route the orders to one of the two shops. This is the concept of ***Distributed System***.

## Load Balancing
* When the Customer sends a request for Pizza, it needs to be served at one of the shops. We need to be able to route the orders to one of the two shops depending on who can serve the Order faster. e.g. Shop-1 may be closer the customer but might have a bigger wait time. So, an intelligent business decision needs to be made here.
* We can create an entity for the purpose of routing the requests. This is a ***Load Balancer***.

## Decoupling
* At this point, we realize that the Delivery Agent and the Pizza Shop has nothing in common. The Delivery Agent is just interested in delivering the package to the customer as soon as possible. Similarly, the Pizza shop doesn't cares if the pick-up is done by the Delivery Agent or the Customer himself.
* So, we are observing a separation of responsibility. This is ***Decoupling***.

## Logging and metrics calculation
* Let us say Shop-1 has a faulty oven and their churning rate goes down. Or there is problem with the Delivery Agent's vehicle. You need to keep track of all these things.
* This includes ***Auditing, Logging, Metrics and Analytics***.

## Extensibility
* The final idea is to keep the system ***Extensible*** so that we can add new features without a lot of changes.
* e.g. the Delivery Agent doesn't needs to know that they are delivering a Pizza; it can be burger tomorrow.

## Sources:
* https://www.youtube.com/watch?v=SqcXvc3ZmRU&list=PLMCXHnjXnTnvo6alSjVkgxV-VH6EPyvoX&index=2