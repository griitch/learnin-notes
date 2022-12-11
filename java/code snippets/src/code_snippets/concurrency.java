package code_snippets;

public class concurrency  implements Runnable {

// An application that creates an instance of Thread must provide the code that will run in that thread.
//	the runnable object got one method run that contain the code to be executed in the thread
	private static int i = 0;
	public void run() {
		System.out.println("hello word from thread " + i );
		i++;
	}
	public static void main(String[] args) throws InterruptedException {	
		Thread t1 = new Thread(new concurrency());
		t1.start();
		Thread t2 = new Thread(new concurrency());
		t1.join();
		t2.start();
		// t.join() causes the current thread to wait for t to end
		// t.join(1000) causes the current thread to wait until either 1sec has passed or t has died
		t2.join();
		System.out.println("hello from main");
		//Thread.sleep(3000); 
}}
