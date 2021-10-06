package template.java8;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Java8Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input user name: ");
        String userName = reader.readLine();
        System.out.print("Input password: ");
        String password = reader.readLine();
        Network network = new Network(userName, password);

        // Enter the message.
        System.out.print("Input message: ");
        String message = reader.readLine();

        System.out.println("\nChoose social network for posting message.\n" +
                           "1 - Facebook\n" +
                           "2 - Twitter");
        int choice = Integer.parseInt(reader.readLine());
        Facebook facebook = new Facebook();
        Twitter twitter = new Twitter();
        if (choice == 1) {
            network.post(message, facebook::logIn, facebook::sendData, facebook::logOut);
        } else if (choice == 2) {
            network.post(message, twitter::logIn, twitter::sendData, twitter::logOut);
        }

    }
}
