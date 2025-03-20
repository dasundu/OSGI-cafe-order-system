package com.cafe.juice;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.api.JuiceOrderListener;
import com.cafe.api.JuiceService;

public class JuiceOrderScreen implements JuiceOrderListener {

    private final JuiceService juiceMaker;

    // Constructor that retrieves JuiceService using OSGi
    public JuiceOrderScreen(ServiceReference<JuiceService> juiceMakerRef, BundleContext context) {
        this.juiceMaker = (juiceMakerRef != null) ? context.getService(juiceMakerRef) : null;
    }

    @Override
    public void onJuiceOrder(String customerName) {
        if (juiceMaker != null) {
            System.out.println(" Juice display :: Juice prepared for " + customerName + ".");
            System.out.println(" Juice display :: Sent signal to juice maker.");
            juiceMaker.makeJuice(customerName);
            
        } else {
            System.err.println("❌ No Juice maker is available! Cannot process order for " + customerName);
        }
    }
}

