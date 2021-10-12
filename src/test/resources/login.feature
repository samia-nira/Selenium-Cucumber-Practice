Feature: Login to an e-commerce website

  Scenario Outline: Verify users can login to portal with valid credentials
    Given User visits e-commerce website
    When User enters valid email "<email>" and valid password "<password>" credentials
    Then User can logged in successfully
    Examples:
      |email|password|
      |samianira544@gmail.com|AUTO@123456|