package my.xiaoxian.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLockDemo {
	public static void main(String[] args) {
		File file = new File("D:" + File.separator + "hello.txt");
		FileOutputStream fos = null;
		FileChannel channel = null;
		try {
			fos = new FileOutputStream(file);
			channel = fos.getChannel();
			FileLock tryLock = channel.tryLock();
			if (tryLock != null) {
				System.out.println("文件锁定30S");
				TimeUnit.SECONDS.sleep(30);
				tryLock.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (channel != null) {
					
					channel.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
