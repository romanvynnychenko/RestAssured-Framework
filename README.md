# RestAssured-Framework

https://reqres.in/

##Test 1: All avatar names contains ID

Path - src/test/java/tests/ListUsersTest
Test - avatarNameContainsIdTest()

HTTP Request: GET
Resource: api/users?page=2
1. GET the list of users from the second (2) page
2. Verify that the status code is 200
3. Response body DESERIALIZATION 
4. Make sure that the names of user avatars cointain id;
__Expected Result:__ 
every avatar name contain user Id
Response status 200

###Test 2: All emails ends with @reqres.in
Path - src/test/java/tests/ListUsersTest
Test - emailEndsWithReqresDotInTest()

HTTP Request: GET
Resource: api/users?page=2
1. GET the list of users from the second (2) page
2. Verify that the status code is 200
3. Response body DESERIALIZATION 
4. Make sure that users emails end with @reqres.in
__Expected Result:__ 
every email ends with @reqres.in
Response status 200

###Test 3: Succsessful registration
Path - src/test/java/tests/RegisterTest
Test - seccessfulRegister()

HTTP Request: POST
Resource: api/register
1. Request body SERIALIZATION {"email": "eve.holt@reqres.in", "password": "pistol"}
2. Send POST request
3. Verify that the status code is 200
4. Response body DESERIALIZATION 
5. Make sure that id is not Null
6. Make sure that token is not Null
7. Make sure that current id equals to expected id (4)
8. Make sure that current token equals to expected token (QpwL5tke4Pnpja7X4)
__Expected Result:__ 
{"id": 4, "token": "QpwL5tke4Pnpja7X4"}
Response status 200

###Test 4: Unsuccsessful registration
Path - src/test/java/tests/RegisterTest
Test - unSeccessfulRegister()

HTTP Request: POST
Resource: api/register
1. Request body SERIALIZATION {"email": "sydney@fife", "password": ""}
2. Send POST request
3. Verify that the status code is 400
4. Response body DESERIALIZATION 
5. Make sure that error is not Null
6. Make sure that current error text equals to expected error text ("Missing password")
__Expected Result:__ 
{"error": "Missing password"}
Response status 400

###Test 5: Unsuccsessful registration
Path - src/test/java/tests/ListResourceTest
Test - sortValidationByYear

HTTP Request: GET
Resource: api/unknown
1. GET the list of data
2. Verify that the status code is 200
3. Response body DESERIALIZATION to list
4. Create years list with year values
5. Create sortedYear list with sorted year valuers
6. Make sure that year list and sortedYears list are equals
__Expected Result:__ 
Response data sorted by year
Response status 200
