package my.xiaoxian.aio;

import java.io.IOException;

import my.xiaoxian.nio.util.InputUtil;

public class AIOEchoClient {
	public static void main(String[] args) throws IOException {
		AIOClientThread clientThread = new AIOClientThread();
		new Thread(clientThread).start();
		while(clientThread.sendMessage(InputUtil.getString("请输入要发送的数据："))) {
			
		}
	}
}
