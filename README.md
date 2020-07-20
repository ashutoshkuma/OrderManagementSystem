# OrderManagementSystem
Order Management System

The Project contains two microservices Order Service and Order Item Service. The Order Service is responsible for creating new orders, retrieving existing order info. 
Similarly, Order Item Service is responsible for creating order items, retrieving order items.
Order Item has below data:
-Product code
-Product name
-Quantity
Order has below data:
-Customer name
-Order date
-Shipping address
-Order items
-Total ($)

FeignClient is used for inter service communucation.
h2 DB is Used.
Exception are handled globally.
REST APIs are used.
Input Data in Validated.

