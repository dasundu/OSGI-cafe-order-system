package com.cafe.sandwichorderscreen;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.api.SandwichOrderListener;
import com.cafe.api.SandwichService;

public class SandwichOrderScreen implements SandwichOrderListener {

    private final SandwichService sandwichMaker;

    // Constructor that accepts SandwichService and BundleContext
    public SandwichOrderScreen(ServiceReference<SandwichService> sandwichMakerRef, BundleContext context) {
        // Get the SandwichService using the service reference
        this.sandwichMaker = (sandwichMakerRef != null) ? context.getService(sandwichMakerRef) : null;
    }

    @Override
    public void onSandwichOrder(String customerName, String sandwichType) {
        if (sandwichMaker != null) {
            // Use the SandwichService to make the sandwich
        	System.out.println(" Sandwich order screen :: Order received for " + customerName + "." + sandwichType.toLowerCase() + " sandwich.");
        	System.out.println(" Sandwich order screen :: Sent signal to sandwich maker.");
            sandwichMaker.makeSandwich(customerName, sandwichType);
        } else {
            System.err.println("❌ No sandwich maker is available! Cannot process order for " + customerName);
        }
    }
}
