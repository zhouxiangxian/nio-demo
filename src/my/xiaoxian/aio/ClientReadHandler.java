package my.xiaoxian.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class ClientReadHandler implements CompletionHandler<Integer,ByteBuffer> {
	private AsynchronousSocketChannel clientChannel;

	private CountDownLatch cdl;

	public ClientReadHandler(AsynchronousSocketChannel clientChannel, CountDownLatch cdl) {
		this.cdl = cdl;
		this.clientChannel = clientChannel;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		buffer.flip();
		String receiveMessage = new String(buffer.array(),0,buffer.remaining());
		System.err.println(receiveMessage);
		
	}

	@Override
	public void failed(Throwable exc, ByteBuffer buffer) {
		System.out.println("发送出现了问题，该客户端被关闭");
		try {
			this.clientChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cdl.countDown();
	}

	

}
