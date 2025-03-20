package cupcakeorderscreen;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.CupCakeOrderListener;
import com.cafe.api.CupCakeService;

public class Activator implements BundleActivator {

    // Holds the registration reference for the CupCakeOrderListener service
    private ServiceRegistration<CupCakeOrderListener> registration;

    // Holds a reference to the CupCakeService, which this bundle depends on
    private ServiceReference<CupCakeService> cupCakeBakerRef;

    @Override
    public void start(BundleContext context) throws Exception {
        // Log that the Cup cake Order Screen bundle is starting
        System.out.println("✅ Cupcake order screen started");

        // Get a reference to the CupCakeService from OSGi's service registry
        cupCakeBakerRef = context.getServiceReference(CupCakeService.class);

        // Create an instance of CupCakeOrderScreen, passing the CupCakeService reference and context
        CupCakeOrderListener cupCakeOrderScreen = new CupCakeOrderScreen(cupCakeBakerRef, context);

        // Register the CupCakeOrderScreen as a CupCakeOrderListener service in OSGi
        registration = context.registerService(CupCakeOrderListener.class, cupCakeOrderScreen, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the CupCakeOrderListener service to clean up resources
        if (registration != null) {
            registration.unregister();
            System.out.println("❌ Cupcake Order Screen - Service Unregistered");
        }
    }
}
