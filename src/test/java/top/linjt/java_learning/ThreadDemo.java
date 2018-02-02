package top.linjt.java_learning;

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		final Object o = new Object ();
		System.out.println("1.主线程开始");
		Thread t1 = new Thread(new Runnable() {
//			Object o = new Object ();
			public void run() {
				synchronized (o) {
					System.out.println("2.t1开始---");
					try {
						System.out.println("3.放弃对象锁,等待其他线程通知对象锁被释放,使该线程进入锁池");
						o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("5.t1 结束---");
			}
		});
		t1.start();
//		t1.join();
		Thread.sleep(1000);
		synchronized (o) {
			System.out.println("4.拿到对象锁--开始唤醒");
			
			o.notifyAll();
			
		}
		Thread.sleep(5000);
		System.out.println("6.主线程结束");
	}
	
}
