package cn.mldn.test.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataBuffer<T> {
	private static final int MAX_LENGTH = 5;
	private Object[] data = new Object[MAX_LENGTH];// 进行数据的保存
	private Lock lock = new ReentrantLock();// 数据锁
	private Condition putCondition = lock.newCondition();
	private Condition getCondition = lock.newCondition();
	private int putIndex = 0;
	private int getIndex = 0;
	private int count = 0;// 数据的个数
	// 放数据

	public void put(T t) {
		this.lock.lock();
		try {
			if(this.count==MAX_LENGTH) {
				//已经满了，写线程进行等待
				try {
					this.putCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			data[this.putIndex++]=t;
			if(this.putIndex==MAX_LENGTH) {
				this.putIndex=0;
			}
			this.count++;
			this.getCondition.signal();//取线程可以取数据
			
		}finally {
			this.lock.unlock();
		}
	}

	// 取数据
	public T get() {
		Object takeObject = null;
		this.lock.lock();// 加锁
		try {
			if (this.count == 0) {
				// 没有数据，读取的线程进行等待
				System.out.println("【"+Thread.currentThread().getName()+"】进入等待1");
				try {
					this.getCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("【"+Thread.currentThread().getName()+"】进入等待2");
			}
			takeObject = this.data[this.getIndex++];
			if (this.getIndex == MAX_LENGTH) {
				this.getIndex = 0;
			}
			this.count--;
			this.putCondition.signal();
		} finally {
			this.lock.unlock();// 释放锁
		}
		return (T) takeObject;
	}

}
