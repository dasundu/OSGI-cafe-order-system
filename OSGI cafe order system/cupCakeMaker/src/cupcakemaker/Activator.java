package cupcakemaker;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.CupCakeService;

public class Activator implements BundleActivator {
    private ServiceRegistration<CupCakeService> registration;
    
    @Override
    public void start(BundleContext context) throws Exception {
        CupCakeService cupCakeService = new CupCakeBaker();
        registration = context.registerService(CupCakeService.class, cupCakeService, null);
        System.out.println("✅ CupCake Service Registered");
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        System.out.println("❌ CupCake Service Unregistered");
    }
}