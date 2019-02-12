package cn.mldn.test.juc;

import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
	private ReentrantLock lock = new ReentrantLock();
	private int count = 100;

	// 卖票方法
	public void sale() {
		lock.lock();
		try {
			if(this.count>0) {
				System.out.println(Thread.currentThread().getName()+"卖票："+this.count--);
			}
		} finally {
			lock.unlock();
		}

	}

}
