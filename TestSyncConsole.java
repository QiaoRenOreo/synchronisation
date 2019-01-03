package ss.week7.synchronisation;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsole extends Thread{
		public SyncConsole syncConsole;
			    
		public TestSyncConsole(String name) 
	    {
	        super(name);
	        syncConsole=new SyncConsole();
	    } 
 
	    public  SyncConsole getConsole()
	    {
	    	return this.syncConsole;
	    }
	    
		private synchronized void sum() 
		{
			Lock syncConsolelock = new ReentrantLock(); // P 7.15 ReentrantLock implements the Lock interface
			syncConsolelock.lock();//P 7.15
			try{
				int aInt;
				int bInt;
				
				try{		
					aInt=syncConsole.readInt(this.getName()+": get number 1? ");
					bInt=syncConsole.readInt(this.getName()+": get number 2?");
					int sumResult=aInt+bInt;
					syncConsole.println(this.getName()+": " + aInt + " + "+ bInt + "=" + sumResult);
				}finally{
					syncConsolelock.unlock();//P 7.15
				}
			}finally{
				//nothing need to do in finally. but "finally{}" has to be here
			}
			
			/*P7.13 alternative way:
			synchronized (System.in) 
			{
				int aInt;
				int bInt;
				
				try{
					System.out.println("enter the first integer");
					//Scanner scanner=new Scanner (System.in);
					aInt=syncConsole.readInt(scanner.nextLine());
					//System.out.println("enter the second integer");
					bInt=syncConsole.readInt(scanner.nextLine());
					int sumResult=aInt+bInt;
					syncConsole.println(this.getName()+": get number 1? "+ aInt);
					syncConsole.println(this.getName()+": get number 2?"+ bInt);
					syncConsole.println(this.getName()+": " + aInt + " + "+ bInt + "=" + sumResult);
				}
				finally{
				
				}
			 */
		}

	    public void run() 
	    {
	    	this.sum();
	    }
		
		public static void main(String args[])
		{
			/**P7.15
			 * P-7.15: important to use the same lock for all threads. 
			 * If each thread has its own lock, 
			 * then the locks are basically useless because only the thread that has this lock can use it. 
			 * If you want to use a lock that is shared among all threads, try using a constant for the lock.*/

				TestSyncConsole t1= new TestSyncConsole("Thread A");
				TestSyncConsole t2= new TestSyncConsole("Thread B");
				t1.start();
				t2.start();

		}
		
}
