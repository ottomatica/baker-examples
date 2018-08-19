package edu.ncsu.csc.coffee_maker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for adding a recipe. The controller returns recipe.html in
 * the /src/main/resources/templates folder.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 * @author Sarah Heckman
 */
@Controller
public class RecipeController {

    /**
     * On a GET request to /recipe, the RecipeController will return
     * /src/main/resources/templates/recipe.html.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( "/recipe" )
    public String recipeForm ( final Model model ) {
        return "recipe";
    }
}
