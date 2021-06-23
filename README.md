# URL Shortener BE
Backend Service for URL Shortener application built on Java

## Starting the service

The webservice is built on Spring Boot, and required to have maven installed and added to environment variable. 

You can start by executing command `mvn spring-boot:run`

## API Endpoints

### 1. `POST /api/short`
API to shorten the URL. The response is the hash code that you can freely append on your FE routing and serve to the user

    curl --location --request POST 'http://localhost:8080/api/short' \
     --header 'Content-Type: text/plain' \
     --data-raw 'https://google.com/'

### 2. `GET /api/short/{hash}`
API to get the original URL back. The response is the full path to original url that you can freely implement redirection on your FE client

    curl --location --request GET 'http://localhost:8080/api/short/5e399431asd'
    
### 3. `POST /api/auth`
Authentication purpose. This API will give you JWT token that you need to supply on admin endpoint. Default first login is "admin:admin"

Request:

    curl --location --request POST 'http://localhost:8080/api/auth' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "username": "admin",
        "password": "admin"
    }'

Response:

    {
        "jwttoken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNDQzNzU2OSwiZXhwIjoxNjI0NDQxMTY5fQ.8Xpoi0LcOXEmJIWV97HGH0p0y6liDH_9MwEg4CDk1k5WuKrdUp5zlA20CwKFyzN-1MApLhCFaZDHawHzVQ7B3A"
    }
    
### 4. `POST /api/auth/register`
API to register new admin user

    curl --location --request POST 'http://localhost:8080/api/auth/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "username": "user",
        "password": "password"
    }'

### 5. `GET /api/admin`
API to retrieve all stored url. Pagination is available.

Request:

    curl --location --request GET 'http://localhost:8080/api/admin?page=0&size=3&sort=expiredTime,desc&search=gle' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNDQzNzU2OSwiZXhwIjoxNjI0NDQxMTY5fQ.8Xpoi0LcOXEmJIWV97HGH0p0y6liDH_9MwEg4CDk1k5WuKrdUp5zlA20CwKFyzN-1MApLhCFaZDHawHzVQ7B3A'

Response:

    {"content":[{"alias":"dab1105e","url":"https://twelve.com/","expired_time":"2022-06-23T15:39:23","hit":0},{"alias":"0669d834","url":"https://two.com/","expired_time":"2022-06-23T15:38:42","hit":0}],"pageable":{"sort":{"sorted":true,"unsorted":false,"empty":false},"offset":0,"pageSize":3,"pageNumber":0,"unpaged":false,"paged":true},"last":true,"totalElements":2,"totalPages":1,"size":3,"number":0,"sort":{"sorted":true,"unsorted":false,"empty":false},"first":true,"numberOfElements":2,"empty":false}

Parameters:
| page   | default 0, start from 0                                                                             | Define the page you want to open   |
|--------|-----------------------------------------------------------------------------------------------------|------------------------------------|
| size   | default 5                                                                                           | Max data count in 1 page           |
|--------|-----------------------------------------------------------------------------------------------------|------------------------------------|
| sort   | must use known json field, default direction ASC. Specify the direction by adding ",asc" or ",desc" | The sorting attribute for the data |
|--------|-----------------------------------------------------------------------------------------------------|------------------------------------|
| search | url to be search. Will search for url containing the specified substring                            |                                    |

