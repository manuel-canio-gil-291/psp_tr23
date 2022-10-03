package es.mcg.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import es.mcg.servidor.CalculadoraServidor;

public class CalculadoraCliente {
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        Socket socketCliente = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        Scanner sc = new Scanner(System.in);
        int num1Entrada, num2Entrada;
        char opEntrada;
        try {
            socketCliente = new Socket(HOST, CalculadoraServidor.PORT);
            
            inputStream = new DataInputStream(socketCliente.getInputStream());
            System.out.println(inputStream.readUTF());
            num1Entrada = sc.nextInt();
            outputStream = new DataOutputStream(socketCliente.getOutputStream());
            outputStream.writeInt(num1Entrada);
            System.out.println(inputStream.readUTF());
            num2Entrada = sc.nextInt();
            outputStream.writeInt(num2Entrada);
            System.out.println(inputStream.readUTF());
            opEntrada = sc.next().charAt(0);
            outputStream.writeChar(opEntrada);
            System.out.println(num1Entrada+" "+opEntrada+" "+num2Entrada+" = "+inputStream.readInt());
        } catch (UnknownHostException hException) {
            hException.printStackTrace();
        } catch(IOException ioException) {
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
