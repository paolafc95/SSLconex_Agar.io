import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
              
            // while (true){
                 String x = (String) entrada.readObject();
                 System.out.println("Se tiene: "+ x);
                 salida.writeObject(x+x);
                // salida.flush();
              //}
                 entrada.close();
                 salida.close();
                 this.sslsocket.close();
         } catch (IOException ex) {
       //Logger.getLogger(CmdLineService.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
       //Logger.getLogger(CmdLineService.class.getName()).log(Level.SEVERE, null, ex); 
    } 
}
}