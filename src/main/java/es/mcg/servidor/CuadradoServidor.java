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
        socketServidor = new ServerSocket(PORT);
        socketCliente = socketServidor.accept();
        outputStream = new DataOutputStream(socketCliente.getOutputStream());
        outputStream.writeUTF("Introduzca un numero");
        inputStream = new DataInputStream(socketCliente.getInputStream());
        n = inputStream.readInt();
        res = (n*n);
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
