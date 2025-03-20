package com.cafe.coffeeorderscreen;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.CoffeeOrderListener;
import com.cafe.api.CoffeeService;

public class Activator implements BundleActivator {

    // Holds the registration reference for the CoffeeOrderListener service
    private ServiceRegistration<CoffeeOrderListener> registration;

    // Holds a reference to the CoffeeService, which this bundle depends on
    private ServiceReference<CoffeeService> coffeeBrewerRef;

    @Override
    public void start(BundleContext context) throws Exception {
        // Log that the Coffee Order Screen bundle is starting
        System.out.println("✅ Coffee order screen stared.");

        // Get a reference to the CoffeeService from OSGi's service registry
        coffeeBrewerRef = context.getServiceReference(CoffeeService.class);

        // Create an instance of CoffeeOrderScreen, passing the CoffeeService reference and context
        CoffeeOrderListener coffeeOrderScreen = new CoffeeOrderScreen(coffeeBrewerRef, context);

        // Register the CoffeeOrderScreen as a CoffeeOrderListener service in OSGi
        registration = context.registerService(CoffeeOrderListener.class, coffeeOrderScreen, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the CoffeeOrderListener service to clean up resources
        if (registration != null) {
            registration.unregister();
            System.out.println("🛑 Coffee Order Screen - Service Unregistered");
        }
    }
}
