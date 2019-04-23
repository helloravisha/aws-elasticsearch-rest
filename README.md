# AWS Elastic Search - Search Microservice

## Project Requirement

Using Java, write a micro service that invokes AWS elastic search and make it available using API gateway.             

1. Test Data - http://askebsa.dol.gov/FOIA%20Files/2017/Latest/F_5500_2017_Latest.zip             
2. Search should be allowed by Plan name, Sponsor name and Sponsor State             
3. Use AWS best practices        


## System  Architecture
Following is my current system architecture for building Highy Available  and Scalable system by leveraging different AWS components.

![Alt text](docs/Architecture.png)  


## Service  Architecture
Spring boot is used to develop the complete  micro service system, following is the high
level micro service acrchitecture of the services in the system.

![Alt text](docs/Service-Architecture.png)       


## Final Deliverables (API's )

With the above architectural components in place, following are the three API's  exposed using  Amazon  API Gateway. All the three methods provides optional pagination along
with  the mandatory query parameters "sponsorname", "sponsorstate" or  "planname".

* <B>With Pagination</b> : You can mention the number of records along with the offset,  here you can pass two optional  extra parameters "size" or "offset" along with the mandatory parameter.
                    
* <b>Without Pagination</b> : By default every  API returns Max 10 records, here you should pass the required  mandatory parameter.

##### Output Fields ( ResponseBody)
For Testing,  i had considered only the following fields. Therefore the API will return response only with the following fields. We 
can update the model as per our business requirement. 

* FILING_STATUS 
* PLAN_NAME 
* SPONS_SIGNED_NAME 
* SPONS_DFE_MAIL_US_STATE 
* SPONS_DFE_LOC_US_CITY 
* BUSINESS_CODE  


### 1. Search by Sponsor Name

* ##### Without Pagination 
https://5g7l7uaz82.execute-api.us-east-2.amazonaws.com/dev?sponsorname=david

* ##### With Pagination
https://5g7l7uaz82.execute-api.us-east-2.amazonaws.com/dev?sponsorname=david&size=5&offset=1

### Sample Output
![Alt text](docs/SponsorName.png)

### 2. Search by Sponsor State

#### Without Pagination  
https://5g7l7uaz82.execute-api.us-east-2.amazonaws.com/dev?sponsorstate=TX

#### With Pagination 
https://5g7l7uaz82.execute-api.us-east-2.amazonaws.com/dev?sponsorstate=TX&size=12&offset=1

### Sample Output
![Alt text](docs/SponsorState.png)

### 3. Search by Plan Name

#### Without Pagination , By default this API returns Max 10 records. 
https://5g7l7uaz82.execute-api.us-east-2.amazonaws.com/dev?planname=401k

#### With Pagination , You can mentione the number of records along with the offset.
https://5g7l7uaz82.execute-api.us-east-2.amazonaws.com/dev?planname=401k&size=2&offset=1

### Sample Output
![Alt text](docs/PlanName.png)


## System Components in Detail

## AWS Elastic Search

Elasticsearch is an open-source, RESTful, distributed search and analytics engine built on Apache Lucene, when it comes to 
Amazon Elasticsearch Service, it  is a fully managed service that is provided by amazon, Basically it provides two different services
one is a single node service and the other is a clustered service with different master nodes and deploying them in different
availability zones for high availability. Along with this,  it also offers Kibana a visual dashbaord by default,  when
we subscribe for Elastic search service. Amazon also provides certain plugins with different log shippers like logstash for 
ingesting the required data to elastic search. 

## Configuartion 
![Alt text](docs/Elastic-Search-Instance.png)


## Logstash Data Ingestion
In todays cloud and IOT world with data growing exponentially data ingestion plays an important role. Therefore, there are different open source  data ingestion tools  for ingesting the data into different repositories. In the same context the term log shippers is used, there are different 
log shippers available in todays cloud world like Filebeat, Logstash, Fluentd, etc. I had leveraged logstash here for ingesting the given test
data into elastic search server as Amazon provides elastic search output channel plugin that can
be used with logstash, this plugin helped me in pushing the huge csv file into elastic search
by doing bulk upload. 

## Configuartion   
![Alt text](docs/Logstash-Config.png)


## Elastic Container Service
Containers are the next level of virtual computing that came after virtual machines. 
Containers are easily deployable with different services like Docker, Rocket etc. Containers
are the most frequently used packaging component in today cloud world,Therefore container orchestartion
is the most important task in today's container world and today we have different container orchestartion
frameworks like Kubernetes, Docker Swarm and many managed services like GKE, AKE, OKE and ECS.
I had leveraged ECS from Amazon for container orchestartion services by leveraging the autoscaling group\
which provides high availability. 

## Cluster Details 
![Alt text](docs/ECS-Cluster.png)


## Configuartion
![Alt text](docs/ECS-AutoScaling-Group.png)



## Load Balancer
Load balancer provides high availability to the system, so i had leveraged elastic load balancer 
provided by AWS deployed across multiple Availability Zones. Load balancer in my current system
talks to ECS for providing the required services. One point to note here is, AWS Load balancer requires certain
health check point to be configured , i had leveraged spring actuator health endpoint for getting the health of the my system. 
 
## Configuartion 
![Alt text](docs/Loadbalancer.png)


## API Gateway
API Gateway is one of the design pattern that is used with micro service based architecture, It acts as a single point of entry
for all the micro services in the system. Amazon provdes the same system with the component named
Amazon API Gateway. Apart from acting as a single point of entry, Amazon API gateway provides numerous
other features like authenication, documentation, Testing, Monetization of your API by resting number of calls to API , Securing from DDOS attacks and 
many more.  

### API Creation

![Alt text](docs/API-Gateway.png)

### API Configuration

API Gateway Configuration for the given API, if you see on the right side of the image here, we 
can see the reference to load balancer. In the current System API gateway is configured to load balancer than directly
interacting with ECS for better availability. 
 
![Alt text](docs/API-Gateway-Configuration.png)


## Docker - Image
Building and publishing docker images to reposoitries is part of continuous integration. Therefore my gradle script
is responsible for pushing an publishing  docker image of my Spring Boot application to docker registry.



### Steps
1. Build a docker image of the Spring Boot application
2. Push the Docker image to dockerhub

My Docker Repo , where the docker image is published. 
![Alt text](docs/My-DockerHub-Repo.png)

### Gradle Docker

Gradle kick starting building and  pushing of Docker image  to docker hub

![Alt text](docs/DockerBuild-and-Push-to-DockerHub.png)


## Unit Testing
An improper unit testing product will definitely impact the product deliverable. Therefore  Unit testing is an integral part of any agile development principles. Today we have different framework like junit, mockito
any many other frameworks, in the current system i had leveraged a framework called spock.

Spock is a testing and specification framework for Java and Groovy applications. 

Why Spock?

* it is Extremely expressive , we can just use english sentences to name the Testing methods
* Facilitates the Given/When/Then syntax for your tests, where given is the inputs, when is execution and then is final validation of your results
* Compatible with most IDEs and CI Servers.

You can find more information here,
https://dzone.com/articles/adapter-design-pattern-video

### Unit Test Results
All the three Rest API are unit tested and the results are 100% clean, attached the results
for reference for the same.

![Alt text](docs/Junit.png)

## Possible Enhancements

Application can be extended with more capabilities by adding some of the following features.

* Route  53 can be configured to API Gateway to leverage more DNS capabilities.
* Configuration management can be done using AWS cloudformation and AWS Service catalog.
* Lambda authorizers and Amazon Cognito user pools can be leveraged for rest authentication solutions.
* Kibana an extra service that  comes by default when you subscribe for Elastic search in AWS can be levaraged for log mangement.

## Conclusion
Finally to conclude, This is a wonderfull excercise that gave me opportunity to look and work on different components of AWS and look into the 
system in a more broad way. Infact we can leverage many more feature from AWS to enhance the system as per the requirements. Any corner you think of either its
on analytics , Machine learning , IOT or any use case,  we can pretty much leverage AWS and other public cloud services. 

Some of my articles  in the context of cloud computing.

* https://ravishajava.com/2018/08/11/my-case-study-on-publicly-available-cloud-migration-coca-cola-cloud-journey/
* https://ravishajava.com/2018/08/10/log-management-kubernetes/
* https://ravishajava.com/2018/08/08/elasticsearch-datasource-grapahana/







