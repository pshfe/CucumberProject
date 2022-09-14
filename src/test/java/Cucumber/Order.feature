
@tag
Feature: Purchase the Order from Ecommerce Website


  Background:
  Given I landed on Ecommerce Website
  
  
  @tag1
  Scenario Outline: Positive Test of Placing Order
    Given Login with valid emailid <email> and password <password>
    When Add Products <Product> into the cart
    And Checkout <Product> and Submit the order
    Then "THANKYOU FOR THE ORDER." is displayed on Confirmation Page

    Examples: 
      | email                   | password    | Product       |
      | pooja.shetty@gmail.com | Qwerty@2022  | IPHONE 13 PRO|
      | pooja.shetty@gmail.com | Qwerty@2022  | ZARA COAT 3|
