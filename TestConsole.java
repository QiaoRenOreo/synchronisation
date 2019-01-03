package ss.week7.synchronisation;
import java.io.*;
import java.util.Scanner;

public class TestConsole extends Thread
{
	//deleted: public Console console;

	//deleted: char threadName;
    public TestConsole(String name) 
    {
        super(name);
        //deleted:  console=new Console();
    } 

    
	private void sum()
	{
		int aInt;
		int bInt;
		aInt=Console.readInt(this.getName()+": get number 1? ");//Console as a class its method can be called directly
		bInt=Console.readInt(this.getName()+": get number 2?");
		int sumResult=aInt+bInt;
		Console.println(this.getName()+": " + aInt + " + "+ bInt + "=" + sumResult);
	}

    public void run() 
    {
    	this.sum();
    }
	
	public static void main(String args[])
	{
		TestConsole t1= new TestConsole("Thread A");
		TestConsole t2= new TestConsole("Thread B");
		t1.start();
		t2.start();
	}
}