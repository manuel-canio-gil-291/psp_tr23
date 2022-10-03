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
            socketCliente = new Socket(HOST, CuadradoServidor.PORT);

            inputStream = new DataInputStream(socketCliente.getInputStream());
            System.out.println(inputStream.readUTF());
            numEntrada = sc.nextInt();
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeInt(numEntrada);
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
