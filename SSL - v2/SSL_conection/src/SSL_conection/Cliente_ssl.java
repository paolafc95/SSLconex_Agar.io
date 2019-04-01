package SSL_conection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class Cliente_ssl {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.setProperty("javax.net.ssl.trustStore", "pfc.store");
		Socket socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket("localhost", 4444);
		BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter printWriter = new PrintWriter (socket.getOutputStream(), true);
		BufferedReader commandPromptBufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese su nombre: ");
		printWriter.println(commandPromptBufferedReader.readLine());
		String message = null;
		
		while(true) {
			System.out.println("Ingrese mensaje a enviar al servidor: ");
			message = commandPromptBufferedReader.readLine();
			
			if(message.equals("quit")) {
				socket.close();
				break;
			}
			printWriter.println(message);
			System.out.println("Mensaje respuesta del servidor: ");
			System.out.println(socketBufferedReader.readLine());
			
		}
			

	}

}
