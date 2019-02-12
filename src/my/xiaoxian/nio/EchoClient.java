package my.xiaoxian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import my.xiaoxian.nio.util.InputUtil;

public class EchoClient {
	public static final String HOST="localhost";
	public static final int PORT=9999;
	public static void main(String[] args) throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress(HOST, PORT));
		ByteBuffer buffer = ByteBuffer.allocate(50);
		boolean flag=true;
		while(flag) {
			buffer.clear();
			
			//写数据
			
			String msg=InputUtil.getString("请输入数据:");
			buffer.put(msg.getBytes());
			buffer.flip();//重置
			
			//将数据放入到通道中
			socketChannel.write(buffer);
			buffer.clear();
			
			
			//读取缓存中的数据
			int readCount=socketChannel.read(buffer);
			
			//重设缓存区
			
			buffer.flip();
			
			System.out.println(new String(buffer.array(),0,readCount));
			
			if("exit".equals(msg)) {
				flag=false;
			}
			
			
		}
		socketChannel.close();
		
	}

}
