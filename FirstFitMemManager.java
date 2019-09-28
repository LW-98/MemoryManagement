package CO2017.exercise2.lw306;

public class FirstFitMemManager extends MemManager {

	public FirstFitMemManager(int s) {
		super(s);
	}

	protected int findSpace(int s) {
		for (int i = 0; i < _memory.length; i++) {
			if (countFreeSpacesAt(i) >= s) {
				return i;
			}
		}
		return -1;
	}
}
