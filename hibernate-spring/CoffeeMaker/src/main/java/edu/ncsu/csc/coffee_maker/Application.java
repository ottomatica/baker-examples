package edu.ncsu.csc.coffee_maker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main.
 *
 * @author Sarah Heckman
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
@SpringBootApplication
public class Application {

    /**
     * Starts the program.
     *
     * @param args
     *            command line args
     */
    public static void main ( final String[] args ) {
        SpringApplication.run( Application.class, args );
    }
}
