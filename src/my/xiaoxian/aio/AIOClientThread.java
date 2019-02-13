package my.xiaoxian.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOClientThread implements Runnable {
	public static final String HOST = "localhost";
	public static final int PORT = 9999;

	private CountDownLatch cdl;

	private AsynchronousSocketChannel clientChannel;

	public AIOClientThread() throws IOException {
		this.clientChannel = AsynchronousSocketChannel.open();
		this.clientChannel.connect(new InetSocketAddress(HOST, PORT));
		this.cdl = new CountDownLatch(1);
	}

	@Override
	public void run() {
		try {
			this.cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean sendMessage(String msg) {
		boolean flag=true;
		ByteBuffer buffer = ByteBuffer.allocate(100);
		buffer.put(msg.getBytes());
		buffer.flip();//重置缓存区
		this.clientChannel.write(buffer, buffer, new ClientWriteHandler(this.clientChannel,this.cdl));
		
		if("exit".equalsIgnoreCase(msg)) {
			flag=false;
		}
		return flag;
	}

}
