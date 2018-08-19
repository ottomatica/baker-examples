package edu.ncsu.csc.coffee_maker;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test the WebLayer of CoffeeMaker.
 *
 * Reference: https://spring.io/guides/gs/testing-web/
 *
 * @author Sarah Heckman
 * @author Kai Presler-Marshall
 */
@RunWith ( SpringRunner.class )
@SpringBootTest ( properties = "logging.level.org.springframework.web=DEBUG" )
@AutoConfigureMockMvc
public class WebLayerTest {

    private MockMvc               mockMvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * Sets up the tests.
     */
    @Before
    public void setup () {
        mockMvc = MockMvcBuilders.webAppContextSetup( context ).build();
    }

    /**
     * Tests that the web layer returns 400 OK and that the text at the top of
     * the page is correct.
     *
     * @throws Exception
     *             on error
     */
    @Test
    public void testCoffeeMakerIndex () throws Exception {

        this.mockMvc.perform( get( "/" ) ).andDo( print() ).andExpect( status().isOk() )
                .andExpect( content().string( containsString( "Coffee Maker" ) ) );
    }

}
