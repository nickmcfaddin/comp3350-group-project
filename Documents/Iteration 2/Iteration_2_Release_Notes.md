# Iteration 2 Release Notes

## Overview
In the second iteration (first update) of the EasyShopper application, it has grown to be the ultimate tool belt for grocery shoppers. Now, shoppers can track their home products, set desired stock for products and have sub-users add requests that can be reviewed by the device owner and added to the main list view. 

## Features Review

- Product Expiry Dates
- Manage Home Storage
- User Profiles

We were able to implement every feature we intended to, however there were some modifications to the users stories. User story summaries can be found below, grouped by feature.

#### Product Expiry Dates
1. Track expiry dates for a product: completed.
2. Remove product from storage with notification if expired: removed.\
    We ultimately decided to cut this user story due to its difficulty to test as well as for the marker's to see as the application would have to run to a given day for the product to expire and for it to be removed from the list.

#### Manage Home Storage
1. Manage quantities: modified\
    We separated the list and inventory views and decided that there would be no connection between the two. For the time being, we added quantities to the inventory view and are going to revisit adding quantities to the list view and how that may integrate with our inventory.
2. Track home products: completed.
3. Add product to list if below desired quantity: removed.
    We removed this feature as we decided that our list view and inventory view would be independent for the time being which makes this story impossible to implement.
4. Sort inventory by priority: added.\
    We added a function to sort products in the inventory by priority (how much you need of the product vs how much you have).

#### User Profiles
1. Have multiple profiles for the people you shop for: completed.
2. Specify preferences for people to personalize your shopping experience: completed.
 
## Testing Instructions

### Running The Application
There are four screens able to be selected from the bottom navigation bar, the list view, inventory view, request view and the search view, indicated left to right respectively. 

On the list view, users can add and remove shopping lists associated with affiliated stores, as well as add products to those lists using the "+" icon in the top right hand corner. By holding down an item within a list, users can also delete those items. Stores cannot have duplicates and items cannot be duplicated within a store.

On the inventory view, users can add what stock they already have in their home so that while they are out, they can see what products they may need. They may update the stock and desired quantity and once the stock is updated, expiry dates are automatically filled and they can see all their expiry dates for any given product.

On the request view, secondary users such as children and non-primary users of the device can create requests for products they want. Once these requests are made, the owner of the device can accept or deny individual products of the request and they will automatically be added to the list view.

On the search view, users can get more information about any specific product. This includes the nutritional facts as well as the prices at competing stores. They may either scroll to find the product or use our search tool by typing in the search bar to find the product they are looking for.

## Running Tests
### Unit Tests
1. To run all the tests that cover the stubs and Logic layers (as well as the DSO's), run the AllUnitTests.java file under the com.example.easyshopper (test) package. You can also use the link in the Tests section of the README.md to find the file. You can run the tests with or without coverage and they should contribute to the values given in the "Code Coverage Statistics" section of the release document.
    - If you want to break down the Unit Tests for these layers individually, they are found within the "business" and "objects" packages within the com.example.easyshopper (test) package and all end in "Test".

### Integration Tests
1. To run all the tests that cover the real database and Logic layers (as well as the DSO's), run the AllIntegrationTests.java file under the com.example.easyshopper (test) package. You can also use the link in the Tests section of the README.md to find the file. You can run the tests with or without coverage and they should contribute to the values given in the "Code Coverage Statistics" section of the release document.
    - If you want to break down the Integration Tests for these layers individually, they are found within the "business" and "objects" packages within the com.example.easyshopper (test) package and all end in "IT".

2. To run the tests for the Presentation layer, navigate to the com.example.easyshopper (androidTest) package and click on the AllAcceptanceTests.java file. This file will run all of the tests for the Presentation layer. If you want to break them down individually, they are all found within the com.example.easyshopper (androidTest) package.

## Code Coverage Statistics
- Application layer: 82%
- Logic layer: 96%
- Domain specific objects: 93%
- Persistence layer (stubs + hsqldb): 68%
- Presentation layer: Not applicable for Line%, ran by androidTest

## Architecture Diagram
Found in the following [link](https://code.cs.umanitoba.ca/comp3350-winter2024/lethalcompany-a01-13/-/blob/main/Documents/Iteration%202/Iteration_2_Architecture_Diagram.png)
