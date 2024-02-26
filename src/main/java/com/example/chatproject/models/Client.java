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

    /**
     * Constructor de la clase Client
     * Primero se establece la conexión con el servidor,
     * se inicializa PrintWriter para enviar datos al servidor e
     * inicializa BufferedReader para leer datos del servidor     *
     * @param serverAddress
     * @param serverPort
     * @throws IOException
     */
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

    /**
     * Método insertarUsuario
     * se usa para insertar un nuevo usuario en el servidor
     * @param nuevo_usuario
     */
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

    /**
     * Método insertarMensaje
     * se usa para insertar un nuevo mensaje en el servidor
     * @param mensaje
     */
    public static void insertarMensaje(Message mensaje){
        Client client = getClient();

        Gson gson = new Gson();
        String json = gson.toJson(mensaje);

        sendCommand("Insertar mensaje", json);

        assert client != null;
        client.closeConnection();
    }

    /**
     * Método encontrarUsuario
     * se utiliza para encontrar un usuario en el servidor
     * @param nombre
     * @param contraseña
     * @return
     */
    public static String encontrarUsuario(String nombre, String contraseña){
        try {
            Client client = getClient();

            Gson gson = new Gson();
            String json = gson.toJson(nombre +" "+ contraseña);

            sendCommand("Select usuario" ,json);

            String respuestaServidor = receiveMessage();

            assert client != null;
            client.closeConnection();
            // Retorna la respuesta si se encontró el usuario, de lo contrario devuelve null
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

    /**
     * Método encontrarUsuarios
     * se utiliza para encontrar usuarios en el servidor
     * @param idUsuario
     * @return
     */
    public static String encontrarUsuarios(String idUsuario){
        try {
            Client client = getClient();
            Gson gson = new Gson();
            String json = gson.toJson(idUsuario);

            sendCommand("Select usuarios" ,json);

            String respuestaServidor = receiveMessage();

            assert client != null;
            client.closeConnection();

            return respuestaServidor;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No se han encontrado usuarios registrados";
    }

    /**
     * Método recibirMensajes
     * sirve para recibir mensajes del servidor
     * @param id_remitente
     * @param id_destinatario
     * @return
     */
    public static String recibirMensajes(String id_remitente, String id_destinatario){
        try {
            Client client = getClient();

            Gson gson = new Gson();
            String json = gson.toJson(id_remitente +" "+id_destinatario);

            sendCommand("Select mensaje" ,json);

            String respuestaServidor = receiveMessage();

            assert client != null;
            client.closeConnection();
            // Retorna la respuesta del servidor si hay mensajes, de lo contrario, null
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

    /**
     * Método sendCommand
     * se usa para enviar un comando y un mensaje al servido
     * @param command
     * @param message
     */
    public static void sendCommand(String command, String message) {
        out.println(command); // Enviar comando al servidor
        out.println(message); // Enviar mensaje al servidor
    }

    /**
     * Método receiveMessage
     * se usa para recibir un mensaje del servidor
     * @return
     * @throws IOException
     */
    public static String receiveMessage() throws IOException {
        return in.readLine();
    }

    /**
     * Método getClient
     * se usa para obtener una instancia del cliente
     * @return
     */
    public static Client getClient(){
        try {
            // Crear una instancia del cliente con la dirección IP y el puerto del servidor
            Client client = new Client("10.11.1.203", 49898);
            return client;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método para cerrar la conexión con el servidor
     */
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
