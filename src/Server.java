import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
    Server
    stores active clients
    boradcast incoming messages to all active clients
 */

class Server {

    private List<ClientHandler> activeClients = new ArrayList<>();

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                activeClients.add(clientHandler);

                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void broadcastMessage(String nickname, String msg, ClientHandler sender) {
        for (ClientHandler client : activeClients) {
            if (client != sender) {
                client.sendMessage(nickname + ": " + msg);
            }
        }
    }

    public void removeClient(ClientHandler client) {
        activeClients.remove(client);
    }

    public static void main(String[] args) {
        new Server().start(1337);
    }

}