package com.example.chatproject.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

public class Client {
    private BufferedReader in;

    private PrintWriter out;

    Socket socket = new Socket("127.0.0.1", 49999);

    public Client() throws IOException {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conexión establecida con el servidor.");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

    }

    public void closeServer(){
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Conexiones cerradas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 /*
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 49999);
            System.out.println("Conexión establecida con el servidor.");

            // Establece flujos de entrada y salida para texto
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Crea un objeto y conviértelo a JSON
            MiObjeto objeto = new MiObjeto("Ejemplo", 42);
            Gson gson = new Gson();
            String json = gson.toJson(objeto);

            // Envia el JSON al servidor
            out.println(json);

            // Recibe la respuesta del servidor
            String respuestaServidor = in.readLine();
            System.out.println("Respuesta del servidor: " + respuestaServidor);

            // Cierra las conexiones con el servidor
            in.close();
            out.close();
            socket.close();
            System.out.println("Conexiones cerradas");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  */
}
Client client = new Client();
