package edu.ncsu.csc.testUtils.web;

import org.openqa.selenium.By;

import edu.ncsu.csc.coffee_maker.models.persistent.Inventory;

/**
 * PageObject for /CoffeeMaker_Cucumber/delete_recipe.jsp
 *
 * @author kpresler
 */
public class CheckInventoryPage extends AbstractPageObject {
    private final By            returnToMain = By.linkText( "Home" );
    final static private String VALUE        = "value";

    /**
     * Constructor
     */
    public CheckInventoryPage () {
        driver = null;
    }

    /**
     * Returns the Inventory
     *
     * @return the inentory
     * @throws Exception
     */
    public Inventory getInventory () throws Exception {
        final Inventory ret = new Inventory();
        final String coffee = driver.findElement( By.id( "currentCoffee" ) ).getAttribute( VALUE );
        final String milk = driver.findElement( By.id( "currentMilk" ) ).getAttribute( VALUE );
        final String sugar = driver.findElement( By.id( "currentSugar" ) ).getAttribute( VALUE );
        final String chocolate = driver.findElement( By.id( "currentChocolate" ) ).getAttribute( VALUE );

        try {
            ret.setCoffee( Integer.parseInt( coffee ) );
            ret.setMilk( Integer.parseInt( milk ) );
            ret.setSugar( Integer.parseInt( sugar ) );
            ret.setChocolate( Integer.parseInt( chocolate ) );
        }
        catch ( final Exception e ) {
            throw new Exception( "Inventory value not in anticipated location" );
        }

        return ret;
    }

    /**
     * Clicks the link to return to the main page
     */
    public void returnToMain () {
        driver.findElement( returnToMain ).click();
    }

}
