/**
 *
 */
package edu.ncsu.csc.testUtils.web;

import org.openqa.selenium.WebDriver;

/**
 * Abstract Class providing shared elements and methods between PageObjects
 *
 * @author seelder
 */
public abstract class AbstractPageObject {
    /** Web driver for testing */
    protected WebDriver driver;

    /**
     * Constructor
     */
    public AbstractPageObject () {
        driver = null;

    }

    /**
     * Generic function used to set the webdriver used by each page Does NOT
     * enforce the singleton pattern (should be handled elsewhere)
     *
     * @param wd
     *            current WebDriver
     */
    public void setDriver ( final WebDriver wd ) {
        driver = wd;
    }

}
