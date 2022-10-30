# LOGGER

****
## Project Description
Logger is an application for saving client logs by log type. 
The client must first register. After that the client has to log 
in to get a jwt token and with that token can access the content 
of the application. <br />

Have two roles in the application:

- CLIENT
- ADMIN

When the application starts, two entities will be created in the DB (user
with role client and administrator with role admin). Details about these
entities can be found in the file data.sql. <br />

By role, the user can access different parts of the application. Below is
the API documentation. You can see the client and admin permissions there. <br />

I used H2 database and if you want to see how the app works you don't need
to connect to any DB because I configured everything. In schema.sql you 
can see a query for creating tables and in data.sql you can see a query 
for input data in DB. You just need to download the folder and run the 
application.
****
## Client

1. Register
    - HTTP Method: `POST`
    - Endpoint URL: `/api/clients/register`
    - Request body:
        ```json
        {
            "username": "string",
            "password": "string",
            "email": "string"
        }
        ```
    - Responses:
        - 201 - Registered
        - 400 - Bad Request
            - username, password and email is mandatory
            - usernames size must be between 3 and 20 characters
            - email address must be a well-formed 
        - 409 - Conflict
            - username already exists
            - email already exists

2. Login
    - HTTP Method: `POST`
    - Endpoint URL: `/api/clients/login`
    - Request body:
        ```json
        {
            "account": "string", // email or username
            "password": "string"
        }
        ```
    - Responses:
        - 200 - OK
            ```json
            {
                "token": "string" // JWT 
            }
            ```
        - 400 - Bad Request
            - Email/Username or password incorrect

3. Create log
    - HTTP Method: `POST`
    - Endpoint URL: `/api/logs/create`
    - Request body:
        ```json
        {
            "message": "string",
            "logType": 0
        }
        ```
    - Request headers:
        - `Authorization` - token (Client token)
    - Responses:
        - 201 - Created
        - 400 - Bad Request
            - Incorrect logType
        - 403 - Forbidden
            - Incorrect token
        - 413 - Payload too large
            - Message should be less than 1024

4. Search logs
    - HTTP Method: `GET`
    - Endpoint URL: `/api/logs/search`
    - Request params:
        - `logType` - int
    - Request headers:
        - `Authorization` - token (Client token)
    - Responses:
        - 200 - OK
            ```json
            [
              {
                "message": "string",
                "logType": 0,
                "createdDate": "date"
              }  
            ]
            ```
        - 400 - Bad request
            - Invalid logType
        - 403 - Forbidden
            - Incorrect token

<div style="page-break-after: always;"></div>

## Admin

1. Get all clients
    - HTTP Method: `GET`
    - Endpoint URL: `/api/clients`
    - Request headers:
        - `Authorization` - token (Admin token)
    - Responses:
        - 200 - OK
            ```json
            [
              {
                "id": "int",
                "username": "string",
                "email": "string",
                "logCount": 0
              }  
            ]
            ```
        - 403 - Forbidden
            - Incorrect token

2. Change client password
    - HTTP Method: `PATCH`
    - Endpoint URL: `/api/clients/{clientId}/reset-password`
    - Request body:
        ```json
        {
            "password": "string"
        }
        ```
    - Request headers:
        - `Authorization` - token (Admin token)
    - Responses:
        - 403 - Forbidden
            - Incorrect token