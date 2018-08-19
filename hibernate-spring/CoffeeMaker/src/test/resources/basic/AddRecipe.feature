#Author: seelder

Feature: Add a recipe
	As a user
	I want to be able to add recipes to the CoffeeMaker
	So that consumers have variety in their diet
	
	
Scenario Outline: Valid recipe
	Given the CoffeeMaker already has <existingRecipes> Recipes
	When I submit valid values for name: <recipeName>; cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
	Then the recipe is successfully added
	
Examples:
	| existingRecipes | recipeName | price | amtCoffee | amtMilk | amtSugar | amtChocolate |
	| 0               | Coffee     | 50    | 3         | 1       | 1        | 0            |
	| 1               | Mocha      | 60    | 3         | 2       | 2        | 3            |
	| 2               | Latte      | 60    | 3         | 3       | 2        | 0            |


Scenario: Unable to add Four  Recipes
	Given the CoffeeMaker already has 3 Recipes
	When I try to add another recipe
	Then the recipe is not added


Scenario: Recipe with the Same Name
	Given the CoffeeMaker already has 1 Recipes
	And the Coffeemaker has a recipe named BlackCoffee
	When I try to add another recipe named BlackCoffee
	Then a second recipe is not added
	
	
Scenario Outline: Invalid Price
	Given the CoffeeMaker already has 1 Recipes
	When I attempt to submit the following recipe values - name: <recipeName>; cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
	Then the error for an invalid recipe price occurs
	And the recipe is not added

Examples:
	| recipeName   | price | amtCoffee | amtMilk | amtSugar | amtChocolate |
	| Coffee       | 5.678 | 3         | 1       | 1        | 0            |
	| Mocha        | %     | 3         | 2       | 2        | 3            |
	| Latte        | A     | 3         | 3       | 2        | 0            |
	| HotChocolate | -10   | 0         | 2       | 2        | 4            |
	| HotChocolate | null  | 0         | 2       | 2        | 4            |
	
	
Scenario Outline: Invalid Ingredient amount
	Given the CoffeeMaker already has 1 Recipes
	When I attempt to submit the following recipe values - name: <recipeName>; cost: <price>; and ingredients: <amtCoffee> coffee, <amtMilk> milk, <amtSugar> sugar, <amtChocolate> chocolate
	Then the error for an invalid amount of <ingredient> in a recipe occurs
	And the recipe is not added

Examples:
	| recipeName   | price | amtCoffee | amtMilk | amtSugar | amtChocolate | ingredient |
	| HotChocolate | 50    | -5        | 2       | 2        | 4            | coffee     |
	| HotChocolate | 50    | NA        | 2       | 2        | 4            | coffee     |
	| HotChocolate | 50    | %         | 2       | 2        | 4            | coffee     |
	| HotChocolate | 50    | 0.5       | 2       | 2        | 4            | coffee     |
	| HotChocolate | 50    | null      | 2       | 2        | 4            | coffee     |
	| Coffee       | 10    | 3         | -1      | 1        | 0            | milk       |
	| Coffee       | 10    | 3         | NA      | 1        | 0            | milk       |
	| Coffee       | 10    | 3         | %       | 1        | 0            | milk       |
	| Coffee       | 10    | 3         | 0.25    | 1        | 0            | milk       |
	| Coffee       | 10    | 3         | null    | 1        | 0            | milk       |
	| Latte        | 60    | 3         | 3       | -2       | 0            | sugar      |
	| Latte        | 60    | 3         | 3       | a        | 0            | sugar      |
	| Latte        | 60    | 3         | 3       | %        | 0            | sugar      |
	| Latte        | 60    | 3         | 3       | 2.5      | 0            | sugar      |
	| Latte        | 60    | 3         | 3       | null     | 0            | sugar      |
	| Coffee       | 10    | 3         | 1       | 1        | -1           | chocolate  |
	| Coffee       | 10    | 3         | 1       | 1        | Why?         | chocolate  |
	| Coffee       | 10    | 3         | 1       | 1        | %            | chocolate  |
	| Coffee       | 10    | 3         | 1       | 1        | 6.5          | chocolate  |
	| Coffee       | 10    | 3         | 1       | 1        | null         | chocolate  |
	