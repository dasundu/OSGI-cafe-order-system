package com.cafe.coffeeorderscreen;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.api.CoffeeOrderListener;
import com.cafe.api.CoffeeService;

public class CoffeeOrderScreen implements CoffeeOrderListener {

    private final CoffeeService coffeeBrewer;

    // Constructor that receives a ServiceReference and retrieves the service instance
    public CoffeeOrderScreen(ServiceReference<CoffeeService> coffeeBrewerRef, BundleContext context) {
        this.coffeeBrewer = (coffeeBrewerRef != null) ? context.getService(coffeeBrewerRef) : null;
    }

    @Override
    public void onCoffeeOrder(String customerName) {
    	System.out.println(" Coffee Display :: Displayed Order for " + customerName +".");
    	System.out.println(" Coffee Display :: Checking the coffee brewer.");
        if (coffeeBrewer != null) {
            coffeeBrewer.makeCoffee(customerName);
        } else {
            System.err.println(" No Coffee Brewer is Service available! Cannot process order for " + customerName);
        }
    }
}
