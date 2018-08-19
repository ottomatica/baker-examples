package edu.ncsu.csc.test_utils;

import edu.ncsu.csc.coffee_maker.models.persistent.Recipe;

/**
 * Shared information about CoffeeMaker for Cucumber tests.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
public class SharedCoffeeMakerData {
    /** New inventory values */
    public int    newCoffee, newMilk, newSugar, newChocolate;
    /** Original inventory values */
    public int    originalCoffee, originalMilk, originalSugar, originalChocolate;
    /** Information about money */
    public int    moneyGiven, change;
    /** Current recipe */
    public Recipe currentRecipe;
    /** Error messages */
    public String errorMessage;

    /**
     * Initializes the shared data.
     */
    public SharedCoffeeMakerData () {
        newCoffee = 0;
        newMilk = 0;
        newSugar = 0;
        newChocolate = 0;
        originalCoffee = 0;
        originalMilk = 0;
        originalSugar = 0;
        originalChocolate = 0;
        moneyGiven = 0;
        change = 0;
        currentRecipe = new Recipe();
        errorMessage = "";
    }
}
