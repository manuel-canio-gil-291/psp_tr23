/**
 * TAREA 3: Realizar un programa donde el cliente envíe dos fechas al servidor, y el servidor 
 * le indique cuál de ellas es la más antigua, y sea el cliente quien la imprima.
 */
package es.mcg.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechasServidor {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        String fecha1S, fecha2S;
        SimpleDateFormat formato = null;
        Date fecha1 = null;
        Date fecha2 = null;
        try {
            //El servidor inicia con el puerto indicado
            socketServidor = new ServerSocket(PORT);
            socketCliente = socketServidor.accept();
            //Se establece el formato de la fecha
            formato = new SimpleDateFormat("dd-MM-yyyy");
            //El servidor envia los mensajes al cliente, siendo el cliente que debe enviar
            // las dos fechas al servidor
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeUTF("Dame la primera fecha");
            inputStream = new DataInputStream(socketCliente.getInputStream());
            fecha1S = inputStream.readUTF();
            outputStream.writeUTF("Dame la segunda fecha");
            fecha2S = inputStream.readUTF();
            //De formato String se pasa al formato Date para hacer la comparacion
            fecha1 = formato.parse(fecha1S);
            fecha2 = formato.parse(fecha2S);
            //Si fecha1 es antiguo que fecha2, el servidor envia la fecha1 como antigua al cliente
            //En caso contrario, sera la fecha2 que debe enviar al cliente
            if(fecha1.before(fecha2))
            {
                outputStream.writeUTF(fecha1S);
            }
            else
            {
                outputStream.writeUTF(fecha2S);
            }
            inputStream.close();
            outputStream.close();
            socketCliente.close();
            socketServidor.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
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
