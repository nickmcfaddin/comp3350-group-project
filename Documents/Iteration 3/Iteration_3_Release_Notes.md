# Iteration 3 Release Notes

## Overview
In the third iteration of the EasyShopper application, users can now share their lists with others in either a txt or pdf format.

## Features Review

- Share Lists
- Tracking Shopping History

We were only required to implement 1 feature and thus pushed the Tracking Shopping History feature to the future.

#### Share Lists
1. Save individual lists to photos: modified.
    We added functionality to allow individual lists to be saved as pdf or txt files so that the user would have an additional choice in how they want to send their data.
2. Save inventory to photo: removed.
    We decided that having one list be saved would be sufficient as the whole inventory would usually be out of scope of what a subuser would be expected to buy.

#### Tracking Shopping History (pushed to future)
1. Save lists for later
2. Restore previous lists
3. View past lists
 
## Testing Instructions

### Running The Application
There are four screens able to be selected from the bottom navigation bar, the list view, inventory view, request view and the search view, indicated left to right respectively. 

On the list view, users can add and remove shopping lists associated with affiliated stores, as well as add products to those lists using the "+" icon in the top right hand corner. By holding down an item within a list, users can also delete those items. Stores cannot have duplicates and items cannot be duplicated within a store. Users can also share lists with others using the icon second from the top right.

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
- Application layer: 70%
- Logic layer: 86%
- Domain specific objects: 93%
- Persistence layer (stubs + hsqldb): 68%
- Presentation layer: Not applicable for Line%, ran by androidTest

## Architecture Diagram
Found in the following [link](a)
