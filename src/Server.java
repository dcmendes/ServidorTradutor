import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;

public class Server {

public static void main(String[] args) {
		
		MulticastSocket aSocket = null;

		try {
			aSocket = new MulticastSocket(80);
			byte[] buffer = new byte[1000];
			Dicionay dicionario = new Dicionay();
			
			while(true) {
				String mensagem;
				mensagem = "";
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				
				mensagem = new String(request.getData());
				System.out.println(mensagem);
				mensagem = dicionario.searchWord(mensagem);
				System.out.println(mensagem);
						
				DatagramPacket reply = new DatagramPacket(mensagem.getBytes(), mensagem.getBytes().length, request.getAddress(), request.getPort());
				
				
				//DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
				aSocket.send(reply);
				
			
				
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null) {
				aSocket.close();
			}
		}
	}

}
