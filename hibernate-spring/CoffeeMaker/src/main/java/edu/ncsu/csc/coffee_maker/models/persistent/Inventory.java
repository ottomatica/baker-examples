package edu.ncsu.csc.coffee_maker.models.persistent;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

/**
 * Inventory for the coffee maker. Inventory is tied to the database using
 * Hibernate libraries.
 *
 * @author Kai Presler-Marshall
 * @author Michelle Lemons
 * @author Elizabeth Gilbert
 * @author Sarah Heckman
 */
@Entity
@Table ( name = "inventory" )
@Validated
public class Inventory extends DomainObject<Inventory> {

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
     * Empty constructor for Hibernate
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
    @Override
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
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
     * Returns true if there are enough ingredients to make the beverage.
     *
     * @param r
     *            recipe to check if there are enough ingredients
     * @return true if enough ingredients to make the beverage
     */
    public boolean enoughIngredients ( final Recipe r ) {
        boolean isEnough = true;
        if ( coffee < r.getCoffee() ) {
            isEnough = false;
        }
        if ( milk < r.getMilk() ) {
            isEnough = false;
        }
        if ( sugar < r.getSugar() ) {
            isEnough = false;
        }
        if ( chocolate < r.getChocolate() ) {
            isEnough = false;
        }
        return isEnough;
    }

    /**
     * Removes the ingredients used to make the specified recipe. Assumes that
     * the user has checked that there are enough ingredients to make
     *
     * @param r
     *            recipe to make
     * @return true if recipe is made.
     */
    public boolean useIngredients ( final Recipe r ) {
        if ( enoughIngredients( r ) ) {
            setCoffee( coffee - r.getCoffee() );
            setMilk( milk - r.getMilk() );
            setSugar( sugar - r.getSugar() );
            setChocolate( chocolate - r.getChocolate() );
            return true;
        }
        else {
            return false;
        }
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

    /**
     * Gets the system's inventory
     *
     * @return inventory for the CoffeeMaker
     */
    @SuppressWarnings ( "unchecked" )
    public static Inventory getInventory () {
        final List<Inventory> inventoryList = (List<Inventory>) DomainObject.getAll( Inventory.class );
        if ( inventoryList != null && inventoryList.size() == 1 ) {
            return inventoryList.get( 0 );
        }
        else {
            // initialize the inventory with 0 of everything
            final Inventory i = new Inventory( 0, 0, 0, 0 );
            i.save();
            return i;
        }

        
    }
}
