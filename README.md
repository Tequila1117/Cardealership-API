# CarDealership API 

This project is a RESTful API for managing a dealership's vehicles, sales contracts, and lease contracts. Built with Spring Boot, it provides various paths for interacting with the dealership's data.

## Functionality

**Vehicle:**

The API is able to manage vehicle inventory, including adding new vehicles, updating vehicle details, and deleting vehicles.
You can query vehicles based on different filters, such as make, model, price range, year, mileage, and color.
Vehicles can also be retrieved by dealership ID or VIN.

**Sales Contract:**

The API supports creating and retrieving sales contracts, which link vehicles to buyers and include contract details like the amount and date.

**Lease Contract:**

The API enables creating and retrieving lease contracts that associate vehicles with customer and track lease details like amount and duration.

# API Paths

**Vehicle:** CRUD operations for managing vehicle data.

**Sales Contract:** Create and retrieve sales contracts associated with vehicles and dealerships.

**Lease Contract:** Create and retrieve lease contracts for vehicles.

The API uses standard HTTP methods (GET, POST, PUT, DELETE) for interacting. All paths are grouped under the "/API" base path. Usage of Spring's dependency injection and the repository pattern to manage data with the help of DAO classes.

