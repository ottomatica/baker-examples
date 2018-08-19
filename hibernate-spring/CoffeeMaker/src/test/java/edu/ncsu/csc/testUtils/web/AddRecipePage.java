package edu.ncsu.csc.testUtils.web;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * PageObject for /CoffeeMaker_Cucumber/add_recipe.jsp
 *
 * @author seelder
 * @author kpresler
 */
public class AddRecipePage extends AbstractPageObject {
    private final By nameBox         = By.name( "name" );
    private final By priceBox        = By.name( "price" );
    private final By amtCoffeeBox    = By.name( "coffee" );
    private final By amtMilkBox      = By.name( "milk" );
    private final By amtSugarBox     = By.name( "sugar" );
    private final By amtChocolateBox = By.name( "chocolate" );
    private final By submitButton    = By.cssSelector( "input[type=\"submit\"]" );
    private final By returnToMain    = By.linkText( "Home" );

    /**
     * Default constructor used by PicoContainer
     */
    public AddRecipePage () {
        driver = null;
    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for Recipe name
     *
     * @param name
     *            String
     */
    public void inputNewName ( final String name ) {
        try {
            driver.findElement( nameBox ).clear();
            driver.findElement( nameBox ).sendKeys( name );
        }
        catch ( final Exception e ) {
            System.out.println( driver.getCurrentUrl() );
            System.out.println( driver.getPageSource() );
            Assert.fail();
        }

    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for Recipe price. This does NOT check whether the
     * String has an integer equivalent since the validation does not trigger
     * until the information is submitted
     *
     * @param price
     *            String
     */
    public void inputNewPrice ( final String price ) {
        driver.findElement( priceBox ).clear();
        driver.findElement( priceBox ).sendKeys( price );
    }

    /**
     * Clears out any previous information and sets the specified string as the
     * value in the textbox for amount of coffee in the Recipe. This does NOT
     * check whether the String has an integer equivalent since the validation
     * does not trigger until the information is submitted
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
     * value in the textbox for amount of milk in the Recipe. This does NOT
     * check whether the String has an integer equivalent since the validation
     * does not trigger until the information is submitted
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
     * value in the textbox for amount of sugar in the Recipe. This does NOT
     * check whether the String has an integer equivalent since the validation
     * does not trigger until the information is submitted
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
     * value in the textbox for amount of chocolate in the Recipe. This does NOT
     * check whether the String has an integer equivalent since the validation
     * does not trigger until the information is submitted
     *
     * @param amtChocolate
     *            amount chocolate
     */
    public void inputNewAmtChocolate ( final String amtChocolate ) {
        driver.findElement( amtChocolateBox ).clear();
        driver.findElement( amtChocolateBox ).sendKeys( amtChocolate );
    }

    /**
     * Click the "Add Recipe" button
     */
    public void submitRecipe () {
        driver.findElement( submitButton ).click();
    }

    /**
     * Inputs a full recipe (but does not submit the recipe information)
     *
     * @param name
     *            String
     * @param price
     *            String
     * @param amtCoffee
     *            String
     * @param amtMilk
     *            String
     * @param amtSugar
     *            String
     * @param amtChocolate
     *            String
     */
    public void addRecipe ( final String name, final String price, final String amtCoffee, final String amtMilk,
            final String amtSugar, final String amtChocolate ) {
        try {
            driver.findElement( By.linkText( "Add another recipe" ) ).click();
        }
        catch ( final org.openqa.selenium.NoSuchElementException nsse ) {
            /* If we're on the right page continue on */
        }
        inputNewName( name );
        inputNewPrice( price );
        inputNewAmtCoffee( amtCoffee );
        inputNewAmtMilk( amtMilk );
        inputNewAmtSugar( amtSugar );
        inputNewAmtChocolate( amtChocolate );
    }

    /**
     * Returns true if given status message is found.
     *
     * @param msg
     *            String
     * @return Boolean value indicating whether the specified status message was
     *         found
     */
    public boolean findStatusMessage ( final String msg ) {
        final List<WebElement> list = driver.findElements( By.xpath(
                "//h3[contains(text(),'Add a Recipe')]/following-sibling::span[contains(text(),'" + msg + "')]" ) );
        return list.size() > 0;
    }

    /**
     * Return true if given text is found.
     *
     * @param txt
     *            text to find
     * @return true if found
     */
    public boolean findText ( final String txt ) {
        return driver.getPageSource().contains( txt );
    }

    /**
     * Clicks the link to return to the main page
     */
    public void returnToMain () {
        driver.findElement( returnToMain ).click();
    }

}
