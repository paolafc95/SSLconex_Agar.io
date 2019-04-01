package SSL_conection;
import java.io.IOException;
import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class Server_ssl {

	public static void main(String[] args) throws IOException {
		System.setProperty("javax.net.ssl.keyStore", "pfc.store" );
		System.setProperty("javax.net.ssl.keyStorePassword", "keystore");
		ServerSocket serverSocket = ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(4444);
		System.out.println("-Server up & ready for conections-");
		while(true) new Tester_server(serverSocket.accept()).start();
	}

}
