package com.cafe.juice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.JuiceOrderListener;
import com.cafe.api.JuiceService;

public class Activator implements BundleActivator {

    private ServiceRegistration<JuiceOrderListener> registration;
    private ServiceReference<JuiceService> juiceMakerRef;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Juice Order Screen Started");

        // Get the reference for JuiceService from OSGi registry
        juiceMakerRef = context.getServiceReference(JuiceService.class);

        // Create the JuiceOrderScreen, passing the service reference and context
        JuiceOrderListener juiceOrderListener = new JuiceOrderScreen(juiceMakerRef, context);

        // Register the JuiceOrderListener service in OSGi
        registration = context.registerService(JuiceOrderListener.class, juiceOrderListener, null);

        System.out.println("✅ Juice Order Screen - Ready to Display Juice Orders");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the JuiceOrderListener service to clean up resources
        if (registration != null) {
            registration.unregister();
            System.out.println("❌ Juice Order Screen - Service Unregistered");
        }
    }
}
