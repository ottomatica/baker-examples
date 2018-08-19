package edu.ncsu.csc.testUtils.web;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * PageObject for /CoffeeMaker_Cucumber/makecoffee.html
 *
 * @author Elizabeth Gilbert (evgilber@ncsu.edu)
 */
public class MakeCoffeePage extends AbstractPageObject {
    private final By recipeList   = By.name( "recipes" );
    private final By submitButton = By.cssSelector( "input[type=\"submit\"]" );
    private final By moneyInput   = By.name( "amtPaid" );
    private final By returnToMain = By.linkText( "Home" );

    /**
     * Constructor for testing MakeCoffe page.
     */
    public MakeCoffeePage () {
        driver = null;
    }

    /**
     * Clicks the link to return to the main page
     */
    public void returnToMain () {
        driver.findElement( returnToMain ).click();
    }

    /**
     * Inputs the money.
     *
     * @param amtMoney
     *            amount to input
     */
    public void inputMoney ( final String amtMoney ) {
        try {
            driver.findElement( moneyInput ).clear();
            driver.findElement( moneyInput ).sendKeys( amtMoney );
        }
        catch ( final Exception e ) {
            System.out.println( driver.getCurrentUrl() );
            System.out.println( driver.getPageSource() );
            Assert.fail();
        }
    }

    /**
     * Looks through the bulleted list of recipes to delete to find the
     * specified recipe name
     *
     * @param name
     *            of recipe
     * @return boolean - true if a recipe name containing the input String is
     *         found. False otherwise
     */
    public boolean findRecipeInForm ( final String name ) {
        final List<WebElement> list = driver.findElements( recipeList );
        for ( final WebElement we : list ) {
            if ( name.equals( we.getAttribute( "value" ) ) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Looks through the list of available recipes and selects the specified
     * recipe
     *
     * @param name
     *            of recipe
     * @return true if found and selected, false if not
     */
    public boolean selectRecipe ( final String name ) {
        final List<WebElement> list = driver.findElements( recipeList );

        // Select the recipe
        for ( final WebElement we : list ) {
            if ( name.equals( we.getAttribute( "value" ) ) ) {
                we.click();
                return true;
            }
        }

        return false;
    }

    /**
     * Click the "Submit" button to delete the recipe
     */
    public void clickSubmit () {
        driver.findElement( submitButton ).click();
    }

    /**
     * Returns true if text is found.
     *
     * @param txt
     *            text to find
     * @return true if found
     */
    public boolean findText ( final String txt ) {
        return driver.getPageSource().contains( txt );
    }

}
