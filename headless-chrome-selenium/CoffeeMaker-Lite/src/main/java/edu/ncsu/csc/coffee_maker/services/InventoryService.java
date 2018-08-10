package edu.ncsu.csc.coffee_maker.services;

import org.springframework.stereotype.Service;

import edu.ncsu.csc.coffee_maker.models.persistent.Inventory;
import edu.ncsu.csc.coffee_maker.models.persistent.Orders;
import edu.ncsu.csc.coffee_maker.models.persistent.Orders.Order;
import java.util.List;

/**
 * Service for working with the inventory.
 *
 * @author Sarah Heckman
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 */
@Service ( "inventoryService" )
public class InventoryService {

    private static Inventory inventory;
    private static Orders orders;

    static {
        inventory = populateInventory();
        orders = new Orders();
    }

    /**
     * Returns the inventory
     *
     * @return the inventory
     */
    public Inventory getInventory () {
        return inventory;
    }

    public List<Order> getOrders () {
        return orders.getOrders();
    }

    /**
     * Updates the InventoryService's inventory with the given values.
     *
     * @param inventory
     *            information to update
     */
    public void updateInventory ( final Inventory inventory ) {
        InventoryService.inventory.setCoffee( inventory.getCoffee() );
        InventoryService.inventory.setMilk( inventory.getMilk() );
        InventoryService.inventory.setSugar( inventory.getSugar() );
        InventoryService.inventory.setChocolate( inventory.getChocolate() );
    }

    /**
     * Returns true if inventory was successfully added.
     *
     * @param inventory
     *            Inventory with new ingredients
     */
    public void addInventory ( final Inventory inventory ) throws IllegalArgumentException {
     
        orders.addOrder( inventory );

        InventoryService.inventory.addIngredients( inventory.getCoffee(), inventory.getMilk(), inventory.getSugar(),
                inventory.getChocolate() );
    }

    /**
     * Returns the inventory of the coffee maker
     *
     * @return Inventory
     */
    public String checkInventory () {
        return InventoryService.inventory.toString();
    }

    private static Inventory populateInventory() {
        final Inventory initInventory = new Inventory(15, 15, 15, 15);
        return initInventory;
    }

}
