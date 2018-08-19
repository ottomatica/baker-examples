package edu.ncsu.csc.testUtils.web;

import org.openqa.selenium.By;

/**
 * PageObject for /CoffeeMaker_Cucumber/add_recipe.jsp
 *
 * @author seelder
 * @author kpresler
 */
public class AddInventoryPage extends AbstractPageObject {
    private final By returnToMain    = By.linkText( "Home" );
    private final By amtCoffeeBox    = By.name( "coffee" );
    private final By amtMilkBox      = By.name( "milk" );
    private final By amtSugarBox     = By.name( "sugar" );
    private final By amtChocolateBox = By.name( "chocolate" );
    private final By submitButton    = By.cssSelector( "input[type=\"submit\"]" );

    /**
     * Default constructor used by PicoContainer
     */
    public AddInventoryPage () {
        driver = null;
    }

    /**
     * Clicks the link to return to the main page
     */
    public void returnToMain () {
        driver.findElement( returnToMain ).click();
    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for amount of coffee to add. This does NOT check
     * whether the String has an integer equivalent since the validation does
     * not trigger until the information is submitted
     *
     * @param amtCoffee
     *            amount coffee
     */
    public void inputNewAmtCoffee ( final String amtCoffee ) {
        driver.findElement( amtCoffeeBox ).clear();
        driver.findElement( amtCoffeeBox ).sendKeys( amtCoffee );
    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for amount of milk to add. This does NOT check
     * whether the String has an integer equivalent since the validation does
     * not trigger until the information is submitted
     *
     * @param amtMilk
     *            amount milk
     */
    public void inputNewAmtMilk ( final String amtMilk ) {
        driver.findElement( amtMilkBox ).clear();
        driver.findElement( amtMilkBox ).sendKeys( amtMilk );
    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for amount of sugar to add. This does NOT check
     * whether the String has an integer equivalent since the validation does
     * not trigger until the information is submitted
     *
     * @param amtSugar
     *            amount sugar
     */
    public void inputNewAmtSugar ( final String amtSugar ) {
        driver.findElement( amtSugarBox ).clear();
        driver.findElement( amtSugarBox ).sendKeys( amtSugar );
    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for amount of chocolate to add. This does NOT check
     * whether the String has an integer equivalent since the validation does
     * not trigger until the information is submitted
     *
     * @param amtChocolate
     *            amount chocolate
     */
    public void inputNewAmtChocolate ( final String amtChocolate ) {
        driver.findElement( amtChocolateBox ).clear();
        driver.findElement( amtChocolateBox ).sendKeys( amtChocolate );
    }

    /**
     * Clicks the add inventory button.
     */
    public void clickAddInventory () {
        driver.findElement( submitButton ).click();

    }

    /**
     * Finds a given status message.
     *
     * @param msg
     *            message to find
     * @return true if found
     */
    public boolean findStatusMessage ( final String msg ) {
        return driver.getPageSource().contains( msg );
    }

}
