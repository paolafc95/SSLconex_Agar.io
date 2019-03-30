package conexionssl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.net.ssl.SSLSocket;

public class Tester extends Thread {
    SSLSocket sslsocket;
    
    public Tester (SSLSocket sslsocket){
        this.sslsocket = sslsocket;
        
    }
    
    public void run (){
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
         try{
             entrada = new ObjectInputStream(sslsocket.getInputStream());
             salida = new ObjectOutputStream (sslsocket.getOutputStream());
              
             while (true){
                 String x = (String) entrada.readObject();
                 System.out.println("Se tiene: "+ x);
                 salida.writeObject(x.concat(x));
                 salida.flush();
              }
         } catch (IOException ex) {
       
    } catch (ClassNotFoundException ex) {
        
    } finally {
        try {
            entrada.close();
            salida.close();
            this.sslsocket.close();
        } catch (IOException ex) {
            
        }
    }  
}
}
