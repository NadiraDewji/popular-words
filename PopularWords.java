import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This is PopularWords class. It consists of the main method that runs the
 * program. It also consists of three other static methods that are used to help
 * this program complete the task: cleanEnds isValidWord, and cleanWord.
 * 
 * @author NadiraDewji
 *
 */
public class PopularWords {
	public static void main(String[] args) {
		/**
		 * These are three Comparable objects that are used to specify which
		 * method of sorting should be used depending on the arguments in the
		 * command line.
		 */
		CompareWordScarcity myComparableScarcity = new CompareWordScarcity();
		CompareWordAlphabetically myComparableAlpha = new CompareWordAlphabetically();
		CompareWordsFrequency myComparableFrequency = new CompareWordsFrequency();

		boolean kExists = false;
		int k = 0;
		/**
		 * An error message will be printed and the program will end if the
		 * number of arguments in the command line is only 1 or 0.
		 */
		if (args.length == 0 || args.length == 1) {
			System.out.println("You have entered invalid arguments in the command line");
			System.exit(1);
		}
		/**
		 * An error message will be printed and the program will end if the
		 * number of arguments in the command line is greater than 3.
		 */
		if (args.length > 3) {
			System.out.println("You have entered invalid arguments in the command line");
		}
		/**
		 * The third argument should be parsed into an integer and in the event
		 * it cannot, an error will be thrown, but then caught and the 3rd
		 * argument will be given a default value of 0, however, it will be
		 * later changed to the number of objects in the hashmap and therefore
		 * all items in the Hashmap will be printed. (If an invalid argument is
		 * in the second index, all values will be printed by default).
		 */
		try {
			Integer.parseInt(args[2]);
			k = Integer.parseInt(args[2]);
			kExists = true;
		} catch (Exception e) {
			k = 0;
		}
		/**
		 * A hashmap is instansiated with the key being of type string
		 * (corresponding to the name of the word) and the Word object will be
		 * the value.
		 */
		HashMap<String, Word> myHashMap = new HashMap<String, Word>();
		/**
		 * The text file is opened and is read line by line. In the event that
		 * an error is thrown while reading the text file, it will be caught.
		 * Every line is then split into an array of strings based on empty
		 * space " ". Then every word in the array of strings is iterated over.
		 * The program first used the isValidWord method to check if the string
		 * is even a valid word. This means it has at least one letter. If it is
		 * not a valid word, then there is no point in cleaning the ends or
		 * splitting it based on delimeters and so continue is used to move onto
		 * the next word. The word if it passes the isValidWord stage is then
		 * "cleaned". This means the cleanWord method is used to check for
		 * double word-conenctors delimeters. It returns an arraylist of words,
		 * because a single word can be split into multiple words because of
		 * word-connectors. Every word in the arraylist is then iterated over
		 * and if the hashmap contains the word the frequency is updated. If it
		 * is not the hashmap will add the word.
		 * 
		 */
		try {
			File f = new File(args[0]);
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String[] line = sc.nextLine().split(" ");
				for (String word : line) {
					ArrayList<String> myWords = new ArrayList<String>();
					// System.out.println(word);
					/**
					 * You have to check whether the word is a valid word based
					 * on the requirments! So first check that the word is just
					 * not dilmeters (" , --")
					 */
					if (isValidWord(word) == false) {
						continue;
					} else {
						myWords = cleanWord(word);
					}

					for (String myWord : myWords) {
						if (myHashMap.containsKey(myWord)) {
							Word myVal = myHashMap.get(myWord);
							myVal.setFrequency(myVal.getFrequency() + 1);
						} else {
							Word myNewWord = new Word(myWord, 1);
							myHashMap.put(myWord, myNewWord);
						}
					}
				}

			}

		} catch (Exception e) {
			System.out.println("Entered invalid arguments in the commandline");

		}
		// for(Word key: myHashMap.keySet()){
		// System.out.println(key);
		// }
		/**
		 * In the event nothing was added to the Hashmap, the program should
		 * just end as there is nothing to sort.
		 */
		if (myHashMap.size() == 0) {
			System.out.println("No valid words exist in your file");
			System.exit(1);
		}
		/**
		 * An array is created of the size of the Hashmap.
		 */
		Word[] myArrayWords = new Word[myHashMap.values().size()];
		/**
		 * Depending on the second argument, the appropriate parameter is used
		 * in the MergeSort.sort() method. This returns a sorted array.
		 */
		if (args[1].compareToIgnoreCase("frequency") == 0) {
			myArrayWords = MergeSort.sort(myHashMap, myComparableFrequency);

		} else if (args[1].compareToIgnoreCase("scarcity") == 0) {
			myArrayWords = MergeSort.sort(myHashMap, myComparableScarcity);
		} else if (args[1].compareToIgnoreCase("name") == 0) {
			myArrayWords = MergeSort.sort(myHashMap, myComparableAlpha);
			// for(Word myW: myArrayWords){
			// System.out.println(myW.getWord());
			// }
		} else {
			System.out.print("Invalid Arguments");
			System.exit(1);
		}
		/**
		 * If the user did not put in a k value in the command line, k will be
		 * equal to the hashmap's size.
		 */
		if (kExists == false) {
			k = myHashMap.size();
		}
		/**
		 * Otherwise, if k is greater than the size of the hashmap, then
		 * everything will be printed. Otherwise, K will remain to be the size
		 * specified by the user.
		 */
		if (kExists) {
			if (k > myHashMap.size()) {
				k = myHashMap.size();
			}
		}
		/**
		 * The content is then printed with the name of the word and its
		 * frequency seperated by a space " ".
		 */
		for (int i = 0; i < k; i++) {
			System.out.println(myArrayWords[i].getWord() + " " + myArrayWords[i].getFrequency());

		}

	}

	/**
	 * 
	 * @param myWord,
	 *            a string of the word passed in from the text-file.
	 * @return returns a boolean value indicating whether it is valid, that is
	 *         it contains at least one letter. The method splits the word into
	 *         an array of characters. It iterated through this array and check
	 *         whether ever word is a letter. Depending on the count it returns
	 *         true or false.
	 */
	public static boolean isValidWord(String myWord) {
		char[] myCharArray = myWord.toCharArray();
		int index = 0;
		boolean isAllNotAlpha = false;
		int alphaCount = 0;
		for (char c : myCharArray) {
			if (Character.isLetter(c)) {
				alphaCount += 1;
			}
		}
		if (alphaCount == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This the cleanWord method and it serves to clean the word based on
	 * whether there are word-connectors and delimeters. All of the possible
	 * combinations of delimeters are replaced with one delimeter, "--". Then
	 * the word is split according to that delimeter. Once the word is split
	 * every individual word is iterated over to look for any and all
	 * delimeters. If a delimeter exists and it is not a word connector, the
	 * string is essentially "split" into two strings. The way this works is if
	 * there is a a delimeter the current string is immediatley appeneded to the
	 * array list (the ends are first cleaned) and the pointer moves to the next
	 * letter.
	 * 
	 * @param myWord
	 *            the string word that is used.
	 * @return returns an arraylist of the words that could be potentially made
	 *         from splitting the word.
	 */
	public static ArrayList<String> cleanWord(String myWord) {
		ArrayList<String> myArrayStrings = new ArrayList<String>();
		myWord = myWord.toLowerCase();
		myWord = myWord.replaceAll("-_", "--");
		myWord = myWord.replaceAll("__", "--");
		myWord = myWord.replaceAll("''", "--");
		myWord = myWord.replaceAll("_-", "--");
		myWord = myWord.replaceAll("'-", "--");
		myWord = myWord.replaceAll("-'", "--");
		myWord = myWord.replaceAll("_'", "--");
		myWord = myWord.replaceAll("'_", "--");

		String[] myStringArray = myWord.split("--");
		for (String myString : myStringArray) {
			// System.out.println(myString);
			int pointer = 0;
			String myCleanString = "";
			/**
			 * You want to split the word based on the delimiters because they
			 * terminate the word.
			 */
			while (pointer < myString.length()) {
				if (Character.isLetter(myString.charAt(pointer)) || myString.charAt(pointer) == '-'
						|| myString.charAt(pointer) == '_'
						|| (Character.toString(myString.charAt(pointer)).equals("'"))) {
					myCleanString += myString.charAt(pointer);
					pointer++;

				}
				// if(pointer==0 &&
				// Character.isLetter(myString.charAt(pointer))==false){
				// pointer++;
				// }
				else {
					/**
					 * Add the current clean string into the arraylist. then set
					 * clean word to an empty string "" and then add the final
					 * string to the array. of course don't add the string if
					 * it's empty.
					 */
					// myArrayStrings.add(myCleanString);
					// myCleanString = "";
					if (myCleanString.isEmpty()) {
						pointer++;
					} else {
						myArrayStrings.add(cleanEnds(myCleanString));
						myCleanString = "";
						pointer++;
					}

				}

			}

			if (!(myCleanString.isEmpty())) {
				if (!(cleanEnds(myCleanString).isEmpty())) {
					myArrayStrings.add(cleanEnds(myCleanString));

				}

			}

		}
		return myArrayStrings;

	}

	/**
	 * This is the cleanEnds method and its purpose is to look at the beginning
	 * and end characters of the string to make sure that a word does not begin
	 * with a delimeter.
	 * 
	 * @param word
	 *            the word as a string.
	 * @return returns a cleaned string that has no delimeters at the beginning
	 *         or end.
	 */

	public static String cleanEnds(String word) {
		String cleaned = "";
		// if(isValidWord(word)==false){
		//
		// }
		while (Character.isLetter(word.charAt(0)) == false) {
			if (word.length() == 1) {
				return "";
			} else {
				word = word.substring(1);
			}
		}

		while (Character.isLetter(word.charAt(word.length() - 1)) == false) {
			word = word.substring(0, word.length() - 1);

		}
		return word;

	}

}
