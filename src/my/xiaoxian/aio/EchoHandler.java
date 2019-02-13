package my.xiaoxian.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class EchoHandler implements CompletionHandler<Integer, ByteBuffer> {

	private AsynchronousSocketChannel clientChannel;

	private boolean exit = false;

	public EchoHandler(AsynchronousSocketChannel clientChannel) {
		this.clientChannel = clientChannel;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		buffer.flip();
		String readMsg = new String(buffer.array(), 0, buffer.remaining()).trim();
		System.err.println("服务端接受到数据:" + readMsg);
		String returnMsg = "【ECHO】" + readMsg + "\n";
		if ("exit".equals(readMsg)) {
			returnMsg = "【拜拜】";
			this.exit = true;

		}
		this.echoWrite(returnMsg);

	}

	private void echoWrite(String returnMsg) {
		ByteBuffer buffer = ByteBuffer.allocate(50);
		buffer.put(returnMsg.getBytes());
		buffer.flip();
		
		this.clientChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				if(buffer.hasRemaining()) {
					EchoHandler.this.clientChannel.write(buffer,buffer,this);
					
				}else {
					ByteBuffer readBuffer = ByteBuffer.allocate(100);
					EchoHandler.this.clientChannel.read(readBuffer,readBuffer,new EchoHandler(EchoHandler.this.clientChannel));
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer buffer) {
				EchoHandler.this.closeClient();
			}
		});
		
	}

	@Override
	public void failed(Throwable exc, ByteBuffer buffer) {
		this.closeClient();
	}

	private void closeClient() {
		System.out.println("客户端连接有误，中断与客户端的连接");
		try {
			this.clientChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
