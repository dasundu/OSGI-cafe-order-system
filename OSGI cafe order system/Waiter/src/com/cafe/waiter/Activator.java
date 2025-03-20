package com.cafe.waiter;

import com.cafe.api.CoffeeOrderListener;
import com.cafe.api.CoffeeService;
import com.cafe.api.JuiceOrderListener;
import com.cafe.api.JuiceService;
import com.cafe.api.SandwichOrderListener;
import com.cafe.api.SandwichService;
import com.cafe.api.CupCakeOrderListener;
import com.cafe.api.CupCakeService;
import com.cafe.api.TeaOrderListener;
import com.cafe.api.TeaService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Activator implements BundleActivator {

	// Item Screens References
	private ServiceReference<CoffeeOrderListener> coffeeServiceReference;
	private ServiceReference<JuiceOrderListener> juiceServiceReference;
	private ServiceReference<CupCakeOrderListener> cupCakeServiceReference;
	private ServiceReference<SandwichOrderListener> sandwichServiceReference;
	private ServiceReference<TeaOrderListener> teaServiceReference;

	// Item Making Service References
	private ServiceReference<CoffeeService> coffeeBrewerRef;
	private ServiceReference<CupCakeService> cupCakeBakerRef;
	private ServiceReference<JuiceService> juiceMakerRef;
	private ServiceReference<SandwichService> sandWichMakerRef;
	private ServiceReference<TeaService> teaMakerRef;

	// Flavors and categories
	private static final List<String> AVAILABLE_FLAVORS = Arrays.asList("vanilla", "chocolate", "strawberry");
	private static final List<String> AVAILABLE_SANDWICHES = Arrays.asList("Veg", "Chicken", "Egg");
	private static final List<String> TEA_FLAVORS = Arrays.asList("black", "herbal", "fruit");

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("👨‍🍳 Waiter Started Working... 🚶🏻");

		// Register the Waiter as a service
		context.registerService(Activator.class.getName(), this, null);

		// Retrieve service references for order display screens
		coffeeServiceReference = context.getServiceReference(CoffeeOrderListener.class);
		juiceServiceReference = context.getServiceReference(JuiceOrderListener.class);
		cupCakeServiceReference = context.getServiceReference(CupCakeOrderListener.class);
		sandwichServiceReference = context.getServiceReference(SandwichOrderListener.class);
		teaServiceReference = context.getServiceReference(TeaOrderListener.class);

		// Retrieve service references for item-making services
		coffeeBrewerRef = context.getServiceReference(CoffeeService.class);
		cupCakeBakerRef = context.getServiceReference(CupCakeService.class);
		juiceMakerRef = context.getServiceReference(JuiceService.class);
		sandWichMakerRef = context.getServiceReference(SandwichService.class);
		teaMakerRef = context.getServiceReference(TeaService.class);

		// Get service instances from references
		CoffeeOrderListener coffeeListener = (coffeeServiceReference != null)
				? context.getService(coffeeServiceReference)
				: null;
		JuiceOrderListener juiceListener = (juiceServiceReference != null) ? context.getService(juiceServiceReference)
				: null;
		CupCakeOrderListener cupCakeListener = (cupCakeServiceReference != null)
				? context.getService(cupCakeServiceReference)
				: null;
		SandwichOrderListener sandwichListener = (sandwichServiceReference != null)
				? context.getService(sandwichServiceReference)
				: null;
		TeaOrderListener teaListener = (teaServiceReference != null) ? context.getService(teaServiceReference) : null;

		// Create objects from service references
		CoffeeService coffeeBrewer = (coffeeBrewerRef != null) ? context.getService(coffeeBrewerRef) : null;
		CupCakeService cupCakeBaker = (cupCakeBakerRef != null) ? context.getService(cupCakeBakerRef) : null;
		JuiceService juiceMaker = (juiceMakerRef != null) ? context.getService(juiceMakerRef) : null;
		SandwichService sandwichMaker = (sandWichMakerRef != null) ? context.getService(sandWichMakerRef) : null;
		TeaService teaMaker = (teaMakerRef != null) ? context.getService(teaMakerRef) : null;

		// Check if no services are available
		if (coffeeBrewer == null && cupCakeBaker == null && juiceMaker == null && sandwichMaker == null
				&& teaMaker == null) {
			System.err.println(
					" All services are unavailable services. Waiter cannot take any orders for any menu item.");
			return;
		}

		while (true) {
			// Taking customer order
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			System.out.print("👤 May I take your name ? (Type exit to exit) ");
			String customerName = scanner.nextLine();

			// Exit condition
			if (customerName.equals("exit")) {
				System.out.println("👋 Goodbye!");
				break; // Exits the loop
			}
			System.out.println("");

			// Ask for the order
			System.out.print(
					"🍽️ What would you like to order ? (Coffee, Juice, CupCake, Sandwich, Tea) or type 'exit' to quit: ");
			String orderChoice = scanner.nextLine().toLowerCase();

			// Exit condition
			if (orderChoice.equals("exit")) {
				System.out.println("👋 Goodbye!");
				break; // Exits the loop
			}

			// Process the order based on user selection
			switch (orderChoice) {

			// Coffee
			case "coffee":
				System.out.println(" WAITER :: Coffee Order Received for " + customerName);
				if (coffeeBrewer == null && coffeeListener == null) {
					System.err.println(" Coffee Brewer and Order Display are unavailable. Order cannot be processed.");
				} else if (coffeeListener == null) {
					System.err
							.println(" Coffe Order Display is unavailable. Coffee will be prepared but not displayed.");
					coffeeBrewer.makeCoffee(customerName);
					System.out.println(" WAITER :: Served the coffee to " + customerName + "  Enjoy!");
				} else {
					coffeeListener.onCoffeeOrder(customerName);
					if (coffeeBrewer != null) {
						System.out.println(" WAITER :: Served the coffee to " + customerName + " Enjoy!");
					}

				}
				break;

			// Juice
			case "juice":
				System.out.println(" WAITER :: Juice Order Received for " + customerName);
				if (juiceMaker == null && juiceListener == null) {
					System.err
							.println(" Juice maker and juice order screen are unavailable. Cannot prepare the juice.");
				} else if (juiceListener == null) {
					System.err
							.println(" Juice Order Display is unavailable. Juice will be prepared but not displayed.");
					juiceMaker.makeJuice(customerName);
					System.out.println(" WAITER :: Served the juice to " + customerName + " Enjoy!");
				} else {
					juiceListener.onJuiceOrder(customerName);
					if (juiceMaker != null) {
						System.out.println(" WAITER :: Served the juice to " + customerName + " Enjoy!");
					}

				}
				break;

			// Cup cake
			case "cupcake":
				System.out.println(" WAITER :: Cupcake Order Received for " + customerName);
				if (cupCakeBaker == null && cupCakeListener == null) {
					System.err.println(
							"❌ CupCake baker and cupcake order screen  are unavailable. Cannot prepare the order.");
				} else {
					String flavor = getValidChoice(scanner, "🧁 Choose a flavor (Vanilla, Chocolate, Strawberry): ",
							AVAILABLE_FLAVORS);

					if (cupCakeListener == null) {
						System.err.println(
								" Cup cake  Display is unavailable. Cup cake will be prepared but not displayed.");
						cupCakeBaker.makeCupCake(customerName, flavor);
						System.out.println(" WAITER :: Served " + flavor + " cup cake for " + customerName);

					} else {
						cupCakeListener.onCupCakeOrder(customerName, flavor);
						if (cupCakeBaker != null) {
							System.out.println(" WAITER :: Served " + flavor + " cup cake for " + customerName);
						}

					}
				}
				break;

			// Sandwich
			case "sandwich":

				if (sandwichMaker == null && sandwichListener == null) {
					System.err.println(
							"❌ Sandwich maker and sandwich order screen are unavailable. Cannot prepare the order.");
				} else {
					String sandwichType = getValidChoice(scanner, " Choose a sandwich type (Veg, Chicken, Egg): ",
							AVAILABLE_SANDWICHES);

					if (sandwichListener == null) {
						System.out.println(" WAITER :: Sandwich order Received for " + customerName);
						System.err.println(
								" Sandwich order screen is unavailable. Sandwich will be prepared but not displayed.");
						sandwichMaker.makeSandwich(customerName, sandwichType);
						System.out.println(" WAITER :: Served " + sandwichType + " sandwich for " + customerName);
					} else {
						System.out.println(" WAITER :: Sandwich order Received for " + customerName);
						sandwichListener.onSandwichOrder(customerName, sandwichType);
						if (sandwichMaker != null) {
							System.out.println(" WAITER :: Served " + sandwichType + " sandwich for " + customerName);
						}

					}
				}
				break;

			// Tea
			case "tea":
				if (teaMaker == null && teaListener == null) {
					System.err.println("❌ Tea maker and tea order screen are unavailable. Cannot prepare the order.");
				} else {
					String teaFlavor = getValidChoice(scanner, "🍵 Choose a tea flavor (Black, Herbal, Fruit): ",
							TEA_FLAVORS);
					if (teaListener == null) {
						System.out.println(" WAITER :: Tea order Received for " + customerName);
						System.err.println(" Tea order screen is unavailable. Tea will be prepared but not displayed.");
						teaMaker.makeTea(customerName, teaFlavor);
						System.out.println(" WAITER :: Served " + teaFlavor + " tea for " + customerName);
					} else {
						System.out.println(" WAITER :: Tea order Received for " + customerName);
						teaListener.onTeaOrder(customerName, teaFlavor);
						if (teaMaker != null) {
							System.out.println(" WAITER :: Served " + teaFlavor + " tea for " + customerName);
						}

					}

				}
				break;

			default:
				System.out.println("❌ Invalid choice. Please select Coffee, Juice, CupCake, Sandwich, or Tea.");
			}
		}

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Waiter stopeed working.");
		context.ungetService(context.getServiceReference(Activator.class.getName()));
	}

	// Helper method to validate user choice
	private String getValidChoice(Scanner scanner, String prompt, List<String> validOptions) {
		String choice;
		while (true) {
			System.out.print(prompt);
			choice = scanner.nextLine().trim().toLowerCase(); // Convert input to lower case

			for (String option : validOptions) {
				if (option.equalsIgnoreCase(choice)) {
					return option; // Return the correctly formatted option from the list
				}
			}

			System.out.println("❌ Invalid choice. Please select from " + validOptions);
		}
	}

}
