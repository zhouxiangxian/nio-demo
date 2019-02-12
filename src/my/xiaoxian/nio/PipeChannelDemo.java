package my.xiaoxian.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

public class PipeChannelDemo {
	public static void main(String[] args) throws IOException {
		Pipe open = Pipe.open();
		new Thread(()->{
			SourceChannel source = open.source();
			ByteBuffer buffer = ByteBuffer.allocate(50);
			try {
				int read = source.read(buffer);
				buffer.flip();//重置
				System.out.println("{接受端}"+new String(buffer.array(),0,read));
			} catch (IOException e) {
				e.printStackTrace();
			}
		},"接受线程").start();
		
		
		new Thread(()->{
			String msg="【"+Thread.currentThread()+"www.xiaoxian.com】";
			SinkChannel sink = open.sink();
			ByteBuffer buffer = ByteBuffer.allocate(50);
			buffer.put(msg.getBytes());
			buffer.flip();
			while(buffer.hasRemaining()) {
				try {
					sink.write(buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		},"发送线程").start();
	}

}
