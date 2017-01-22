import java.util.*;

/**
 * @author NadiraDewji This is a static class that implements mergesort
 *         algorithm.
 */

public class MergeSort {
	/**
	 * This method takes a hashmap and a comparator and creates an array and
	 * copies the values in the the hashmap into the array. the private
	 * recMegeSort method is then called.
	 * 
	 * @param myHashMap
	 *            a hasmap storing strings as keys and words as values
	 * @param myComparator
	 *            a comparator to indicate how sorting should be performed
	 * @return
	 */
	public static Word[] sort(HashMap<String, Word> myHashMap, Comparator<Word> myComparator) {
		Word[] myWordArray = new Word[myHashMap.size()];
		int index = 0;
		for (Word myKey : myHashMap.values()) {
			myWordArray[index] = myKey;
			index++;
		}
		recMergeSort(myWordArray, 0, myWordArray.length - 1, myComparator);
		return myWordArray;
	}

	/**
	 * This is the private recMegeSort method and it sorts the array.
	 * 
	 * @param myArray
	 *            an array to sort
	 * @param leftIndex
	 *            the starting index of the array
	 * @param rightIndex
	 *            the right index of the array
	 * @param c
	 *            the comparator to which to compare the elements
	 */
	private static void recMergeSort(Word[] myArray, int leftIndex, int rightIndex, Comparator<Word> c) {
		/**
		 * If left is equal to right then you have a one element array you
		 * cannot sort so return.
		 */
		if (leftIndex == rightIndex) {
			return;

		}
		/**
		 * calculate the middle of the array
		 */
		int mid = (rightIndex + leftIndex) / 2;
		/**
		 * call merge sort again with the different halves of the array using
		 * the mid as the new left and right.
		 */
		recMergeSort(myArray, leftIndex, mid, c);
		recMergeSort(myArray, mid + 1, rightIndex, c);
		/**
		 * call the merge method once you return two arrays and merge them
		 */
		merge(myArray, leftIndex, mid, mid + 1, rightIndex, c);
	}

	/**
	 * this is the private merge method that takes 'two arrays' and merges them
	 * into one.
	 * 
	 * @param myArray
	 *            the array we are accessing
	 * @param firstLeft
	 *            the index of the left of the first array
	 * @param lastLeft
	 *            the index of the right of the first array
	 * @param firstRight
	 *            the index of the left index of the second array
	 * @param lastRight
	 *            the index of the right most index of the secon array
	 * @param c
	 *            the comparator
	 * @return
	 */
	private static Word[] merge(Word[] myArray, int firstLeft, int lastLeft, int firstRight, int lastRight,
			Comparator<Word> c) {
		/**
		 * create a temporary array to hold the information once it is merged
		 */
		Word[] temp = new Word[lastRight - firstLeft + 1];
		int leftIndex = firstLeft;
		int rightIndex = firstRight;
		int index = 0;
		/**
		 * While the left index of your new array is less than the indext of the
		 * last index of your left arrayand your right index is less than the
		 * index of your last index in your right array you continue to compare
		 * the values if the values in the left array are smaller than the right
		 * continue appending it to temp, otherwise use the right value.
		 */
		while (leftIndex <= lastLeft && rightIndex <= lastRight) {
			if (c.compare(myArray[leftIndex], myArray[rightIndex]) < 0) {
				temp[index] = myArray[leftIndex];
				leftIndex++;
			} else {
				temp[index] = myArray[rightIndex];
				rightIndex++;
			}
			index++;
		}
		/**
		 * Now copy the remaining elements into the temp
		 */
		while (leftIndex <= lastLeft) {
			temp[index] = myArray[leftIndex];
			leftIndex++;
			index++;

		}
		while (rightIndex <= lastRight) {
			temp[index] = myArray[rightIndex];
			rightIndex++;
			index++;
		}

		// for(int i = 0; i <temp.length; i++){
		// System.out.println(temp[i]);
		// }
		/**
		 * now copy everything into the original array
		 */
		for (int i = firstLeft; i < temp.length + firstLeft; i++) {
			myArray[i] = temp[i - firstLeft];
		}
		return myArray;

	}

}
