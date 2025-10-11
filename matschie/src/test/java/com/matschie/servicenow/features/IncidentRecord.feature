Feature: Servicenow API incident feature scenarios validation

Scenario: Validate user should able fetch the all incident table records
Given user should set the base uri as "https://dev230683.service-now.com" in the api client
And user should set the base path "/api/now/table" of the service now table api
And user should set the basic authentication user name as "admin" and password as "Hz1e=0AU!fAd"
When user should hit the get request of the "/incident" table
Then user should able to see the success response and with relevant status code and message