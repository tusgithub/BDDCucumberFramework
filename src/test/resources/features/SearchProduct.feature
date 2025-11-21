@OffersPage

Feature: Search and Place the order for Products

  Scenario Outline: Search Experience for product search in both Home and Offers page

    Given User is on GreenCart Landing page
    When User searched with shortname <Name> and extracted actual name of product
    Then User searched for <Name> shortname in offers page
    And Validate product name in Offers page matches with Landing Page

    Examples:
      |Name |
      | Tom |
      | Beet |