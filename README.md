# Virtual Power Plant API
Backend Code Challenge - Sample Springboot project for virtual power plant system

# Prerequisites for Local environment

1. Install [docker](https://docs.docker.com/get-docker/)
2. Install [docker compose](https://docs.docker.com/compose/install/)
3. Install [postman](https://www.postman.com/downloads/)

# Start system

1. Clone virtual-power-plant repository
2. Run `docker compose up`

# Accept data - Post endpoints

[Post a request](http://localhost:8080/api/batteries/bulk_create) to accept a list of batteries.

sample input jason raw data ------------------------------------------------------------------------------------------------------------------------------------------------>
{
   "batteryCreateRequestList" : [
 {
    "name":"c2amb-500",
    "postcode":11901,
    "wattCapacity":12.1
    }, {
    "name":"b2amb-01",
    "postcode":11401,
    "wattCapacity":12.1
    }, {
    "name":"l2kia-01",
    "postcode":10401,
    "wattCapacity":18.1
    }
    ]
}
<-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

![image](https://user-images.githubusercontent.com/31001595/191975651-960053df-626e-477a-bb65-f0e6f3380d36.png)

# View data - Get endpoints

1. Goto [view all data](http://localhost:8080/api/batteries) to access api
2. Goto [http://localhost:8080/api/batteries/postcode_range?lowerPostcode=<Integer value>&upperPostcode=<Integer value>](http://localhost:8080/api/batteries/postcode_range?lowerPostcode=10400&upperPostcode=11500) to access list of names of batteries and 
some statistics such as total and average watt capacity that fall within the range

![image](https://user-images.githubusercontent.com/31001595/191972491-61c37e34-3490-4b23-97c7-0245b7fedd82.png)

