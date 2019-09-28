package CO2017.exercise2.lw306;

public class BestFitMemManager extends MemManager {

	public BestFitMemManager(int s) {
		super(s);
	}

	protected int findSpace(int s) {
		int temp = -1;
		for (int i = 0; i < _memory.length; i++) {
			if (countFreeSpacesAt(i) >= s) { 
				temp = i;
				break;
			}
		}
		for (int i = 0; i < _memory.length; i++) {
			if (countFreeSpacesAt(i) < countFreeSpacesAt(temp) && countFreeSpacesAt(i) >= s) {
				temp = i;
			}
		}
		return temp;
	}
}
