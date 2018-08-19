package edu.ncsu.csc.coffee_maker.models.persistent;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Criterion;

/**
 * Recipe for the coffee maker. Recipe is tied to the database using Hibernate
 * libraries.
 *
 * @author Sarah Heckman
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
@Entity
@Table ( name = "recipes" )
public class Recipe extends DomainObject<Recipe> {

    private Long    id;

    @NotNull
    private String  name;

    @Min ( 0 )
    private Integer price;

    @Min ( 0 )
    private Integer coffee;

    @Min ( 0 )
    private Integer milk;

    @Min ( 0 )
    private Integer sugar;

    @Min ( 0 )
    private Integer chocolate;

    /**
     * Creates a default recipe for the coffee maker.
     */
    public Recipe () {
        this.id = 0L;
        this.name = "";
        this.price = 0;
        this.coffee = 0;
        this.milk = 0;
        this.sugar = 0;
        this.chocolate = 0;
    }

    /**
     * Get the ID of the Recipe
     *
     * @return the ID
     */
    @Override
    @Id
    @GeneratedValue ( generator = "increment" )
    @GenericGenerator ( name = "increment", strategy = "increment" )
    public Long getId () {
        return id;
    }

    /**
     * Set the ID of the Recipe (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    @SuppressWarnings ( "unused" )
    private void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Returns amount of chocolate in the recipe.
     *
     * @return Returns the amtChocolate.
     */
    public Integer getChocolate () {
        return chocolate;
    }

    /**
     * Sets the amount of chocolate in the recipe.
     *
     * @param chocolate
     *            The amtChocolate to set.
     */
    public void setChocolate ( final int chocolate ) {
        this.chocolate = chocolate;
    }

    /**
     * Returns amount of coffee in the recipe.
     *
     * @return Returns the amtCoffee.
     */
    public Integer getCoffee () {
        return coffee;
    }

    /**
     * Sets the amount of coffee in the recipe.
     *
     * @param coffee
     *            The amtCoffee to set.
     */
    public void setCoffee ( final int coffee ) {
        this.coffee = coffee;
    }

    /**
     * Returns amount of milk in the recipe.
     *
     * @return Returns the amtMilk.
     */
    public Integer getMilk () {
        return milk;
    }

    /**
     * Sets the amount of milk in the recipe.
     *
     * @param milk
     *            The amtMilk to set.
     */
    public void setMilk ( final int milk ) {
        this.milk = milk;
    }

    /**
     * Returns amount of sugar in the recipe.
     *
     * @return Returns the amtSugar.
     */
    public Integer getSugar () {
        return sugar;
    }

    /**
     * Sets the amount of sugar in the recipe.
     *
     * @param sugar
     *            The amtSugar to set.
     */
    public void setSugar ( final int sugar ) {
        this.sugar = sugar;
    }

    /**
     * Returns name of the recipe.
     *
     * @return Returns the name.
     */
    public String getName () {
        return name;
    }

    /**
     * Sets the recipe name.
     *
     * @param name
     *            The name to set.
     */
    public void setName ( final String name ) {
        this.name = name;
    }

    /**
     * Returns the price of the recipe.
     *
     * @return Returns the price.
     */
    public Integer getPrice () {
        return price;
    }

    /**
     * Sets the recipe price.
     *
     * @param price
     *            The price to set.
     */
    public void setPrice ( final int price ) {
        this.price = price;
    }

    /**
     * Updates the fields to be equal to the passed Recipe
     *
     * @param r
     *            with updated fields
     */
    public void updateRecipe ( final Recipe r ) {
        setChocolate( r.getChocolate() );
        setCoffee( r.getCoffee() );
        setMilk( r.getMilk() );
        setSugar( r.getSugar() );
        setPrice( r.getPrice() );
    }

    /**
     * Returns the name of the recipe.
     *
     * @return String
     */
    @Override
    public String toString () {
        return name;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        return result;
    }

    @Override
    public boolean equals ( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if ( name == null ) {
            if ( other.name != null ) {
                return false;
            }
        }
        else if ( !name.equals( other.name ) ) {
            return false;
        }
        return true;
    }

    /**
     * Get all the associated Recipes given criteria constraints
     *
     * @param criteriaList
     *            list of what to search for
     * @return list of recipes that match the given criterias
     */
    @SuppressWarnings ( "unchecked" )
    public static List<Recipe> getWhere ( List<Criterion> criteriaList ) {
        return (List<Recipe>) DomainObject.getWhere( Recipe.class, criteriaList );
    }

    /**
     * Get all recipes in the system
     *
     * @return all recipes currently stored
     */
    @SuppressWarnings ( "unchecked" )
    public static List<Recipe> getAll () {
        return (List<Recipe>) DomainObject.getAll( Recipe.class );
    }

    /**
     * Gets a recipe from a given name
     *
     * @param name
     *            name of the recipe
     * @return recipe with the given name
     */
    public static Recipe getByName ( String name ) {
        return (Recipe) DomainObject.getBy( Recipe.class, "name", name );
    }

}
