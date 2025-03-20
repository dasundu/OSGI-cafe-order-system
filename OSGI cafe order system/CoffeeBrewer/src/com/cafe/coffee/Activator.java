package com.cafe.coffee;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.CoffeeService;

public class Activator implements BundleActivator {

    private ServiceRegistration<CoffeeService> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        CoffeeService coffeeService = new CoffeeBrewer();
        registration = context.registerService(CoffeeService.class, coffeeService, null);
        
        if(registration!=null) {
        	System.out.println("✅ Coffee Brewer Started ");
        }else {
        	System.err.println("✅ Coffee brewer could not started.");
        }
        
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (registration != null) {
            registration.unregister();  // Unregister the service
            registration = null;  // Clear reference
        }
        System.out.println("❌ Coffee Brewer Forcefully Stopped and Unregistered");
    }

}
