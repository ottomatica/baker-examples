package edu.ncsu.csc.test_utils;

/**
 * Shared information about Inventory for Cucumber tests.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
public class SharedInventoryData {
    /** New inventory values */
    public int    newCoffee, newMilk, newSugar, newChocolate;
    /** Original inventory values */
    public int    originalCoffee, originalMilk, originalSugar, originalChocolate;
    /** Error messages */
    public String errorMessage;

    /**
     * Initializes the shared data.
     */
    public SharedInventoryData () {
        newCoffee = 0;
        newMilk = 0;
        newSugar = 0;
        newChocolate = 0;
        originalCoffee = 0;
        originalMilk = 0;
        originalSugar = 0;
        originalChocolate = 0;
        errorMessage = "";
    }

}
