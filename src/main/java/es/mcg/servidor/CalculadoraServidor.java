/**
 * TAREA 2: Realizar un programa donde el cliente envíe dos números y un símbolo '+', '-', '*', '/'. 
 * El servidor debe realizar la operación adecuada con ambos números, devolviendo el resultado, 
 * y siendo el cliente quien pinte por pantalla el resultado.
 */
package es.mcg.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServidor {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        int n1, n2, res;
        char op;
        try {
            //El servidor inicia con el puerto indicado
            socketServidor = new ServerSocket(PORT);
            socketCliente = socketServidor.accept();
            //El servidor envia los mensajes al cliente, siendo el cliente que debe enviar
            // los numeros y la operacion al servidor
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeUTF("Introduce el primer numero");
            inputStream = new DataInputStream(socketCliente.getInputStream());
            n1 = inputStream.readInt();
            outputStream.writeUTF("Introduce el segundo numero");
            n2 = inputStream.readInt();
            outputStream.writeUTF("Seleccione una opcion\n\t+ -> Sumar\n\t- -> Restar\n\t* -> Multiplicar\n\t/ -> Dividir");
            op = inputStream.readChar();
            //Hacemos un switch para comparar el simbolo de la operacion
            switch(op)
            //El servidor envia el resultado al cliente
            {
                case '+': {
                    res = (n1+n2);
                    outputStream.writeInt(res);
                }break;
                case '-': {
                    res = (n1-n2);
                    outputStream.writeInt(res);
                }break;
                case '*': {
                    res = (n1*n2);
                    outputStream.writeInt(res);
                }break;
                case '/': {
                    res = (n1/n2);
                    outputStream.writeInt(res);
                }break;
            }
            inputStream.close();
            outputStream.close();
            socketCliente.close();
            socketServidor.close();
        } catch (IOException ioException) {
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
