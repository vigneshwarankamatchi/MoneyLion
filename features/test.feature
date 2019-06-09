Feature: LaunchWebsite
  This Feature open the new website

#  Scenario: Able to access MoneyLion Plus Membership page successfully
#    Given I am a new customer And access to the MoneyLion website
#    When I hover on “Membership” and click on “Plus” at the top of the webpage
#    Then I should able to go to the MoneyLion Plus membership page
#    And I should be able to see Martha K testimony is displayed
#    Then Close the Browser

    Scenario: Able to see ways to earn rewards
      Given I am a new customer And access to the MoneyLion website
      When I click on the Rewards at the bottom of the page
      And I scroll to view the “Earn rewards by” section
      Then I should able to see all four ways to earn rewards
      Then Close the Browser


