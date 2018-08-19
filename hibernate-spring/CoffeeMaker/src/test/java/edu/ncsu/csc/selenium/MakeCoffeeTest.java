package edu.ncsu.csc.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Tests Make Coffee functionality.
 *
 * @author Elizabeth Gilbert (evgilber@ncsu.edu)
 * @author Kai Presler-Marshall (kpresle@ncsu.edu)
 */
public class MakeCoffeeTest extends SeleniumTest {

    /** The URL for CoffeeMaker - change as needed */
    private String             baseUrl;
    private final StringBuffer verificationErrors = new StringBuffer();

    @Override
    @Before
    protected void setUp () throws Exception {
        super.setUp();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait( 20, TimeUnit.SECONDS );

    }

    /**
     * Helper to create a recipe to make
     *
     * @return the name of the recipe
     * @throws Exception
     *             if there was an issue in submitting the recipe
     */
    private void createRecipe ( final String name, final int price, final int amtCoffee, final int amtMilk,
            final int amtSugar, final int amtChocolate ) throws Exception {
        driver.get( baseUrl + "" );
        driver.findElement( By.linkText( "Add a Recipe" ) ).click();

        // Enter the recipe information
        final String recipeName = "Coffee";
        driver.findElement( By.name( "name" ) ).clear();
        driver.findElement( By.name( "name" ) ).sendKeys( recipeName );
        driver.findElement( By.name( "price" ) ).clear();
        driver.findElement( By.name( "price" ) ).sendKeys( price + "" );
        driver.findElement( By.name( "coffee" ) ).clear();
        driver.findElement( By.name( "coffee" ) ).sendKeys( amtCoffee + "" );
        driver.findElement( By.name( "milk" ) ).clear();
        driver.findElement( By.name( "milk" ) ).sendKeys( amtMilk + "" );
        driver.findElement( By.name( "sugar" ) ).clear();
        driver.findElement( By.name( "sugar" ) ).sendKeys( amtSugar + "" );
        driver.findElement( By.name( "chocolate" ) ).clear();
        driver.findElement( By.name( "chocolate" ) ).sendKeys( amtChocolate + "" );

        // Submit the recipe.
        driver.findElement( By.cssSelector( "input[type=\"submit\"]" ) ).click();
        Thread.sleep( 5000 );

        // Make sure the proper message was displayed.
        assertTextPresent( "Recipe Created", driver );
    }

    /**
     * Looks through the list of available recipes and selects the specified
     * recipe
     *
     * @param name
     * @return true if found and selected, false if not
     * @throws InterruptedException
     */
    private boolean selectRecipe ( final String name ) throws InterruptedException {
        final List<WebElement> list = driver.findElements( By.name( "name" ) );
        Thread.sleep( 5000 );

        // Select the recipe
        for ( final WebElement we : list ) {
            if ( name.equals( we.getAttribute( "value" ) ) ) {
                we.click();
                // Wait for thread to perform operation
                while ( !we.isSelected() ) {
                    Thread.sleep( 5000 );
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Create valid coffee
     *
     * @throws Exception
     *
     */
    private void makeCoffee ( final String recipeName, final int price, final int amtCoffee, final int amtMilk,
            final int amtSugar, final int amtChocolate, final int paid, final String expectedMessage )
            throws Exception {
        createRecipe( recipeName, price, amtCoffee, amtMilk, amtSugar, amtChocolate );

        driver.get( baseUrl + "" );
        driver.findElement( By.linkText( "Make Coffee" ) ).click();

        selectRecipe( recipeName );

        try {
            driver.findElement( By.name( "amtPaid" ) ).clear();
            driver.findElement( By.name( "amtPaid" ) ).sendKeys( paid + "" );
        }
        catch ( final Exception e ) {
            System.out.println( driver.getCurrentUrl() );
            System.out.println( driver.getPageSource() );
            Assert.fail();
        }

        // Submit
        System.out.println( recipeName + " " + price + " " + amtCoffee + " " + amtMilk + " " + " " + amtSugar + " "
                + amtChocolate + " " + paid + " " + expectedMessage );
        driver.findElement( By.cssSelector( "input[type=\"submit\"]" ) ).click();
        Thread.sleep( 5000 );

        // Make sure the proper message was displayed.
        assertTextPresent( expectedMessage, driver );
    }

    /**
     * Test for making coffee (valid) Expect to get an appropriate success
     * message.
     *
     * @throws Exception
     */
    @Test
    public void testValidMakeCoffee () throws Exception {
        makeCoffee( "Coffee", 60, 0, 3, 7, 2, 60, "Coffee was made" );
        makeCoffee( "Coffee", 60, 5, 0, 7, 2, 60, "Coffee was made" );
        makeCoffee( "Coffee", 60, 5, 3, 0, 2, 60, "Coffee was made" );
        makeCoffee( "Coffee", 60, 5, 3, 0, 2, 60, "Coffee was made" );
        makeCoffee( "Coffee", 60, 5, 3, 7, 0, 60, "Coffee was made" );
        makeCoffee( "Coffee", 60, 5, 3, 7, 2, 100, "Coffee was made" );
        makeCoffee( "Coffee", 60, 5, 3, 7, 2, 61, "Coffee was made" );
    }

    /**
     * Test for making coffee (invalid) Expect to get an appropriate failure
     * message
     *
     * @throws Exception
     */
    @Test
    public void testInvalidMakeCoffee () throws Exception {
        makeCoffee( "Coffee", 60, 0, 3, 7, 2, 59, "Error while making recipe" );
        makeCoffee( "Coffee", 60, 5, 0, 7, 2, -1, "Error while making recipe" );
    }

    @Override
    @After
    public void tearDown () {
        final String verificationErrorString = verificationErrors.toString();
        if ( !"".equals( verificationErrorString ) ) {
            fail( verificationErrorString );
        }
    }
}
