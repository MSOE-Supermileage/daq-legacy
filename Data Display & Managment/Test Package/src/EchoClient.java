import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException {
    	
        Socket echoSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        String host = "0.0.0.0";
        int port = 1234;

        try {
            echoSocket = new Socket(host, port);
            out = new ObjectOutputStream(echoSocket.getOutputStream());
            in = new ObjectInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host + ".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host + ".");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while ((userInput = stdIn.readLine()) != null) {
        	try {
        		out.writeObject(userInput);
				System.out.println(in.readObject().toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	
		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
    }
}