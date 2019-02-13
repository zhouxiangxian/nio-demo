package my.xiaoxian.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,AIOServerThread> {


	@Override
	public void completed(AsynchronousSocketChannel channel, AIOServerThread aioThread) {
		aioThread.getServerChannel().accept(aioThread, this);
		ByteBuffer buffer = ByteBuffer.allocate(50);
		channel.read(buffer,buffer,new EchoHandler(channel));
	}

	@Override
	public void failed(Throwable exc, AIOServerThread aioThread) {
		System.out.println("服务器连接失败....");
		aioThread.getLatch().countDown();
		
	}

}
