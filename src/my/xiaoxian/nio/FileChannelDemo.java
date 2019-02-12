package my.xiaoxian.nio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
	public static void main(String[] args) {
		File file = new File("D:" + File.separator + "hello.txt");
		FileInputStream input = null;
		FileChannel fileChannel = null;
		ByteArrayOutputStream bos = null;
		try {
			input = new FileInputStream(file);
			fileChannel = input.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(20);
			bos = new ByteArrayOutputStream();
			while ((fileChannel.read(byteBuffer)) != -1) {
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					bos.write(byteBuffer.get());
				}
				byteBuffer.clear();
			}
			System.out.println(new String(bos.toByteArray(),"GBK"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭通道
			try {
				fileChannel.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
