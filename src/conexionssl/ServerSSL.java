package conexionssl;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import javax.net.ssl.*;

public class ServerSSL {
    public static void main (String[] args){
        //variables que guardan las contrase�as del cliente y keystore respectivamente
        String passwClient = "cliente";
        String passwKs = "keystore";
        
        String nameKs = "./resources/Servidor.jks";         //se establece el nombre de la KeyStore -sujeta a cambios-
        char[] passwordCl = passwClient.toCharArray();
        char[] passwordKs = passwKs.toCharArray();
        
        KeyStore ks;        //se crea una nueva KeyStore
        try{
            ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(nameKs), passwordKs);       //se carga la KeyStore
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SUNX509");
            kmf.init(ks, passwordCl);
            
            SSLContext scon = SSLContext.getInstance("TLS");
            scon.init(kmf.getKeyManagers(), null, null );
            SSLServerSocketFactory  ssf = scon.getServerSocketFactory();
            SSLServerSocket s = (SSLServerSocket)ssf.createServerSocket(8000);
            
            while (true){
                 SSLSocket sslsocket =(SSLSocket) s.accept();
                 System.out.println("Se ha aceptado un nuevo cliente");
                 
                 Tester t = new Tester (sslsocket);
                 t.start();                 
            }
 } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | KeyManagementException ex) {     
    }     
    }    
}
