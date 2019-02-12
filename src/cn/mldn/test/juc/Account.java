package cn.mldn.test.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
	private String name;// 开户名
	private double asset;// 存款
	private ReadWriteLock myLock = new ReentrantReadWriteLock(false);
    public Account(String name,double asset) {
    	this.name=name;
    	this.asset=asset;
    }
	public String getName() {
		return name;
	}
    //存款
	public boolean saveMoney(double money) {
		myLock.writeLock().lock();
		try {
			try {
				System.out.println("【" + Thread.currentThread().getName() + "（before）】存款:" + money);
				TimeUnit.SECONDS.sleep(1);
				if (money > 0) {
					this.asset += money;
					return true;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			System.out.println("【" + Thread.currentThread().getName() + "（after）】取款:" + this.asset);
			myLock.writeLock().unlock();
		}
		return false;
	}
	
	
	public double loadMoney() {
		myLock.readLock().lock();
		try {
			return this.asset;
		}finally {
			myLock.readLock().unlock();
		}
	}

}
