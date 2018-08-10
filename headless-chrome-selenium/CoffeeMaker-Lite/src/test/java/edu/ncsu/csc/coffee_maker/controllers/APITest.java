package edu.ncsu.csc.coffee_maker.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ncsu.csc.coffee_maker.models.persistent.Inventory;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Perform a quick check of one of the API methods to ensure that the API
 * controller is up and receiving requests as it should be
 *
 * @author Kai Presler-Marshall
 *
 */
@RunWith ( SpringRunner.class )
@SpringBootTest ( properties = "logging.level.org.springframework.web=DEBUG" )
@AutoConfigureMockMvc
public class APITest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * Sets up the tests.
     */
    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();
    }

    /**
     * Tests that we are able to make a call to the REST API. If such a call
     * cannot be made, throws an exception instead
     *
     * @throws Exception
     */
    @Test
    public void testApi () throws Exception {
        // Verify that we are able to make a request to the Inventory API
        // endpoint and that we get a 200 (OK) status in return
        mvc.perform( get( "/api/v1/inventory" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath("$.coffee", is(15) ) );

    }

    @Test
    public void testPut () throws Exception {
        // Verify that we are able to add coffee
        final Inventory inventory = new Inventory(1,0,0,0);
        mvc.perform( put( "/api/v1/inventory" )
                     .contentType(MediaType.APPLICATION_JSON)
                     .content( new Gson().toJson(inventory) )
        )
        .andExpect( status().isOk() )
        .andExpect( jsonPath("$.coffee", is(16) ) )
        .andExpect( jsonPath("$.milk", is(15) ) )
        ;
    }

    @Test
    public void getMissingOrder () throws Exception {
        // Verify that we non existant orders produce errors
        mvc.perform( get( "/api/v1/orders/100" ) )
        .andExpect( status().is4xxClientError() );
    }

    @Test 
    public void getExistingOrder() throws Exception {
        mvc.perform( get( "/api/v1/orders/1" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath("$.orderNumber", is(1) ) )
        .andExpect( jsonPath("$.coffee", is(1) ) )
        .andExpect( jsonPath("$.milk", is(0) ) );
    }
}
