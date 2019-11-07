BookingBoss Application

- This is maven project, so checkout the project and execute mvn clean install
- Run ```java -jar target/booking-boss.jar```

```curl -X POST -H "Content-Type: application/json" --data @/path/to/booking-boss/src/main/resources/data/post-request.json http://localhost:8080/products```

```
{
   "id":"1",
   "timestamp":"10-10-2019 10:10:10",
   "products":[
      {
         "id":1,
         "name":"te",
         "quantity":1,
         "salemount":1.2
      },
       {
         "id":12,
         "name":"te",
         "quantity":1,
         "salemount":1.2
      }
   ]
}

```
- Get Response
```curl -X GET -H "Content-Type: application/json" --data @/path/to/booking-boss/src/main/resources/data/get-request.json http://localhost:8080/products```

```
{
	"products": [
		{
			"id": 1,
			"name": "test",
			"quantity": 1,
			"salemount": 1.2
		}
	],
	"unavailableProducts": [
		{
			"id": 12
		}
	],
	"id": "1",
	"timestamp": "10-10-2019 10:10:10"
}
```






