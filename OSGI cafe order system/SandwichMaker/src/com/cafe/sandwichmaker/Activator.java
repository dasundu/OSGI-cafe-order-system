package com.cafe.sandwichmaker;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.SandwichService;

public class Activator implements BundleActivator {

    private ServiceRegistration<SandwichService> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        // Ensure the correct class type (SandwichMaker implements SandwichService)
        SandwichService sandwichService = new SandwichMaker(); // SandwichMaker should implement SandwichService
        registration = context.registerService(SandwichService.class, sandwichService, null);

        if (registration != null) {
            System.out.println("✅ Sandwich Service Registered successfully!");
        } else {
            System.out.println("❌ Failed to register Sandwich Service.");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (registration != null) {
            registration.unregister();
            System.out.println("❌ Sandwich Service Unregistered.");
        }
    }

}
