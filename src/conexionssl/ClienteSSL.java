package conexionssl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClienteSSL {
    public static void main (String[] args) throws ClassNotFoundException{
        System.setProperty("java.net.ssl.trustore", "./resources/Servidor.jks" );
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;
        SSLSocket sslsocket = null;
        
        try{
            SSLSocketFactory fact = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sslsocket = (SSLSocket) fact.createSocket("localhost", 8000);
            //hacer handshake
            sslsocket.startHandshake();
            System.out.println("Se ha autenticado correctamente");
            
            salida = new ObjectOutputStream(sslsocket.getOutputStream());
            entrada = new ObjectInputStream(sslsocket.getInputStream());
            
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            boolean exit = false;
            while (!exit){
                System.out.println("> ");
                String line = buffer.readLine();
                salida.writeObject(line);
                salida.flush();
                
                String st = (String) entrada.readObject();
                System.out.println(st);
            }         
        
        }catch (IOException ex) {
            } catch (ClassNotFoundException ex) {

		} finally {

			try {
				entrada.close();
				sslsocket.close();
			} catch (IOException ex) {

			}
		}           

		}
        
        
    }