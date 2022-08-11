package upb.isc.clientsocketui2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocket {
    private static ClientSocket instance;
    private static Socket socket;
    private static String username;
    private static String serverIP;
    private static int port;
    private ClientSocket() {

    }

    public void setUsername(String username) {
        ClientSocket.username = username;
    }
    public String getUsername() {
        return username;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        ClientSocket.serverIP = serverIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        ClientSocket.port = port;
    }

    public static ClientSocket getInstance() {
        if (instance == null) {
            instance = new ClientSocket();
        }
        return instance;
    }

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message.toString());
        try {
            DataOutputStream clientOut = new DataOutputStream(socket.getOutputStream());
            clientOut.writeUTF(new Message(username, message).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String connect(String serverIP, int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(serverIP, port));
            System.out.println("Connected to: " + socket.getInetAddress().getHostAddress() + ":" + port);
            return "Connected";
        } catch (Exception e) {
            try {
                System.out.println("Closing socket");
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return e.getMessage();
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String listenMessages(){
        final String[] messageToReturn = {""};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DataInputStream clientIn = new DataInputStream(socket.getInputStream());
                    while (true) {
                        try {
                            messageToReturn[0] = clientIn.readUTF();
                            System.out.println(socket.getInetAddress().getHostAddress() + " - " + ": " + messageToReturn[0]);
                        } catch (IOException e) {
                            break;
                        }
                    }
                    socket.close();
                    System.out.println("Client disconnected: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        thread.start();
        return messageToReturn[0];
    }
}
