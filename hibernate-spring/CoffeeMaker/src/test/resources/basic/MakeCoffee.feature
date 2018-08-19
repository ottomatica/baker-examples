#Author: Elizabeth Gilbert (evgilber)

Feature: Make Coffee
	As a customer
	I want to be able to make coffee
	So that I can speak politely with my colleagues in the morning
	
	
	
	
Scenario Outline: Valid Make Coffee
	Given the CoffeeMaker has <existingRecipes> Recipes
	And <originalCoffee> coffee, <originalMilk> milk, <originalSugar> sugar, and <originalChocolate> chocolate currently in the CoffeeMaker
	When I submit values for name: <recipeName>; cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
	And I make coffee with the recipe for <recipeName> and input <money> money
	Then the coffee is successfully made with correct change
	And the inventory is updated correctly

Examples:
	| originalCoffee | originalMilk | originalSugar | originalChocolate | existingRecipes | recipeName | price | amtCoffee | amtMilk | amtSugar | amtChocolate | money |
	| 15             | 15           | 15            | 15                | 0               | coffee     | 60    | 0         | 3       | 7        | 2            | 60    |
	| 15             | 15           | 15            | 15                | 1               | coffee     | 60    | 5         | 0       | 7        | 2            | 60    |
	| 15             | 15           | 15            | 15                | 2               | coffee     | 60    | 5         | 3       | 0        | 2            | 60    |
	| 15             | 15           | 15            | 15                | 2               | coffee     | 60    | 5         | 3       | 7        | 0            | 60    |
	| 15             | 15           | 15            | 15                | 0               | coffee     | 60    | 5         | 3       | 7        | 2            | 100   |
	| 15             | 15           | 15            | 15                | 0               | coffee     | 60    | 5         | 3       | 7        | 2            | 61    |
	| 6              | 4            | 8             | 3                 | 0               | coffee     | 60    | 5         | 3       | 7        | 2            | 60    |
	| 5              | 3            | 7             | 2                 | 0               | coffee     | 60    | 5         | 3       | 7        | 2            | 60    |




Scenario Outline: Invalid Make Coffee
	Given the CoffeeMaker has <existingRecipes> Recipes
	And <originalCoffee> coffee, <originalMilk> milk, <originalSugar> sugar, and <originalChocolate> chocolate currently in the CoffeeMaker
	When I submit values for name: <recipeName>; cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
	And I make coffee with the recipe for <recipeName> and input <money> money
	Then the coffee maker returns your money
	And the inventory is not changed

Examples:
	| originalCoffee | originalMilk | originalSugar | originalChocolate | existingRecipes | recipeName | price | amtCoffee | amtMilk | amtSugar | amtChocolate | money |
	| 15             | 15           | 15            | 15                | 0               | coffee     | 60    | 5         | 3       | 7        | 2            | 59    |
	| 15             | 15           | 15            | 15                | 1               | coffee     | 60    | 5         | 3       | 7        | 2            | -1    |
	