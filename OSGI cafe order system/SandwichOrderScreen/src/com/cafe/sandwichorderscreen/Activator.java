package com.cafe.sandwichorderscreen;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.SandwichOrderListener;
import com.cafe.api.SandwichService;

public class Activator implements BundleActivator {

    private ServiceRegistration<SandwichOrderListener> registration;
    private ServiceReference<SandwichService> sandwichMakerRef;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Sandwich Order Screen - Ready to Display Orders");

        // Get the reference for SandwichService from OSGi registry
        sandwichMakerRef = context.getServiceReference(SandwichService.class);

        // Create SandwichOrderScreen, passing the SandwichService reference and BundleContext
        SandwichOrderListener sandwichOrderScreen = new SandwichOrderScreen(sandwichMakerRef, context);

        // Register SandwichOrderScreen as a service
        registration = context.registerService(SandwichOrderListener.class, sandwichOrderScreen, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the SandwichOrderListener service to clean up resources
        if (registration != null) {
            registration.unregister();
            System.out.println("❌ Sandwich Order Screen - Service Unregistered");
        }
    }
}
