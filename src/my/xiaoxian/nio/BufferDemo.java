package my.xiaoxian.nio;

import java.nio.ByteBuffer;

public class BufferDemo {
	public static void main(String[] args) {
		String str="www.xiaoxian.comdddddddddddddddd";
	    ByteBuffer buffer = ByteBuffer.allocate(200);
	    System.out.println("1:capacity="+buffer.capacity()+",limit="+buffer.limit()+",position="+buffer.position());
		buffer.put(str.getBytes());
		System.out.println("2:capacity="+buffer.capacity()+",limit="+buffer.limit()+",position="+buffer.position());
		buffer.flip();
		
		System.out.println("3:capacity="+buffer.capacity()+",limit="+buffer.limit()+",position="+buffer.position());
		
		
		while(buffer.hasRemaining()) {
			System.out.print(buffer.get()+"、");
		}
		
		System.out.println();
		
		
		//清空缓存区
		
		
		buffer.clear();
		
		System.out.println("4:capacity="+buffer.capacity()+",limit="+buffer.limit()+",position="+buffer.position());
		
	}

}
