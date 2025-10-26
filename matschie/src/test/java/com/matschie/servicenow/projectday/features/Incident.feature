Feature: Service now application table api incident table service

  Scenario Outline: Validate user able to create new record using POST method
    Given user should set the base uri as "service.now.base.uri" in the api client
    And user should set the base path "service.now.base.path" of the service now table api
    And user should set the basic authentication user name as "sevice.now.instance.username" and password as "service.now.instance.password"
    And Set the header "Content-Type" key and "application/json" as value
    And Set the Accpet header "Accept" key and "application/json" as value
    When create the incident record with the following input data
         | <short_description> | <description> |
    And hit the post http method with request body as the pojo object of the "/incident" table
    Then validate the status code and status line

    Examples: 
      | short_description   | description                                  |
      | APISessionJAN       | Adding new record using POST POJO Object JAN |
      | APISessionFEB       | Adding new record using POST POJO Object FEB |
      | APISessionMAR       | Adding new record using POST POJO Object MAR |
      | APISessionAPR       | Adding new record using POST POJO Object APR |