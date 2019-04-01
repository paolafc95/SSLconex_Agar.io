
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClienteSSL {
    public static void main (String[] args) {
        //String sDirectorioTrabajo = System.getProperty("user.dir");
        //System.out.println("El directorio de trabajo es " + sDirectorioTrabajo);
        
        System.setProperty("javax.net.ssl.trustStore", "pfc.store" );     //pfc.store cambiar por esto
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;
        SSLSocket sslsocket = null;
        
        try{
            SSLSocketFactory fact = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sslsocket = (SSLSocket) fact.createSocket("localhost", 8000);   //("localhost",SERVER_PORT)
            //hacer handshake
            sslsocket.startHandshake();
            System.out.println("Se ha autenticado correctamente");
            
            salida = new ObjectOutputStream(sslsocket.getOutputStream());
            entrada = new ObjectInputStream(sslsocket.getInputStream());
            
           // BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            boolean exit = false;
            //while (!exit){
                System.out.println("> ");
                String line = "changos";
                salida.writeObject(line);
              //  salida.flush();
                
                String st = (String) entrada.readObject();
                System.out.println(st);
            //}         
                entrada.close();
                sslsocket.close();
        }catch (Exception e) {
        	System.out.println(e.getMessage());
            } 
		

            

		}    
        
    }