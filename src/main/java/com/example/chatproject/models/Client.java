package com.example.chatproject.models;

import com.example.chatproject.models.objects.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static BufferedReader in;
    private static PrintWriter out;
    private Socket socket;

    public Client(String serverAddress, int serverPort) throws IOException {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conexión establecida con el servidor.");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertarUsuario(User nuevo_usuario){
        try {
            Client client = new Client("127.0.0.1", 49899);

            Gson gson = new Gson();
            String json = gson.toJson(nuevo_usuario);

            // Envia el JSON al servidor
            client.sendCommand("Insertar", json);

            // Recibe la respuesta del servidor
            String respuestaServidor = client.receiveMessage();
            System.out.println("Respuesta del servidor: " + respuestaServidor);

            // Cierra la conexión con el servidor
            client.closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendCommand(String command, String message) {
        out.println(command);
        out.println(message);
    }

    public static String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Conexiones cerradas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
