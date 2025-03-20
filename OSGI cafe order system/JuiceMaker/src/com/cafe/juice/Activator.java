package com.cafe.juice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.JuiceService;

public class Activator implements BundleActivator {

    private ServiceRegistration<JuiceService> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        JuiceService juiceService = new JuiceMaker();
        registration = context.registerService(JuiceService.class, juiceService, null);
        System.out.println(" Juice maker started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
        System.out.println(" Juice maker stoped.");
    }
}
