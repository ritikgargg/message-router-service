/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package midsem;

import java.io.IOException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        try {
            MessageRouterService mrs = new MessageRouterService("src\\main\\resources\\config.json");
            mrs.routeServiceHandler();

            Client client1 = new Client(44001);
            client1.main();

            Client client2 = new Client(44002);
            client2.main();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}