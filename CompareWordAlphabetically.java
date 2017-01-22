import java.util.Comparator;
import java.util.*;

/**
 * * @author NadiraDewji This is a class that implements Comparator so words can
 * be sorted in alphabetical order. It only implements the compare method and it
 * compares the first word to the second if the result is less than 0
 *
 */
public class CompareWordAlphabetically implements Comparator<Word> {
	@Override
	/**
	 * it returns -1, if it is greater 1 and equal to 0 returns 0.
	 */
	public int compare(Word o1, Word o2) {
		int comp = o1.getWord().compareToIgnoreCase(o2.getWord());
		if (comp < 0) {
			return -1;

		} else if (comp > 0) {
			return 1;
		} else {
			return 0;
		}

	}

}
