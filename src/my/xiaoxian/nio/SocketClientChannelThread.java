package my.xiaoxian.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientChannelThread implements Runnable {

	private SocketChannel clientChannel;
	private boolean flag = true;

	public SocketClientChannelThread(SocketChannel clientChannel) throws Exception {
		this.clientChannel = clientChannel;
		System.out.println("【客户端连接成功】，该客户端的连接地址为:" + clientChannel.getRemoteAddress());
	}

	@Override
	public void run() {
		ByteBuffer buffer = ByteBuffer.allocate(50);
		try {
			while (this.flag) {
				// 读取数据
				buffer.clear();
				int read = this.clientChannel.read(buffer);
				String readMessage = new String(buffer.array(), 0, read).trim();
				System.out.println("服务器接受到的消息:" + readMessage);

				String writeMessage = null;
				writeMessage = "【echo】" + readMessage + "\n";
				if ("exit".equals(readMessage)) {
					this.flag = false;
					writeMessage = "拜拜！";

				}
				buffer.clear();// 清空缓存区
				buffer.put(writeMessage.getBytes());
				// 重置缓存区
				buffer.flip();
				clientChannel.write(buffer);

			}
			this.clientChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
