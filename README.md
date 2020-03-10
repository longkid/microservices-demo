# microservices-demo
Studying microservices architecture using Spring Framework, Java

## Code folder structure
Each project plays a role of a microservice in microservices architecture. Here is the basic folder structure which I use for organizing my code (in `src\main\java`):
```
├── dto                       # DTO classes are put here
├── entity                    # @Entity classes are put here
├── repository                # @Repository interface for CRUD are put here
├── rest                      # @RestController classes are put here
├── service                   # Classes implementing business logic are put here
└── util                      # Utility classes are put here
```
You can find `application.properties` and SQL script (if any) in `src\main\resources` folder.

## How to run the application
### Use embedded database
If you use Apache Derby in embedded mode, you can start all services by following steps:
1. Go to project directory (api-gateway, car-catalog-service, discovery-server, order-service)
2. Run this command in Windows cmd or Terminal (Linux): `mvn spring-boot:run`

The *discovery-server* service need to be started firstly, then you can start the remaining services in any order.
### Use MariaDB
If you use MariaDB, you need to:
1. Install MariaDB. If password of root account is not 123456, please update this properties for connecting to DB:
```
spring.datasource.password=123456
```
2. For order-service and car-catalog-service project, execute DB script `db_script.sql` in `src\main\resources` folder
3. Revert this commit: https://github.com/longkid/microservices-demo/commit/7ed88c1ce09cc089406bae798a3c971becedc878
4. Go to project directory
5. Run: `mvn spring-boot:run`
## How to verify the APIs
### Using Postman
The first way to test API is to use Postman. You need to install Postman and import file `XYZ - Find cars for sale.postman_collection.json` in this repository.
### Using CURL
Here are the CURL commands to verify APIs:
1. Get all cars API:
```
curl --location --request GET 'http://localhost:8085/api/catalog/cars'
```
2. Get a car API:
```
curl --location --request GET 'http://localhost:8085/api/catalog/cars/1'
```
3. Create a car API:
```
curl --location --request POST 'http://localhost:8085/api/catalog/cars' \
--header 'Content-Type: application/json' \
--data-raw '{
	"make": "Toyota",
	"model": "Camry",
	"colour": "White",
	"price": 1200
}'
```
4. Update a car API:
```
curl --location --request PUT 'http://localhost:8085/api/catalog/cars/9' \
--header 'Content-Type: application/json' \
--data-raw '{
	"make": "Toyota",
	"model": "Camry",
	"colour": "White",
	"price": 1100
}'
```
5. Delete a car API:
```
curl --location --request DELETE 'http://localhost:8085/api/catalog/cars/9'
```
6. Place an order API:
```
curl --location --request POST 'http://localhost:8085/api/catalog/cars/order' \
--header 'Content-Type: application/json' \
--data-raw '{
	"carId": 1,
	"customerName": "Ho Tien Dung",
	"customerPhone": "0909090902"
}'
```
7. Get all orders API:
```
curl --location --request GET 'http://localhost:8085/api/order/orders'
```
8. Retrieve car info API:
```
curl --location --request GET 'http://localhost:8085/api/order/orders/retrieve-car-info/1'
```
