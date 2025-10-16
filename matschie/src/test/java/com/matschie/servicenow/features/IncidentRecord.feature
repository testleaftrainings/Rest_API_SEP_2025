Feature: Servicenow API incident feature scenarios validation

Scenario: Validate user should able fetch the all incident table records
Given user should set the base uri as "service.now.base.uri" in the api client
And user should set the base path "service.now.base.path" of the service now table api
And user should set the basic authentication user name as "sevice.now.instance.username" and password as "service.now.instance.password"
When user should hit the get request of the "/incident" table
Then user should able to see the success response and with relevant status code and message