
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductConsumerWithMonitors {
	public static Buffer buffer = new Buffer();
	
	public static void main(String[] args)
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
	}
	
	
	
	private static class Buffer
	{
		private static final int CAPACITY = 1;
		private LinkedList<Integer> queue = new LinkedList<Integer>();
		private static Object notEmpty = new Object();
		private Object notFull = new Object();
		public void write (int value)
		{
			synchronized(notFull)
			{
				synchronized(notEmpty)
				{
					try
					{
						while (queue.size() == CAPACITY)
						{
							System.out.println("Wait for notFull condition " + value);
							notFull.wait();
						}
						queue.offer(value);
						notEmpty.notify();
					}
					catch(InterruptedException ie)
					{
						System.out.println("BUffer. write IE: " + ie.getMessage());
					}
				}
			}
			
		}
		public int read()
		{
			int value = 0;
			
		}
	}
	
	private static class ConsumerTask implements Runnable
	{
		public void run()
		{
			try
			{
				while (true)
				{
					System.out.println("\t\tConsumer reads: " + buffer.read());
					
				}
			}
		}
	}
	
	private static class ProducerTask implements Runnable{
		@Override
		public void run()
		{
			try
			{
				int i = 1;
				while(true)
				{
					System.out.println("Produce writes: " + i);
					buffer.write(i++);
					Thread.sleep((int)(Math.random()*1000));
				}
			}
			catch(InterruptedException ie)
			{
				System.out.println("Producer IE: " + ie.getLocalizedMessage());
			}
		}

	}

}






