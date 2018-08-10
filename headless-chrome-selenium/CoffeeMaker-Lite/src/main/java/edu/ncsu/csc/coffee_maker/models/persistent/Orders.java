package edu.ncsu.csc.coffee_maker.models.persistent;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

@Validated
public class Orders {

    List<Order> _orders = new ArrayList<Order>();
    public static class Order
    {
        private int id;
        @Min ( 0 )
        private int  coffee;
        @Min ( 0 )
        private int  milk;
        @Min ( 0 )
        private int  sugar;
        @Min ( 0 )
        private int  chocolate;

        public Order ( final int orderNumber, final int coffee, final int milk, final int sugar, final int chocolate ) {
            this.id = orderNumber;
            this.coffee = coffee;
            this.milk = milk;
            this.sugar = sugar;
            this.chocolate = chocolate;
        }

        public int getOrderNumber() {return id;}
        public int getCoffee() {return coffee;}
        public int getMilk() {return milk;}
        public int getSugar() {return sugar;}
        public int getChocolate() {return chocolate;}
    }

    public List<Order> getOrders()
    {
        return _orders;
    }

    public void addOrder(final Inventory inventory)
    {
        addOrder(inventory.getCoffee(), inventory.getMilk(), inventory.getSugar(), inventory.getChocolate());
    }

    public void addOrder(final int coffee, final int milk, final int sugar, final int chocolate)
    {
        int orderNumber = _orders.size()+1;
        _orders.add( new Order(orderNumber, coffee, milk, sugar, chocolate));
    }
}