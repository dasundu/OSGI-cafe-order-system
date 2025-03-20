package cupcakeorderscreen;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.api.CupCakeOrderListener;
import com.cafe.api.CupCakeService;

public class CupCakeOrderScreen implements CupCakeOrderListener {

    private final CupCakeService cupCakeMaker;

    // Constructor retrieves CupCakeService using OSGi
    public CupCakeOrderScreen(ServiceReference<CupCakeService> cupCakeMakerRef, BundleContext context) {
        this.cupCakeMaker = (cupCakeMakerRef != null) ? context.getService(cupCakeMakerRef) : null;
    }

    @Override
    public void onCupCakeOrder(String customerName, String flavor) {
        if (cupCakeMaker != null) {
            System.out.println(" Cup cake Display :: Order received for " + customerName + ": " + flavor + " cupcake.");
            System.out.println(" Cup cake Display :: Sent signal to the cup cake maker.");
            cupCakeMaker.makeCupCake(customerName, flavor);
        } else {
            System.err.println("❌ No CupCakeService available! Cannot process order for " + customerName);
        }
    }
}
