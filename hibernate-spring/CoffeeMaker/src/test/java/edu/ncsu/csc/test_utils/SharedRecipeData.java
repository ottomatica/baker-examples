package edu.ncsu.csc.test_utils;

import java.util.List;
import java.util.Vector;

import edu.ncsu.csc.coffee_maker.models.persistent.Recipe;

/**
 * Shared information about Recipes for Cucumber tests.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
public class SharedRecipeData {
    /** Last recipe added */
    public boolean      latestRecipeAdded;
    /** Current recipe */
    public Recipe       currentRecipe;
    /** List of recipse */
    public List<Recipe> currentRecipeList;
    /** Recipe index */
    public int          index;
    /** Error messages */
    public String       recipeError;

    /**
     * Initializes the shared data.
     */
    public SharedRecipeData () {
        latestRecipeAdded = false;
        currentRecipe = new Recipe();
        currentRecipeList = new Vector<Recipe>();
        index = 0;
        recipeError = "";
    }

}
