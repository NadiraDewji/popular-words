import java.util.Comparator;

/**
 * @author NadiraDewji This is a class that implements Comparator so words can
 *         be sorted in frequency order. It only implements the compare method
 *         and it compares the first word to the second if the result is less
 *         than 0
 *
 */
public class CompareWordsFrequency implements Comparator<Word> {
	Comparator<Word> c = new CompareWordAlphabetically();

	@Override
	/**
	 * * it returns -1, if it is greater 1 and equal to 0 returns 0.
	 */
	public int compare(Word myWord, Word myWordTwo) {

		int comp = myWord.getFrequency() - myWordTwo.getFrequency();
		if (comp < 0) {
			return 1;

		} else if (comp > 0) {
			return -1;
		} else {
			return c.compare(myWord, myWordTwo);
		}

	}

}
