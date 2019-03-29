package conexionssl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClienteSSL {
    public static void main (String[] args){
        System.setProperty("java.net.ssl.trustore", "./resources/Servidor.jks" );
        ObjectOutputStream salida = null;
        ObjectInputStream entrada = null;
        SSLSocket sslsocket = null;
        
        try{
            SSLSocketFactory fact = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sslsocket = (SSLSocket) fact.createSocket("localhost", 8000);
            
        }catch (IOException ex) {

		}
        
        
    }
    
}
