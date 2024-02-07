# Iteration 1 Release Notes

## Overview
In EasyShopper's debut to market, it presents itself as a basic grocery list application that allows users to add and remove lists coordinating with our affiliating stores. With lists in place, they can add and remove products within the system and see their nutritional facts, and prices at competing stores. 

## Features Review

- Manage Lists
- Prices
- Search Function

We were able to implement every feature we intended to, however there were some modifications to the users stories. User story summaries can be found below, grouped by feature.

#### Manage Lists
1. Adding a list: completed.
2. Removing a list: completed.
3. Adding an item: completed.
4. Deleting an item: completed.

#### Prices
1. Item price: completed.
2. List totals: completed.
3. Price comparison: completed.
4. Change item price: removed.\
    We made the choice to remove the "Change item price" feature as we switched our database structure to hold individual prices for each store a product is held in. Initially we thought that we would have one price for each product and the user would be able to alter these prices and items, however we decided that we wanted to have a preset catalog of stores and items they could choose from so that all of our lists and items are all preloaded, allowing us to give the user a better experience for each list and product they select as we cater to each specifically.

#### Search Function
1. Search for an item: completed.
2. Nutritional facts: completed.
3. Search for a list: removed.\
    Much like the previous change we made to the change item price, this removal stems from the lack of a need to search for a list as we have decided to have a maximum of 10 preloaded lists. Since we have such a small amount of lists that are able to be created, it would render the "Search for a list" function practically useless.
 
## Testing Instructions
### Running Unit Tests
1. To run all the tests that cover the Persistence and Logic layers (as well as the DSO's), run the AllUnitTests.java file under the com.example.easyshopper (test) package. You can also use the link in the Testing section of the README.md to find the file. You can run the tests with or without coverage and they should produce the values given in the "Code Coverage Statistics" section of the release document.
    - If you want to break down the Unit Tests for these layers individually, they are found within the "business" and "objects" packages within the com.example.easyshopper (test) package.

2. To run the tests for the Presentation layer, navigate to the com.example.easyshopper (androidTest) package and click on the AllAcceptanceTests.java file. This file will run all of the tests for the Presentation layer. If you want to break them down individually, they are all found within the com.example.easyshopper (androidTest) package.

## Code Coverage Statistics
- Application layer: 100%
- Logic layer: 92%
- Domain specific objects: 94%
- Persistence layer (stubs): 87%
- Presentation layer: Not applicable for Line%, ran by androidTest
