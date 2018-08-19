package edu.ncsu.csc.coffee_maker.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Tests the InventoryController and that inventory is displayed and added
 * correctly.
 *
 * Reference: https://spring.io/guides/gs/testing-web/
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 * @author Sarah Heckman
 */
@RunWith ( SpringRunner.class )
@WebMvcTest ( InventoryController.class )
public class InventoryControllerTest {

    @Autowired
    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc mockMvc;

    /**
     * Retrieves the Inventory page and ensures that the page, as expected, lets
     * us view the current inventory of the CoffeeMaker
     *
     * @throws Exception
     */
    @Test
    public void testInventoryGet () throws Exception {
        this.mockMvc.perform( get( "/inventory" ) ).andDo( print() ).andExpect( status().isOk() )
                .andExpect( content().string( containsString( "View Inventory" ) ) );
    }

}
