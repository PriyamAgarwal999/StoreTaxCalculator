# TAX CALCULATOR

Calculate and apply basic sales tax for the products sold in a departmental store.


## Description

```
A department store needs to calculate and apply basic sales tax for the products sold in its stores.
Sales tax is applied as standard 10% for all items, except Books, Food and Medical products.

An additional 5% import duty is levied on all imported products, with no exceptions.

Write a program to prepare and print the receipt for every purchase. 
The receipt should list the name of all the items and their price (including tax),
finishing with the total cost of the items,and the total amounts of sales taxes paid. 
The rounding rulesfor sales tax are that for a tax rate of n%, 
a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax. 
```

# Getting Started

## Prerequisites

- **JAVA**
- **Springboot**
- **SQL**

___
## Dependencies

* JDK 15.0.3
* MySQL 8.0.27
* Spring Web
* Spring Data JPA

___
## Running the project

* Change the sql username and password in `src/main/resources/application.properties` according to your mysql installation

* Run the `StoreTaxCalulationApplication`.java class as spring boot application

___
## Endpoints

### GET

This GET endpoint displays all the products available in the store in JSON format.

```
 /products/ 
```

### POST

This POST endpoint adds new product to the store. 

``` 
/addproduct/
```

Give the input in JSON format that includes ProductId,productName,productQuantity and productPrice.

```
{
    "productId":"7",
    "productName":"Book",
    "productQuantity": "1",
    "productPrice":"400"
}
```

### PUT

This PUT endpoint updates the product details of a particular product in the store.

```
/updateproduct/
```
Give the input in JSON format that includes ProductId,productName,productQuantity and productPrice.

```
{
    "productId":"7",
    "productName":"Book",
    "productQuantity": "1",
    "productPrice":"400"
}
```
### DELETE 

This DELETE endpoint  deletes the record of a particular product.

```
/deleteproduct/{id}/
```

### GET

This GET endpoint displays the receiptId, productName, totalPrice, totalTax in JSON format.

```
/store/id/{id}/
```

## GET

This GET endpoint displays the details of the particular product

```
/product/id/{id}/
```


## STRUCTURAL LAYOUT
- StoreTaxCalulationApplication is the main class of the spring boot project.
- StoreTaxController class inside the controller package is responsible for handling all the endpoints.
- ProductService interface inside the service package defines some methods that we will be using and ProductServiceImpl class implements the ProductService interface and holds the business logic of the project.
- ReceiptService interface inside the service package defines some methods that we will be using and ReceiptServiceImpl class implements the ReceiptService interface and holds the business logic of the project.
- ProductRepo Interface in the dao package extends JpaRepository that helps us in carrying out database operations.
- ReceiptRepo Interface in the dao package extends JpaRepository that helps us in carrying out database operations.
- ProductDetails class inside the details package is the entity class that creates tables and columns according to our needs.
- ReceiptDetails class inside the details package is the entity class that creates tables and columns according to our needs.


## AUTHOR
**priyam**

## COPYRIGHTS

*All rights reserved by `Priyam Agarwal`*