package edu.ncsu.csc.testUtils.web;

import org.openqa.selenium.By;

/**
 * PageObject for /CoffeeMaker_Cucumber/add_recipe.jsp
 *
 * @author seelder
 * @author kpresler
 *
 */
public class MainPage extends AbstractPageObject {
    private final By addRecipeLink      = By.linkText( "Add a Recipe" );
    private final By addInventoryLink   = By.linkText( "Update Inventory" );
    private final By checkInventoryLink = addInventoryLink;
    private final By deleteRecipeLink   = By.linkText( "View/Delete Recipe" );
    private final By makeCoffeeLink     = By.linkText( "Make Coffee" );

    /**
     * Constructor
     *
     */
    public MainPage () {
        driver = null;
    }

    /**
     * Clicks the link to go to the "Add a Recipe" page
     *
     */
    public void goToAddRecipe () {
        driver.findElement( addRecipeLink ).click();

    }

    /**
     * Clicks the link to go to the "Delete Recipe" page
     *
     */
    public void goToDeleteRecipe () {
        driver.findElement( deleteRecipeLink ).click();

    }

    /**
     * Clicks the link to go to the "Add Inventory" page
     *
     */
    public void goToAddInventory () {
        driver.findElement( addInventoryLink ).click();

    }

    /**
     * Clicks the link to go to the "Check Inventory" page
     *
     */
    public void goToCheckInventory () {
        driver.findElement( checkInventoryLink ).click();

    }

    /**
     * Clicks the link to go to the "Make Coffee" page
     *
     */
    public void goToMakeCoffee () {
        driver.findElement( makeCoffeeLink ).click();

    }

}
