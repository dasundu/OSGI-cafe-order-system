package com.cafe.teaorderscreen;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.api.TeaOrderListener;
import com.cafe.api.TeaService;

public class TeaOrderScreen implements TeaOrderListener {

    private final TeaService teaMaker;

    // Constructor that receives TeaService and BundleContext
    public TeaOrderScreen(ServiceReference <TeaService> teaMakerRef, BundleContext context) {
        // Get the TeaService using the service reference
    	if(teaMakerRef == null) {
    		System.err.println("Tea ref is empty");
    	}
        this.teaMaker = (teaMakerRef != null) ? context.getService(teaMakerRef) : null;
    }

    @Override
    public void onTeaOrder(String customerName, String flavor) {
        if (teaMaker != null) {
            // Use TeaService to make tea
            teaMaker.makeTea(customerName, flavor);
            System.out.println(" TEA SCREEN :: Order received for " + customerName + ": " + flavor + " tea.");
        } else {
            System.err.println(" No tea maker is available! Cannot process order for " + customerName);
        }
    }
}
