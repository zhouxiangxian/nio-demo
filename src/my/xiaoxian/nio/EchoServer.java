package my.xiaoxian.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
	public static final int PORT = 9999;

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		ServerSocketChannel open = ServerSocketChannel.open();
		open.configureBlocking(false);
		open.bind(new InetSocketAddress(PORT));
		Selector selector = Selector.open();
		open.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("服务端启动程序，在"+PORT+"端口上进行监听");
		
		while((selector.select())>0) {
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iter=selectedKeys.iterator();
			while(iter.hasNext()) {
				SelectionKey selectionKey=iter.next();
				if(selectionKey.isAcceptable()) {
					SocketChannel clientChannel = open.accept();
					if(clientChannel!=null) {
						executorService.submit(new SocketClientChannelThread(clientChannel));
					}
				}
				iter.remove();
 			}
		}
		
		executorService.shutdown();
		open.close();
	}

}
