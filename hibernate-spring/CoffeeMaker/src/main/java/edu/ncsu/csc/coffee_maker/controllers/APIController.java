package edu.ncsu.csc.coffee_maker.controllers;

import com.google.gson.Gson;

/**
 * Base class for all of the API controllers for manipulating DomainObjects. Add
 * in any fields or functionality that ought to be shared throughout.
 *
 * PLEASE NOTE: For (almost) all of the REST API endpoints that deal with the
 * creation or modification of DomainObject entities, we have opted to have the
 * API endpoint receive the appropriate *Form* object for each type instead of
 * the object itself. This is done because the entirely text-based forms are
 * easier for a client to generate rather than the complex object structure. For
 * instance, otherwise, when creating a new OfficeVisit, the JSON sent in by the
 * user would need to contain the _entire_ objects for the Patient and HCP
 * (including the username, hashed password, and status) as nested JSON. This
 * would be _very_ ugly and would make it much harder to use the API. Thus,
 * we're using _Forms_ for the API, where the user only has to provide the
 * _name_ (or other primary key) of a system resource that is presumed to
 * already exist. The JSON for the Form objects is automatically de-serialized
 * to a proper Java object by Spring, and then after that we manually convert
 * the Forms to proper entities before they can be saved in the database. This
 * allows proper validation of input and gets it into datastructures that use
 * proper, strong typing so that it is abundantly clear what is what.
 *
 * @author Kai Presler-Marshall
 *
 */
public abstract class APIController {
    /** Base path of API */
    static final protected String BASE_PATH = "/api/v1/";

    /**
     * Used to serialize data and messages to JSON for transmitting through the
     * REST API
     */
    static final private Gson     GSON      = new Gson();

    /**
     * Turns the provided object into JSON
     *
     * @param obj
     *            The object to serialize
     * @return The resulting JSON String
     */
    static final protected String toJson ( final Object obj ) {
        return GSON.toJson( obj );
    }

    /**
     * Turns the provided object into JSON, providing the class of the object to
     * give a better serialization. Use this if the type is known.
     *
     * @param obj
     *            The object to serialize to JSON
     * @param cls
     *            The class of the object
     * @return The resulting JSON String
     */
    static final protected String toJson ( final Object obj, final Class<JSONResponse> cls ) {
        return GSON.toJson( obj, cls );
    }

    /**
     * Creates a JSONResponse for sending an error or informational message back
     * to the user.
     *
     * @param status
     *            The status of the request to send
     * @param message
     *            The detailed message to send
     * @return The resulting JSON String
     */
    static final protected String responseMessage ( final String status, final String message ) {
        return toJson( new JSONResponse( status, message ), JSONResponse.class );
    }

    /**
     * Creates a JSONResponse, setting the status to failed and the message to
     * what is provided.
     *
     * @param message
     *            The detailed message to send
     * @return The resulting JSON String
     */
    static final protected String errorResponse ( final String message ) {
        return responseMessage( "failed", message );
    }

    /**
     * Creates a JSONResponse, setting the status to success and the message to
     * what is provided.
     *
     * @param message
     *            The detailed message to send
     * @return The resulting JSON String
     */
    static final protected String successResponse ( final String message ) {
        return responseMessage( "success", message );
    }

    /**
     * Small class used for creating simple success/error messages to return via
     * the REST API. Contains a status of the action and an message.
     *
     * @author Kai Presler-Marshall
     *
     */
    static protected class JSONResponse {

        /**
         * Status of the response (success/failed)
         */
        String status;

        /**
         * Message (what went wrong, something informational, etc)
         */
        String message;

        /**
         * Default constructor for JSONResponse.
         *
         * @param status
         *            The status (success/failed)
         * @param message
         *            The message; any informational message desired
         */
        public JSONResponse ( final String status, final String message ) {
            this.status = status;
            this.message = message;
        }
    }
}
