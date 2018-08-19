package edu.ncsu.csc.testUtils.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

/**
 * PageObject for /CoffeeMaker_Cucumber/delete_recipe.jsp
 *
 * @author Sarah Elder (seelder@ncsu.edu)
 * @author Kai Presler-Marshall (kpresle@ncsu.edu)
 * @author Elizabeth Gilbert (evgilber@ncsu.edu)
 */
public class DeleteRecipePage extends AbstractPageObject {
    private final By recipeList   = By.name( "recipes" );
    private final By submitButton = By.cssSelector( "input[type=\"submit\"]" );
    private final By clearAll     = By.cssSelector( "input[type=\"checkbox\"]" );

    private final By returnToMain = By.linkText( "Home" );

    /**
     * Constructor
     */
    public DeleteRecipePage () {
        driver = null;
    }

    /**
     * Deletes all recipes on the page
     *
     * @param driver
     * @return the number of recipes deleted
     *
     */
    public int deleteAllRecipes () {
        try {
            final List<WebElement> list = driver.findElements( recipeList );
            final int len = list.size();
            final WebElement we = driver.findElement( clearAll );
            we.click();
            driver.findElement( submitButton ).click();

            return len;
        }
        catch ( final NoSuchElementException e ) {
            return 0;
        }
    }

    /**
     * Clicks the link to return to the main page
     */
    public void returnToMain () {
        driver.findElement( returnToMain ).click();
    }

    /**
     * Looks through the bulleted list of recipes to delete to find the
     * specified recipe name
     *
     * @param name
     *            recipe name
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
     *            recipe name
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
     * Returns true if given text is found.
     *
     * @param txt
     *            text to find
     * @return true if found
     */
    public boolean findText ( final String txt ) {
        return driver.getPageSource().contains( txt );
    }

    /**
     * Returns the number of recipes containing the specified text
     *
     * @param text
     *            text to find
     * @return int number of recipes with text
     */
    public int countRecipeInForm ( final String text ) {
        final List<WebElement> list = driver.findElements( recipeList );
        int count = 0;
        for ( final WebElement we : list ) {
            if ( text.equals( we.getAttribute( "value" ) ) ) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the size of the recipe list
     *
     * @return size of recipe list
     */
    public int countNumberOfRecipes () {
        final List<WebElement> list = driver.findElements( recipeList );
        return list.size();
    }
}
