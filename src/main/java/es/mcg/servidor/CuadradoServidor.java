/**
 * TAREA 1: Realizar un programa donde el cliente envíe un número y el servidor calcule
 * su número al cuadrado (por ejemplo: 2 -> 2^2 = 4):
 * a. El servidor escribe por pantalla el resultado y finalizan la conexión cliente y servidor.
 * b. El servidor devuelve el resultado al cliente, y el cliente lo escribe por pantalla.
 */
package es.mcg.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CuadradoServidor {
    public static final int PORT = 8080;

    public static void main(String[] args) {
       ServerSocket socketServidor = null;
       Socket socketCliente = null;
       DataOutputStream outputStream = null;
       DataInputStream inputStream = null;
       int n, res; 
       try
       {
        //El servidor inicia con el puerto indicado
        socketServidor = new ServerSocket(PORT);
        socketCliente = socketServidor.accept();
        //El servidor envia el mensaje al cliente, siendo el cliente que debe enviar
        // el numero al servidor
        outputStream = new DataOutputStream(socketCliente.getOutputStream());
        outputStream.writeUTF("Introduzca un numero");
        inputStream = new DataInputStream(socketCliente.getInputStream());
        n = inputStream.readInt();
        //Se realiza la operacion
        res = (n*n);
        //El servidor envia el resultado al cliente
        outputStream.writeInt(res);

        inputStream.close();
        outputStream.close();
        socketCliente.close();
        socketServidor.close();
       }
       catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(outputStream != null)
            {
                try {
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if(socketCliente != null)
            {
                try {
                    socketCliente.close();
                } catch (IOException ioException) { 
                    ioException.printStackTrace();
                }
            }
            if(socketServidor != null)
            {
                try {
                    socketServidor.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
