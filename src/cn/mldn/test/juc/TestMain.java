package cn.mldn.test.juc;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestMain {
	public static void main(String[] args) {
		//testPayTicket();
		//testReadWriteLock();
		
		
		//testCondition();
		//testSemaphore();
		
		//testCountDownLatch();
		
		//testCyclicBarrier();
		
		//testExchanger();
		
		List<String> list=new CopyOnWriteArrayList<String>();
		for(int x=0;x<3;x++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int y=0;y<10;y++) {
						list.add("CopyOnWriteArrayList【"+Thread.currentThread().getName()+"】");
						System.out.println(list);
					}
				}
			},"xiaoxian-"+x).start();
		}
	}

	private static void testExchanger() {
		Exchanger<String> exchange=new Exchanger<String>();
		for(int x=0;x<3;x++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							String data = exchange.exchange(null);
							TimeUnit.SECONDS.sleep(3);
							if(data!=null) {
								System.out.println("【"+Thread.currentThread().getName()+"】get data="+data);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			},"消费者-"+x).start();
		}
		
		
		for(int x=0;x<2;x++) {
			int temp=x;
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int y=0;y<10;y++) {
						String data="xiaoxian-"+temp+"-"+y;
						try {
							TimeUnit.SECONDS.sleep(2);
							exchange.exchange(data);
							System.out.println("【"+Thread.currentThread().getName()+"】生产数据:"+data);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			},"生产者-"+x).start();
		}
	}

	private static void testCyclicBarrier() {
		CyclicBarrier barrier=new CyclicBarrier(2);
		for(int x=0;x<3;x++) {
			int sec=x;
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("【"+Thread.currentThread().getName()+"】等待开始");
					if(sec==2) {
						barrier.reset();
						System.out.println("【"+Thread.currentThread().getName()+"】重置处理");
					}else {
						try {
							TimeUnit.SECONDS.sleep(sec);
							barrier.await(4,TimeUnit.SECONDS);
							System.out.println("【"+Thread.currentThread().getName()+"】开始工作");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			},"娱乐-"+x).start();
		}
	}

	private static void testCountDownLatch() {
		CountDownLatch cdl=new CountDownLatch(5);
		for(int x=0;x<5;x++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					cdl.countDown();
					System.out.println("【"+Thread.currentThread().getName()+"】执行了");
				}
			},"cdl-"+x).start();
		}
		try {
			System.out.println("执行await前");
			cdl.await();
			System.out.println("执行await后");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void testSemaphore() {
		Semaphore semaphore=new Semaphore(2);
		Random random=new Random();
		for(int x=0;x<10;x++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					if(semaphore.availablePermits()>0) {
						System.out.println("【"+Thread.currentThread().getName()+"】进入银行，没有人排队");
					}else {
						System.out.println("【"+Thread.currentThread().getName()+"】排队办理业务");
					}
					try {
						semaphore.acquire();
						System.out.println("【"+Thread.currentThread().getName()+"】开始办理业务");
						TimeUnit.SECONDS.sleep(random.nextInt(10));
						System.out.println("【"+Thread.currentThread().getName()+"】结束办理业务");
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			},"顾客-"+x).start();
		}
	}

	private static void testCondition() {
		DataBuffer<String> buffer=new DataBuffer<String>();
		for(int x=0;x<3;x++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int y=0;y<10;y++) {
						try {
							TimeUnit.SECONDS.sleep(3);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						buffer.put("【"+Thread.currentThread().getName()+"】put y="+y);
					}
				}
			},"生成者-"+x).start();
		}
		
		
		for(int x=0;x<5;x++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("【"+Thread.currentThread().getName()+"】get ="+buffer.get());
					}
				}
			},"消费者-"+x).start();
		}
	}

	private static void testReadWriteLock() {
		Account account=new Account("小贤",15.0);
		double saveMoney[]=new double[] {5.0,2000.0,1200.0,1800.0,5000.0};
		//查询余额
		for(int x=0;x<10;x++) {
			new Thread(()->{
				System.out.println(Thread.currentThread().getName()+"查账，账户名："+account.getName()+",余额:"+account.loadMoney());
			}).start();
		}
		//存款
		for(int x=0;x<2;x++) {
			new Thread(()->{
				for(int y=0;y<saveMoney.length;y++) {
					account.saveMoney(saveMoney[y]);
				}
			}).start();
		}
	}

	private static void testPayTicket() {
		Ticket ticket=new Ticket();
		for(int x=0;x<6;x++) {
			new Thread(new MyRunnable(ticket)).start();
		}
	}

}
class MyRunnable implements Runnable{
    private Ticket ticket;
	public MyRunnable(Ticket ticket) {
    	this.ticket=ticket;
    }
	@Override
	public void run() {
		while(true) {
			ticket.sale();
		}
		
	}
	
}
