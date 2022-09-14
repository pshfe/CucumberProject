
@tag
Feature: Validating my orders

  @tag1
  Scenario Outline: Validating my submitted orders
    Given I landed on Ecommerce Website
    Given Login with valid emailid <email> and password <password>
    When check for the <Product> in cart
    Then verify the assertions for proper <Product> in cart

     Examples: 
      | email                   | password    | Product       |
      | pooja.shetty@gmail.com | Qwerty@2022  | IPHONE 13 PRO|
      | pooja.shetty@gmail.com | Qwerty@2022  | ZARA COAT 3|
