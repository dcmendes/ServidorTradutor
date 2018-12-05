import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;

public class DicionaryServer implements Runnable {

	MulticastSocket aSocket;
	Dicionay dicionary;

	public DicionaryServer() {
		super();
		this.aSocket = aSocket;
		this.dicionary = dicionary;
	}

	public void executa() {

		try {
			aSocket = new MulticastSocket(4545);
			byte[] buffer = new byte[1024];

			while (true) {
				String mensagem, output;
				mensagem = "";
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);

				mensagem = new String(request.getData());
				System.out.println(mensagem);

				dicionary.setString(mensagem);

				output = dicionary.getResult();

				DatagramPacket reply = new DatagramPacket(output.getBytes(), output.getBytes().length,
						request.getAddress(), request.getPort());

				aSocket.send(reply);
				buffer = null;
				buffer = new byte[1024];

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

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
