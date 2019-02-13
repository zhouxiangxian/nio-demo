package my.xiaoxian.aio;

import java.io.IOException;

public class AIOEchoServer {
	public static void main(String[] args) throws IOException {
		new Thread(new AIOServerThread()).start();
	}
}
