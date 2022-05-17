# Welcome to the inventory API
This app was built for use in the application process and I will guide you through the process of setting up this app and running the Crud commands.  

## Endpoints
| Crud operation     | Description |  Endpoint   | Request Body(if applicible) |
| :---        |    :----:   |          ---: |
| Get All Products      | Gets all products from table      |  /products/products | null |
| Get Product By Id   | Gets a product by it's unique id       |  /products/product/1    |  null |

## What is the H2 database you ask?  

 > H2 is an open-source lightweight Java database. It can be embedded in Java applications or run in the client-server mode. Mainly, H2 database can be configured to run as inmemory database, which means that data will not persist on the disk. Because of embedded database it is not used for production development, but mostly used for development and testing.
> 
>This database can be used in embedded mode or in server mode. Following are the main features of H2 database −
>
> - Extremely fast, open source, JDBC API
> - Available in embedded and server modes; in-memory databases
> - Browser-based Console application
> - Small footprint − Around 1.5MB jar file size
> source <https://www.tutorialspoint.com/h2_database/h2_database_introduction.htm>

## Replit 
This app is deployed on replit to test and spin up the server [here](https://replit.com/@Steinml82/inventory-api#src/main/resources/application.properties).
Just hit the run button and wait a few minuits for the api to boot up.  Once it has finished you should see ![image of replit once deployed]() 

A thing to note is the Http status 404. There is no HTML 

The api is now running at [https://inventory-api.steinml82.repl.co/](https://inventory-api.steinml82.repl.co/) and is ready to test with postman or any other api testing tool that use!

#### OR

## Setting up this project on Localhost
Running the project is easy, just:

- Go to the inventoryapi class and run the Main function.
This will start the application server on Apache Tomcat.

- Alternitivly, navigate to the root of the folder and in your command line and  run: 
 ```
 mvn spring-boot
 ```

- In your browser enter the URL <http://localhost:2019/h2-console/>. This will bring you to a screen that should look like this.  

![look like this](H2-database%20PM.png)

- Click on the connect button and you should be good to go to monitor the in memory DB.

