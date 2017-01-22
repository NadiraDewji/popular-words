import java.util.Comparator;

/**
 * * @author NadiraDewji This is a class that implements Comparator so words can
 * be sorted by scarcity It only implements the compare method and it compares
 * the first word to the second if the result is less than 0
 */
public class CompareWordScarcity implements Comparator<Word> {
	Comparator<Word> c = new CompareWordAlphabetically();

	@Override
	/**
	 * it returns -1, if it is greater 1 and equal to 0 returns 0. If the first
	 * word's frequency is less than the frequency of the second then it returns
	 * negative value.
	 */
	public int compare(Word o1, Word o2) {
		int comp = o1.getFrequency() - o2.getFrequency();
		if (comp < 0) {
			return -1;

		} else if (comp > 0) {
			return 1;
		} else {
			return c.compare(o1, o2);
		}

	}

}
