package com.cafe.tea;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import com.cafe.api.TeaService;

public class Activator implements BundleActivator {

	private ServiceRegistration<TeaService> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		TeaService teaService = new TeaMaker();
		registration = context.registerService(TeaService.class, teaService, null);
		System.out.println(" Tea maker started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		registration.unregister();
		System.out.println(" Tea maker stopped");
	}
}
