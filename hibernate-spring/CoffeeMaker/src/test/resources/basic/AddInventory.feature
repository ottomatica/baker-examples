#Author: seelder

Feature: Add Inventory
	As an Administrator
	I want to add Inventory to the Coffee Machine
	So that supplies are not depleted and consumers can still drink coffee (it makes life easier for all of us)

Scenario Outline: Valid Inventory added
Given there is <originalCoffee> coffee, <originalMilk> milk, <originalSugar> sugar, and <originalChocolate> chocolate in the CoffeeMaker
When I add <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, and <amtChocolate> chocolate
Then the inventory of the CoffeeMaker is successfully added

Examples:
  | originalCoffee | originalMilk | originalSugar | originalChocolate | amtCoffee | amtMilk | amtSugar | amtChocolate |
  | 0              | 0            | 0             | 0                 | 5         | 3       | 7        | 2            |
  | 20             | 20           | 15            | 22                | 5         | 3       | 7        | 2            |
	
	
	
	
	
Scenario Outline: Invalid Inventory data input
Given there is <originalCoffee> coffee, <originalMilk> milk, <originalSugar> sugar, and <originalChocolate> chocolate in the CoffeeMaker
When I attempt to add <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, and <amtChocolate> chocolate
Then an error occurs for <error>
And the inventory of the CoffeeMaker is not updated

Examples:
  | originalCoffee | originalMilk | originalSugar | originalChocolate | amtCoffee | amtMilk | amtSugar | amtChocolate | error     |
  | 20             | 20           | 15            | 22                | -1        | 3       | 7        | 2            | coffee    |
  | 20             | 20           | 15            | 22                | 5         | -1      | 7        | 2            | milk      |
  | 20             | 20           | 15            | 22                | 5         | 3       | -1       | 2            | sugar     |
  | 20             | 20           | 15            | 22                | 5         | 3       | 7        | -1           | chocolate |
	
	