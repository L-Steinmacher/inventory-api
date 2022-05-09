# Welcome to the inventory API
This app was built for use in the application process and I will guide you through the process of setting up this app and running the Crud commands in the H2 console.  

## First off
What is the H2 console you ask?  
 > H2 is an open-source lightweight Java database. It can be embedded in Java applications or run in the client-server mode. Mainly, H2 database can be configured to run as inmemory database, which means that data will not persist on the disk. Because of embedded database it is not used for production development, but mostly used for development and testing.
> 
>This database can be used in embedded mode or in server mode. Following are the main features of H2 database −
>
> - Extremely fast, open source, JDBC API
> - Available in embedded and server modes; in-memory databases
> - Browser-based Console application
> - Small footprint − Around 1.5MB jar file size
> source <https://www.tutorialspoint.com/h2_database/h2_database_introduction.htm>

## Setting up this project
Running the project is easy, just
- Go to the inventoryapi class and run the Main function.
This will start the application server on Apache Tomcat.

- In your browser enter the URL <http://localhost:2019/h2-console/>. This will bring you to a screen that should look like this.  

![look like this](H2-database%20PM.png)

- Click on the connect button and you should be good to go to monitor the in memory DB.

