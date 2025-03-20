package com.cafe.teaorderscreen;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.TeaOrderListener;
import com.cafe.api.TeaService;

public class Activator implements BundleActivator {

    private ServiceRegistration<TeaOrderListener> registration;
    private ServiceReference <TeaService> teaMaker_Ref;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Tea Order Screen Started");

        // Get the reference for TeaService from OSGi registry
        teaMaker_Ref = context.getServiceReference(TeaService.class);
        
        if(teaMaker_Ref == null)
        {
        	System.err.println("Tea ref is empty in activator");
        }

        // Create the TeaOrderScreen, passing the service reference and context
        TeaOrderListener teaOrderScreen = new TeaOrderScreen(teaMaker_Ref, context);

        // Register the TeaOrderListener service in OSGi
        registration = context.registerService(TeaOrderListener.class, teaOrderScreen, null);

        System.out.println("✅ Tea order screen started");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the TeaOrderListener service to clean up resources
        if (registration != null) {
            registration.unregister();
            System.out.println("❌ Tea Order Screen - Service Unregistered");
        }
    }
}
