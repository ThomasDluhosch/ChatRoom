import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
    Clients
    sends messages to Server
 */

class Client {

    private static final String host = "localhost";

    public void start(String host, int port) {

        try {
            Socket socket = new Socket(host, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.print("Create your nickname: ");

            ClientUI ui = new ClientUI();

            Thread inputThread = new Thread(() -> {
                try {
                    String input;

                    while ((input = reader.readLine()) != null) {
                        writer.println(input);
                        if (input.equalsIgnoreCase("exit")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread outputThread = new Thread(() -> {
                try {
                    String serverResponse;
                    while ((serverResponse = serverReader.readLine()) != null) {
                        System.out.println(serverResponse);
                        sendMessageToUI(serverResponse, ui);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            inputThread.start();
            outputThread.start();
            inputThread.join();
            outputThread.join();
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start(host, 1337);
    }

    public void recieveMessageFromUI(){
        // TODO: 08.12.2023  
    }
    
    public void sendMessageToUI(String message, ClientUI ui) {
        ui.printOutput(message);
    }

}