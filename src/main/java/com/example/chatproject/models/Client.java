package com.example.chatproject.models;

import com.example.chatproject.models.objects.Message;
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

        Gson gson = new Gson();
        String json = gson.toJson(nuevo_usuario);
        Client client = getClient();

        // Envia el JSON al servidor
        sendCommand("Insertar usuario", json);

        // Cierra la conexión con el servidor
        assert client != null;
        client.closeConnection();
    }

    public static void insertarMensaje(Message mensaje){
        Client client = getClient();

        Gson gson = new Gson();
        String json = gson.toJson(mensaje);

        // Envia el JSON al servidor
        sendCommand("Insertar mensaje", json);

        // Cierra la conexión con el servidor
        assert client != null;
        client.closeConnection();
    }

    public static String encontrarUsuario(String nombre, String contraseña){
        try {
            Client client = getClient();

            Gson gson = new Gson();
            String json = gson.toJson(nombre +" "+ contraseña);

            // Envia el JSON al servidor
            sendCommand("Select usuario" ,json);

            // Recibe la respuesta del servidor
            String respuestaServidor = receiveMessage();

            // Cierra la conexión con el servidor
            assert client != null;
            client.closeConnection();
            if (!respuestaServidor.equals("No se encontró ningún usuario con el nombre y contraseña proporcionados.")){
                return respuestaServidor;
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encontrarUsuarios(){
        try {
            Client client = getClient();

            // Envia el JSON al servidor
            sendCommand("Select usuarios" ,"");

            // Recibe la respuesta del servidor
            String respuestaServidor = receiveMessage();

            // Cierra la conexión con el servidor
            assert client != null;
            client.closeConnection();

            return respuestaServidor;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No se han encontrado usuarios registrados";
    }


    public static String recibirMensajes(String id_remitente, String id_destinatario){
        try {
            Client client = getClient();

            Gson gson = new Gson();
            String json = gson.toJson(id_remitente +" "+id_destinatario);

            // Envia el JSON al servidor
            sendCommand("Select mensaje" ,json);

            // Recibe la respuesta del servidor
            String respuestaServidor = receiveMessage();

            // Cierra la conexión con el servidor
            assert client != null;
            client.closeConnection();
            if (!respuestaServidor.equals("Error, no se encontraron conversaciones registradas.")){
                return respuestaServidor;
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void sendCommand(String command, String message) {
        out.println(command);
        out.println(message);
    }

    public static String receiveMessage() throws IOException {
        return in.readLine();
    }

    public static Client getClient(){
        try {
            Client client = new Client("192.168.0.177", 49898);
            return client;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
