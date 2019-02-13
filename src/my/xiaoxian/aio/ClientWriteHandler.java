package my.xiaoxian.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class ClientWriteHandler implements CompletionHandler<Integer,ByteBuffer> {
	
	
	private AsynchronousSocketChannel clientChannel;
	
	private CountDownLatch cdl;
	
	public ClientWriteHandler(AsynchronousSocketChannel clientChannel,CountDownLatch cdl) {
		this.cdl=cdl;
		this.clientChannel=clientChannel;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		if(buffer.hasRemaining()) {
			this.clientChannel.write(buffer,buffer,this);
			
		}else {
			ByteBuffer readBuffer = ByteBuffer.allocate(100);
			this.clientChannel.read(readBuffer,readBuffer,new ClientReadHandler(this.clientChannel,this.cdl));
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer buffer) {
		
	}

	

}
