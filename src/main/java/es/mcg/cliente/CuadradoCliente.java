/**
 * TAREA 1: Realizar un programa donde el cliente envíe un número y el servidor calcule
 * su número al cuadrado (por ejemplo: 2 -> 2^2 = 4):
 * a. El servidor escribe por pantalla el resultado y finalizan la conexión cliente y servidor.
 * b. El servidor devuelve el resultado al cliente, y el cliente lo escribe por pantalla.
 */
package es.mcg.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import es.mcg.servidor.CuadradoServidor;

public class CuadradoCliente {
    private static final String HOST = "localhost";
    public static void main(String[] args) {
        Socket socketCliente = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        Scanner sc = new Scanner(System.in);
        int numEntrada;
        try{
            //El cliente conecta su IP de la PC al puerto del servidor
            socketCliente = new Socket(HOST, CuadradoServidor.PORT);

            //El cliente recibe el mensaje, siendo el cliente que debe enviar el numero
            // al servidor 
            inputStream = new DataInputStream(socketCliente.getInputStream());
            System.out.println(inputStream.readUTF());
            numEntrada = sc.nextInt();
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeInt(numEntrada);
            //Imprime el resultado por la pantalla
            System.out.println("Resultado: "+inputStream.readInt());
        }catch(UnknownHostException unknownHostException)
        {
            unknownHostException.printStackTrace();
        }
        catch(IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
                sc.close();
            }
            catch(IOException ioException2)
            {
                ioException2.printStackTrace();
            }
        }
    }
}
