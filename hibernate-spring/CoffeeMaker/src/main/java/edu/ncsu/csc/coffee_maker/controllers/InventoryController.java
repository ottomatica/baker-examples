package edu.ncsu.csc.coffee_maker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for the inventory page of CoffeeMaker. The controller
 * returns inventory.html in the /src/main/resources/templates folder. The page
 * also includes the associated form for entering more inventory.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 * @author Sarah Heckman
 */
@Controller
public class InventoryController {

    /**
     * Handles a GET request for inventory. The GET request provides a view to
     * the client that includes the list of the current ingredients in the
     * inventory and a form where the client can enter more ingredients to add
     * to the inventory.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( "/inventory" )
    public String inventoryForm ( final Model model ) {
        return "inventory";
    }

}
