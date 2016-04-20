package OtrioOL;

import java.io.IOException;
import java.net.Socket;

public interface Delivery {
	public int read(Socket socket) throws IOException;
	public void write(Socket socket) throws IOException;

}
