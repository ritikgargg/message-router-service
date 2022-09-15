# Message Router Service


### 1. What does this program do?
This submission is aimed at designing and developing a message router service that acts as an intermediary to pass the request of a sender to a receiver, as the clients cannot communicate directly. For this purpose, it handles POST requests sent by a sender, uses routing tables to forward the request to the designated client location. Meanwhile, it also takes care of logging important details and sending back the acknowledgement of the request back to the sender.

### 2. How does this program work?

The implementation is done following the SOLID principles. For different functionalities, different classes have been made ensuring the single responsibility principle. In order to make the design extensible so as to allow adding services that communicate via protocols other than HTTP, I have followed the principle of Programming to the interface. In my implementation, the interface is RequestForwarder, which can have different implementations as desired, yet preserve the general structure. The redundancy is avoided at all places of opportunity.

The primary function responsible for setting up the message router service is the `routeServiceHandler()`.

`requestParser()` function is responsible for parsing and extracting important information from the payload.

`destinationFinder()` function is responsible for searching the routing_table in order to find out the desired destination.

`requestForwarder()` function is responsible for forwarding the request to the destination using an HTTP post request, sent by the message router service.


### 3. How to compile and run this program?

The root of the project contains a gradlew script (`gradlew.bat` for Windows).

To run App.java, execute

`./gradlew run`

App.Java uses a main() function to run the function of MessageRouterService.
Important points while running the program:
Change the path of the config.json file passed to MessageRouterService from the main() function of App.java
    (i) The sender program sends a requests to the "/" end-point.
    (ii) The acknowledgement is passed in terms of status code of the POST request made by MessageRouterService to the receiver.
    (iii) The names of the tables are stored in the variables routingTableName and messageLogsTableName of the DatabaseHandler class. Make sure to make appropriate changes as per the names of these tables in your database.
    (iv) Change the config.json file as per your parameters.
    (v) The sqlite commands used to create the tables in this implementation can be found in the file databaseCommands.sql
    (vi) The payload is passed as text(or XML) along with the POST request.


To run the tests, you have to make certain changes
    (i) Change the directory variable in JSONFileParserTest.java by the location of the project folder in your system.
    (ii) Change the dbURL in DatabaseHandlerTest.java, with the URL of your database.
    (iii) For the success of the function destinationFinder(), make sure the corresponding entry is present in the routing_table of your database.

Finally, execute the following command:
`./gradlew test`