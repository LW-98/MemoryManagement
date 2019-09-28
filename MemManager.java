package CO2017.exercise2.lw306;

public abstract class MemManager {

	volatile boolean _changed;
	volatile int _largestSpace;
	final char[] _memory;

	public MemManager(int s) {
		_changed = true;
		_largestSpace = s;
		_memory = new char[s];
		for (int i = 0; i<_memory.length; i++) {
			_memory[i] = '.';
		}
	}

	public synchronized void allocate(Process p) throws InterruptedException {
		while (!(p.getSize()<=_largestSpace)) wait();
		int address = findSpace(p.getSize());
		p.setAddress(address);
		for (int i = address; i < (p.getSize()+address); i++) {
			_memory[i] = p.getID();
		}
		int max = countFreeSpacesAt(0);
		for (int j = 1; j < _memory.length; j++) {
			if (countFreeSpacesAt(j) > max) max = countFreeSpacesAt(j);
		}
		_largestSpace = max;
		_changed = true;
		notifyAll();
	}

	int countFreeSpacesAt(int pos) {
		int count = 0;
		for (int i = pos; i < (_memory.length); i++) {
			if (_memory[i] != '.') {
				break;
			} else {
				count++;
			}
		}
		return count;
	}

	protected abstract int findSpace(int s);

	public synchronized void free(Process p) {
		for (int i = 0; i < _memory.length; i++) {
			if (_memory[i] == p.getID()) {
				_memory[i] = '.';
			}
		}
		p.setAddress(-1);
		int max = countFreeSpacesAt(0);
		for (int j = 1; j < _memory.length; j++) {
			if (countFreeSpacesAt(j) > max) max = countFreeSpacesAt(j);
		}
		_largestSpace = max;
		_changed = true;
		notifyAll();
	}

	boolean isChanged() {
		return _changed;
	}

	@Override
	public String toString() {
		String string = "";
		int count = 0;
		int temp = 0;
		for (int i = 0; i < ((double) _memory.length/20); i++) {
			string += String.format("%4s",temp + "|");
			for (int j = 0; j < 20; j++) {
				if (count >= _memory.length) break;
				string += _memory[count];
				count++;
			}
			string += "|\n";
			temp+=20;
		}
		int max = countFreeSpacesAt(0);
		for (int x = 1; x < _memory.length; x++) {
			if (countFreeSpacesAt(x) > max) max = countFreeSpacesAt(x);
		}
		string += "ls:" + String.format("%4s",max);;
		return string;
	}
}