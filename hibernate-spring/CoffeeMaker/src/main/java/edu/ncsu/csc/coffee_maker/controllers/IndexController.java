package edu.ncsu.csc.coffee_maker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for the index (or front page) of CoffeeMaker. The controller
 * returns index.html in the /src/main/resources/templates folder.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 * @author Sarah Heckman
 */
@Controller
public class IndexController {

    /**
     * On a GET request to /index, the IndexController will return
     * /src/main/resources/templates/index.html.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/index", "/" } )
    public String index ( final Model model ) {
        return "index";
    }
}
