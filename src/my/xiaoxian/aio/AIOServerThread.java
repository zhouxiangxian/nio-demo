package my.xiaoxian.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AIOServerThread implements Runnable {
	
	private static final int PORT=9999;
	private CountDownLatch cdl=null;
	private AsynchronousServerSocketChannel serverChannel=null;
	
	public AIOServerThread() throws IOException {
		this.cdl=new CountDownLatch(1);
		this.serverChannel=AsynchronousServerSocketChannel.open();
		this.serverChannel.bind(new InetSocketAddress(PORT));
		System.out.println("服务端启动成功，在"+PORT+"端口上进行监听,等待客户端的连接.....");
	}

	
	
	public AsynchronousServerSocketChannel getServerChannel() {
		return this.serverChannel;
	}
	
	
	public CountDownLatch getLatch() {
		return this.cdl;
	}
	@Override
	public void run() {
		this.serverChannel.accept(this, new AcceptHandler());
		try {
			this.cdl.await();
			System.out.println("服务器连接失败，服务器停止运行.....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
