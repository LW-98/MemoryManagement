package CO2017.exercise2.lw306;

public class Process implements Runnable {

	char id;
	int size;
	int runtime;
	int address = -1;
	MemManager manager;

	public Process(MemManager m, char i, int s, int r) {
		id = i;
		manager = m;
		size = s;
		runtime = r;
	}

	public int getSize() {
		return size;
	}

	public char getID() {
		return id;
	}

	public void setAddress(int a) {
		address = a;
	}

	public int getAddress() {
		return address;
	}

	public void run() {
		System.out.println(this + " waiting to run.");
		try {
			manager.allocate(this);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(this + " running.");
		try {
			Thread.sleep(100*runtime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		manager.free(this);
		System.out.println(this + " has finished.");
	}

	@Override
	public String toString() {
		
		if (address == -1) {
			String string = this.getID() + ":" + 'U' + "+" + this.getSize();
			return string;
		}
		String string = this.getID() + ":" + this.getAddress() + "+" + this.getSize();
		return string;
	}
}