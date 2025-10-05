Feature: Servicenow API incident feature scenarios validation

Scenario: Validate user should able fetch the all incident table records
Given user should set the base uri as "https://dev230683.service-now.com" in the api client
And user should set the base path "/api/now/table" of the service now table api
And user should set the basic authentication user name as "admin" and password as "Hz1e=0AU!fAd"
When user should hit the get request of the "/incident" table
Then user should able to see the success response and with relevant status code and message

Scenario: Validate user should able to create the new incident table record without body
Given user should set the base uri as "https://dev230683.service-now.com" in the api client
And user should set the base path "/api/now/table" of the service now table api
And user should set the basic authentication user name as "admin" and password as "Hz1e=0AU!fAd"
And user should set the header key as "Content-Type" and value as "application/json"
When user should hit the post request of the "/incident" table
Then user should successfully created the new record and with the relevant status code and message
| statusCode | statusLine | contentType      | responseTime |
| 201        | Created    | application/json | 5000         |