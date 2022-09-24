# Virtual Power Plant API
Backend Code Challenge - Sample Springboot project for virtual power plant system

# Deployed Docker compose on AWS EC2

1. Access API http://54.146.20.217:8080/api/batteries
2. Configure Base Url http://54.146.20.217:8080/api as a environment of [postman](https://www.postman.com/downloads/)

# Accept data - Post endpoints

[Post a request](http://54.146.20.217:8080/api/batteries/bulk_create) to accept a list of batteries.

sample input jason raw data ---------------------------------------------------------------------------------------------------------------------------->

{
   "batteryCreateRequestList" : [
 {
    "name":"ca2amb-500",
    "postcode":9001,
    "wattCapacity":11.1
    }, {
    "name":"ba2amb-01",
    "postcode":9070,
    "wattCapacity":10.5
    }, {
    "name":"la2kia-01",
    "postcode":9180,
    "wattCapacity":8.1
    }
    ]
}
<----------------------------------------------------------------------------------------------------------------------------------------------------------

![image](https://user-images.githubusercontent.com/31001595/192081546-ae36ecc4-bb6b-4060-b730-a0985d1659bd.png)


# View data - Get endpoints

Goto [view stat data](http://54.146.20.217:8080/api/batteries/postcode_range?lowerPostcode=11400&upperPostcode=11910) to access a list of names of batteries and 
some statistics such as total and average watt capacity fall within the postcode range. 

sample input query params---------------------------------------->lowerPostcode=11400&upperPostcode=11910<-------------------------------------------------------------

![image](https://user-images.githubusercontent.com/31001595/192081941-829ed2f7-51b5-4ffe-95e6-5cb7b7225dc9.png)


# Prerequisites for Local environment

1. Install [docker](https://docs.docker.com/get-docker/)
2. Install [docker compose](https://docs.docker.com/compose/install/)

# Start system Locally

1. Clone repository `git clone https://github.com/RavinduSamaranayake/virtual-power-plant.git`
2. Run `docker compose up`
3. Access API http://localhost:8080/api/batteries
4. Configure Base Url http://localhost:8080/api as a environment of [postman](https://www.postman.com/downloads/)
5. Access endpoints same as the above steps



