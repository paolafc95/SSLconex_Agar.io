
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;

public class ServerSSL {
    public static void main (String[] args){
    	System.setProperty("javax.net.ssl.keyStore", "pfc.store" );
		System.setProperty("javax.net.ssl.keyStorePassword", "keystore");
        //variables que guardan las contraseñas del cliente y keystore respectivamente
        String passwClient = "cliente";
        String passwKs = "keystore";
        System.out.println("esperando1");
        String nameKs = "pfc.store";         //se establece el nombre de la KeyStore -sujeta a cambios-
        char[] passwordCl = passwClient.toCharArray();
        char[] passwordKs = passwKs.toCharArray();
        System.out.println("esperando2");
        KeyStore ks;        //se crea una nueva KeyStore
        try{
        	
        	System.out.println("esperando3");
            ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(nameKs), passwordKs);   
            System.out.println(ks.toString()+" "+passwordCl);//se carga la KeyStore
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SUNX509");
            kmf.init(ks, passwordKs);
            
            System.out.println("esperando4");
            SSLContext scon = SSLContext.getInstance("TLS");
            scon.init(kmf.getKeyManagers(), null, null );
            SSLServerSocketFactory  ssf = scon.getServerSocketFactory();
            SSLServerSocket s = (SSLServerSocket)ssf.createServerSocket(8000);      //(SERVER_PORT)
           
            while (true){ 
                 SSLSocket sslsocket =(SSLSocket) s.accept();
                 System.out.println("Se ha aceptado un nuevo cliente");
                 
                 Tester t = new Tester (sslsocket);
                 t.start();                 
            }
 } catch (Exception e) {
	System.out.println(e.getMessage());
     //Logger.getLogger(TotalControlServer.class.getName()).log(Level.SEVERE, null, ex);
    }     
    }    
}