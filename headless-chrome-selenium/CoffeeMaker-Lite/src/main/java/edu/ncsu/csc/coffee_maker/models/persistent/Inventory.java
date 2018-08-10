package edu.ncsu.csc.coffee_maker.models.persistent;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

/**
 * Inventory for the coffee maker. Inventory is tied to the database using
 * Hibernate libraries.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 * @author Sarah Heckman
 */
@Validated
public class Inventory {

    private Long id;
    @Min ( 0 )
    private int  coffee;
    @Min ( 0 )
    private int  milk;
    @Min ( 0 )
    private int  sugar;
    @Min ( 0 )
    private int  chocolate;

    /**
     * Creates a coffee maker inventory object and fills each item in the
     * inventory with 15 units.
     */
    public Inventory () {
        // Intentionally empty so that Hibernate can instantiate
        // Inventory object.
    }

    /**
     * Use this to create inventory with specified amts.
     *
     * @param coffee
     *            amt of coffee
     * @param milk
     *            amt of milk
     * @param sugar
     *            amt of sugar
     * @param chocolate
     *            amt of chocolate
     */
    public Inventory ( final int coffee, final int milk, final int sugar, final int chocolate ) {
        setCoffee( coffee );
        setMilk( milk );
        setSugar( sugar );
        setChocolate( chocolate );
    }

    /**
     * Returns the ID of the entry in the DB
     *
     * @return long
     */
    public Long getId () {
        return id;
    }

    /**
     * Set the ID of the Inventory (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Returns the current number of chocolate units in the inventory.
     *
     * @return amount of chocolate
     */
    public int getChocolate () {
        return chocolate;
    }

    /**
     * Sets the number of chocolate units in the inventory to the specified
     * amount.
     *
     * @param amtChocolate
     *            amount of chocolate to set
     */
    public void setChocolate ( final int amtChocolate ) {
        if ( chocolate >= 0 ) {
            chocolate = amtChocolate;
        }
    }

    /**
     * Add the number of chocolate units in the inventory to the current amount
     * of chocolate units.
     *
     * @param chocolate
     *            amount of chocolate
     * @return checked amount of chocolate
     * @throws InventoryException
     */
    public int checkChocolate ( final String chocolate ) throws IllegalArgumentException {
        int amtChocolate = 0;
        try {
            amtChocolate = Integer.parseInt( chocolate );
        }
        catch ( final NumberFormatException e ) {
            throw new IllegalArgumentException( "Units of chocolate must be a positive integer" );
        }
        if ( amtChocolate < 0 ) {
            throw new IllegalArgumentException( "Units of chocolate must be a positive integer" );
        }

        return amtChocolate;
    }

    /**
     * Returns the current number of coffee units in the inventory.
     *
     * @return amount of coffee
     */
    public int getCoffee () {
        return coffee;
    }

    /**
     * Sets the number of coffee units in the inventory to the specified amount.
     *
     * @param amtCoffee
     *            amount of coffee to set
     */
    public void setCoffee ( final int amtCoffee ) {
        if ( coffee >= 0 ) {
            coffee = amtCoffee;
        }
    }

    /**
     * Add the number of coffee units in the inventory to the current amount of
     * coffee units.
     *
     * @param coffee
     *            amount of coffee
     * @return checked amount of coffee
     * @throws InventoryException
     */
    public int checkCoffee ( final String coffee ) throws IllegalArgumentException {
        int amtCoffee = 0;
        try {
            amtCoffee = Integer.parseInt( coffee );
        }
        catch ( final NumberFormatException e ) {
            throw new IllegalArgumentException( "Units of coffee must be a positive integer" );
        }
        if ( amtCoffee < 0 ) {
            throw new IllegalArgumentException( "Units of coffee must be a positive integer" );
        }

        return amtCoffee;
    }

    /**
     * Returns the current number of milk units in the inventory.
     *
     * @return int
     */
    public int getMilk () {
        return milk;
    }

    /**
     * Sets the number of milk units in the inventory to the specified amount.
     *
     * @param amtMilk
     *            amount of milk to set
     */
    public void setMilk ( final int amtMilk ) {
        if ( milk >= 0 ) {
            milk = amtMilk;
        }
    }

    /**
     * Add the number of milk units in the inventory to the current amount of
     * milk units.
     *
     * @param milk
     *            amount of milk
     * @return checked amount of milk
     * @throws InventoryException
     */
    public int checkMilk ( final String milk ) throws IllegalArgumentException {
        int amtMilk = 0;
        try {
            amtMilk = Integer.parseInt( milk );
        }
        catch ( final NumberFormatException e ) {
            throw new IllegalArgumentException( "Units of milk must be a positive integer" );
        }
        if ( amtMilk < 0 ) {
            throw new IllegalArgumentException( "Units of milk must be a positive integer" );
        }

        return amtMilk;
    }

    /**
     * Returns the current number of sugar units in the inventory.
     *
     * @return int
     */
    public int getSugar () {
        return sugar;
    }

    /**
     * Sets the number of sugar units in the inventory to the specified amount.
     *
     * @param amtSugar
     *            amount of sugar to set
     */
    public void setSugar ( final int amtSugar ) {
        if ( sugar >= 0 ) {
            sugar = amtSugar;
        }
    }

    /**
     * Add the number of sugar units in the inventory to the current amount of
     * sugar units.
     *
     * @param sugar
     *            amount of sugar
     * @return checked amount of sugar
     * @throws InventoryException
     */
    public int checkSugar ( final String sugar ) throws IllegalArgumentException {
        int amtSugar = 0;
        try {
            amtSugar = Integer.parseInt( sugar );
        }
        catch ( final NumberFormatException e ) {
            throw new IllegalArgumentException( "Units of sugar must be a positive integer" );
        }
        if ( amtSugar < 0 ) {
            throw new IllegalArgumentException( "Units of sugar must be a positive integer" );
        }

        return amtSugar;
    }


    /**
     * Adds ingredients to the inventory
     *
     * @param coffee
     *            amt of coffee
     * @param milk
     *            amt of milk
     * @param sugar
     *            amt of sugar
     * @param chocolate
     *            amt of chocolate
     * @return true if successful, false if not
     */
    public boolean addIngredients ( final int coffee, final int milk, final int sugar, final int chocolate ) {
        if ( coffee < 0 || milk < 0 || sugar < 0 || chocolate < 0 ) {
            throw new IllegalArgumentException( "Amount cannot be negative" );
        }

        setCoffee( this.coffee + coffee );
        setMilk( this.milk + milk );
        setSugar( this.sugar + sugar );
        setChocolate( this.chocolate + chocolate );


        return true;
    }

    /**
     * Returns a string describing the current contents of the inventory.
     *
     * @return String
     */
    @Override
    public String toString () {
        final StringBuffer buf = new StringBuffer();
        buf.append( "Coffee: " );
        buf.append( getCoffee() );
        buf.append( "\n" );
        buf.append( "Milk: " );
        buf.append( getMilk() );
        buf.append( "\n" );
        buf.append( "Sugar: " );
        buf.append( getSugar() );
        buf.append( "\n" );
        buf.append( "Chocolate: " );
        buf.append( getChocolate() );
        buf.append( "\n" );
        return buf.toString();
    }
}
