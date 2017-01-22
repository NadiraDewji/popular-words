/**
 * 
 * @author NadiraDewji This is the Word class and it contains the methods and
 *         data fields that constitute the word object.
 */
public class Word {
	/**
	 * The frequency indicates how many times the word is present The String
	 * word is the String representation of the word
	 */
	private int frequency;
	private String word;

	/**
	 * This is the constructor, it takes in string word and a frequency.
	 * 
	 * @param word
	 * @param frequency
	 */
	public Word(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}

	/**
	 * This is a getter method for frequency
	 * 
	 * @return the frequency
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * This is a getter method for the word string.
	 * 
	 * @return the string name
	 */
	public String getWord() {
		return this.word;
	}

	/**
	 * This is a setter and it sets the frequency
	 * 
	 * @param frequency
	 * @return returns the updated frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
