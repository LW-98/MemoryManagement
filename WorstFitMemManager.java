package CO2017.exercise2.lw306;

public class WorstFitMemManager extends MemManager {

	public WorstFitMemManager(int s) {
		super(s);
	}

	protected int findSpace(int s) {
		int max = 0;
		for (int i = 0; i < _memory.length; i++) {
			if (countFreeSpacesAt(i) > countFreeSpacesAt(max)) {
				max = i;
			}
		}
		return max;
	}
}
