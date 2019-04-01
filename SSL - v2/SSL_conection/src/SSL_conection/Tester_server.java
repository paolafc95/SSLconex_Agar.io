package SSL_conection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Tester_server extends Thread{
	Socket socket;
	Tester_server(Socket socket){
		this.socket = socket;
	}
	public void run() {
		try {
			PrintWriter printWriter = new PrintWriter (socket.getOutputStream(), true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader (socket.getInputStream()));
			System.out.println("- Usuario "+ bufferedReader.readLine()+ " está conectado al servidor -");
			while (true) printWriter.println(bufferedReader.readLine() + " eco");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
			
			