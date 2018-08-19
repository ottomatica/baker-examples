package edu.ncsu.csc.test_utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

// Based heavily on the SharedDriver example found at
// https://github.com/cucumber/cucumber-jvm/blob/master/examples/java-webbit-websockets-selenium/src/test/java/cucumber/examples/java/websockets/SharedDriver.java

/**
 * Test Driver for coffee maker
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
public class CoffeeMakerDriver extends EventFiringWebDriver {
    private static final WebDriver REAL_DRIVER  = new HtmlUnitDriver(true);
    private static final String    BASE_URL     = "http://localhost:8080";
    private static final Thread    CLOSE_THREAD = new Thread() {
                                                    @Override
                                                    public void run () {
                                                        REAL_DRIVER.close();
                                                    }
                                                };

    static {
        Runtime.getRuntime().addShutdownHook( CLOSE_THREAD );
    }

    /**
     * Constructs the driver.
     */
    public CoffeeMakerDriver () {
        super( REAL_DRIVER );
    }

    @Override
    public void close () {
        if ( Thread.currentThread() != CLOSE_THREAD ) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits." );
        }
        super.close();
    }

    /**
     * Loads the page.
     *
     * @param url
     *            to load
     */
    public void loadPage ( final String url ) {
        REAL_DRIVER.get( BASE_URL + url );
    }

    /**
     * Returns true if the location is correct.
     *
     * @param url
     *            url to check
     * @return true if the same
     */
    public boolean verifyLocation ( final String url ) {
        return REAL_DRIVER.getCurrentUrl().equalsIgnoreCase( BASE_URL + url );
    }

}
