package edu.ncsu.csc.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;

import junit.framework.TestCase;

/**
 * Base Selenium test. Contains helper methods for checking text.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
abstract class SeleniumTest extends TestCase {

    final static private String OS = System.getProperty( "os.name" );

    static protected WebDriver  driver;

    @Override
    protected void setUp () throws Exception {
        driver = BrowserHandler.getInstance().getDriver();
    }

    static private boolean Mac () {
        return OS.contains( "Mac OS X" );
    }

    static private boolean Linux () {
        return OS.contains( "Linux" );
    }

    static private boolean Windows () {
        return OS.contains( "Windows" );
    }

    public void close () {
        driver.close();
        driver.quit();

        if ( Windows() ) {
            windowsKill();
        }
        else if ( Linux() || Mac() ) {
            unixKill();
        }

    }

    static private void windowsKill () {
        try {
            Runtime.getRuntime().exec( "taskkill /f /im chrome.exe" );
            Runtime.getRuntime().exec( "taskkill /f /im chromedriver.exe" );
        }
        catch ( final Exception e ) {
        }
    }

    static private void unixKill () {
        try {
            Runtime.getRuntime().exec( "pkill -f chromium-browser" );
            Runtime.getRuntime().exec( "pkill -f chrome" );
            Runtime.getRuntime().exec( "pkill -f chromedriver" );
        }
        catch ( final Exception e ) {
        }

    }

    /**
     * Asserts that the text is on the page
     *
     * @param text
     *            text to check
     * @param driver
     *            web driver
     */
    public void assertTextPresent ( final String text, final WebDriver driver ) {
        final List<WebElement> list = driver.findElements( By.xpath( "//*[contains(text(),'" + text + "')]" ) );
        assertTrue( "Text not found!", list.size() > 0 );
    }

    /**
     * Asserts that the text is not on the page. Does not pause for text to
     * appear.
     *
     * @param text
     *            text to check
     * @param driver
     *            web driver
     */
    public void assertTextNotPresent ( final String text, final WebDriver driver ) {
        assertFalse( "Text should not be found!",
                driver.findElement( By.cssSelector( "BODY" ) ).getText().contains( text ) );
    }

    /**
     * wait method that will let angular finish loading before continuing
     */
    protected void waitForAngular () {
        new NgWebDriver( (ChromeDriver) driver ).waitForAngularRequestsToFinish();
    }

}
