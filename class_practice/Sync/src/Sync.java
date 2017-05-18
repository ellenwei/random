
public class Sync extends Thread {
	private boolean flag;

	public Sync(boolean flag) {
		this.flag = flag;
	}

	synchronized static void classMeth() throws InterruptedException {
		System.out.println("in class meth");
		Thread.sleep(1000);
		System.out.println("out class meth");
	}

	synchronized void instanceMeth() throws InterruptedException {
		System.out.println("in instance meth");
		Thread.sleep(1000);
		System.out.println("out instance meth");
	}

	public void run() {
		try {
			if (flag) {
				instanceMeth();
			} else {
				Sync.classMeth();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static public void main(String[] args) {
		Sync s1 = new Sync(false);
		Sync s2 = new Sync(true);
		Sync s3 = new Sync(true);
		Sync s4 = new Sync(false);
		
		s1.start();
		s2.start();
		s3.start();
		s4.start();
	}
}
