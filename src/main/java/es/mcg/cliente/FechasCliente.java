/**
 * TAREA 3: Realizar un programa donde el cliente envíe dos fechas al servidor, y el servidor 
 * le indique cuál de ellas es la más antigua, y sea el cliente quien la imprima.
 */
package es.mcg.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import es.mcg.servidor.FechasServidor;

public class FechasCliente {
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        Socket socketCliente = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        Scanner sc = new Scanner(System.in);
        String fecha1E, fecha2E, fechaS;
        try {
            //El cliente conecta su IP de la PC al puerto del servidor
            socketCliente = new Socket(HOST, FechasServidor.PORT);

            //El cliente recibe los mensajes, siendo el cliente que debe enviar las dos fechas
            // al servidor 
            inputStream = new DataInputStream(socketCliente.getInputStream());
            System.out.println(inputStream.readUTF());
            fecha1E = sc.nextLine();
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeUTF(fecha1E);
            System.out.println(inputStream.readUTF());
            fecha2E = sc.nextLine();
            outputStream.writeUTF(fecha2E);
            //Una vez que el servidor adivine la fecha antigua, se lo pasa a la variable fechaS
            fechaS = inputStream.readUTF();
            //Se imprime la fecha antigua por la pantalla
            System.out.println("La fecha antigua es el: "+fechaS);
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
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
