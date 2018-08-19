#Author: Elizabeth Gilbert (evgilber)

Feature: Delete a recipe
	As a user
	I want to be able to delete recipes from the CoffeeMaker
	So that only the best recipes are available for our customers
	
	
	
	
	
	
Scenario Outline: Delete recipe by valid name
	Given the Coffeemaker has a recipe named <recipeName>
	When I delete the recipe called <recipeName>
	Then the recipe is successfully deleted by name

Examples:
	| recipeName |
	| Coffee     |





Scenario Outline: Delete recipe by invalid name
	Given the CoffeeMaker already has <existingRecipes> Recipes
	When I attempt to delete a nonexistent recipe called <recipeName>
	Then the recipe book of the CoffeeMaker is not changed

Examples:
	| existingRecipes | recipeName |
	| 0				  | dne        |
	| 1               | dne        |
	| 2               | dne        |
	| 3               | dne        |
	
	
	
	
	
#Scenario Outline: Delete recipe by valid index
	#Given the CoffeeMaker already has <existingRecipes> Recipes
	#When I attempt to delete the recipe at index <index>
	#Then the recipe is successfully deleted by index
	
#Examples:
#	| existingRecipes | index |
#	| 1               | 0     |
#	| 2               | 0     |
#	| 2               | 1     |
#	| 3               | 0     |
#	| 3               | 1     |
#	| 3               | 2     |
	
	
	
	
	
#Scenario Outline: Delete recipe by invalid index
#	Given the CoffeeMaker already has <existingRecipes> Recipes
#	When I attempt to delete the recipe at index <index>
#	Then an error occurs
#	And the recipe book of the CoffeeMaker is not changed
	
#Examples:
#	| existingRecipes | index  |
#	| 0               | 0      |
#	| 0               | -1     |
#	| 1               | -1     |
#	| 1               | 2      |
	
	
	