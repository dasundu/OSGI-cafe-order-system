# OSGi-Based Café Order System

A modular café ordering system built with OSGi framework demonstrating producer-consumer architecture for handling various café item orders.

## Overview

This project simulates a complete café system where customers can order Coffee, Cupcakes, Juice, Sandwiches, and Tea. The system processes these orders using a modular OSGi bundle architecture, allowing for loose coupling between components and easy extensibility.

![OSGi Café System](https://via.placeholder.com/800x400?text=OSGi+Caf%C3%A9+System)

## Features

- **Modular Design**: Independent bundles for each café item and service
- **Loose Coupling**: Producers and consumers interact through shared interfaces
- **Dynamic Service Registration**: Services registered and retrieved at runtime
- **Extensible Architecture**: Easy to add new products or services
- **Real-time Order Processing**: Simulates actual café workflow

## Project Structure

### Common API Bundle
- **com.cafe.api**: Shared interfaces for all café items
  - Services: `CoffeeService`, `CupCakeService`, `JuiceService`, `SandwichService`, `TeaService`
  - Listeners: `CoffeeOrderListener`, `CupCakeOrderListener`, `JuiceOrderListener`, `SandwichOrderListener`, `TeaOrderListener`

### Producer Bundles
- **com.cafe.coffeemaker**: Implements coffee brewing functionality
- **com.cafe.cupcakemaker**: Implements cupcake baking functionality
- **com.cafe.juicemaker**: Implements juice making functionality
- **com.cafe.sandwichmaker**: Implements sandwich preparation functionality
- **com.cafe.teamaker**: Implements tea brewing functionality

### Consumer Bundles
- **com.cafe.coffeeorderscreen**: Handles coffee orders
- **com.cafe.cupcakeorderscreen**: Handles cupcake orders
- **com.cafe.juiceorderscreen**: Handles juice orders
- **com.cafe.sandwichorderscreen**: Handles sandwich orders
- **com.cafe.teaorderscreen**: Handles tea orders

### Coordination Bundle
- **com.cafe.waiter**: Main interface for receiving customer orders and coordinating between producers and consumers

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Apache Felix OSGi framework
- Maven (optional, for building)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/osgi-cafe-system.git
   cd osgi-cafe-system
   ```

2. Build the bundles:
   ```bash
   mvn clean install
   ```

3. Set up Apache Felix:
   - Download Apache Felix from [the official website](https://felix.apache.org/downloads.cgi)
   - Copy the generated JAR files to Felix's bundle directory

### Running the System

1. Start Apache Felix:
   ```bash
   java -jar bin/felix.jar
   ```

2. Install bundles using the Felix console:
   ```bash
   install file:/path/to/com.cafe.api.jar
   install file:/path/to/com.cafe.coffeemaker.jar
   # Install all other bundles...
   ```

3. Start the bundles:
   ```bash
   start <bundle-id>
   ```

4. Interact with the system through the Waiter bundle interface

## Sample Output

```
✅ Coffee Service Registered successfully!
✅ Sandwich Service Registered successfully!
✅ Waiter Started Working... 🚶🏻

👤 May I take your name ? (Type exit to exit) John
🍽️ What would you like to order ? (Coffee, CupCake, Juice, Sandwich, Tea) or type 'exit' to quit: Sandwich
Choose a sandwich type (Veg, Chicken, Egg): Chicken
 Sandwich order screen :: Order received for John. chicken sandwich.
 Sandwich order screen :: Sent signal to sandwich maker.
 Sandwich maker :: Made chicken sandwich for John.
```

## Architecture Diagram

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│                 │     │                 │     │                 │
│  Coffee Maker   │◄────┤  Waiter Bundle  ├────►│ Coffee Order    │
│  Bundle         │     │  (Coordinator)  │     │ Screen Bundle   │
│                 │     │                 │     │                 │
└─────────────────┘     │                 │     └─────────────────┘
                        │                 │
┌─────────────────┐     │                 │     ┌─────────────────┐
│                 │     │                 │     │                 │
│  Sandwich Maker │◄────┤                 ├────►│ Sandwich Order  │
│  Bundle         │     │                 │     │ Screen Bundle   │
│                 │     │                 │     │                 │
└─────────────────┘     └─────────────────┘     └─────────────────┘
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to everyone who has contributed to this project
- OSGi Alliance for providing the framework
- Felix team for their excellent OSGi implementation
