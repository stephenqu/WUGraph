import graph.*;
import java.util.Random;

public class Sort {

	static final Random RND = new Random();

	/**
	 * Method to swap two Objects in an array.
	 * 
	 * @param a
	 *            an array of Objects.
	 * @param i
	 *            the index of the first int to be swapped.
	 * @param j
	 *            the index of the second int to be swapped.
	 **/
	private static void swap(Object[] array, int i, int j) {
		Object tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	/**
	 * Partition sorts the Edge array and returns a new int index. If index or
	 * end is null, subtract the index/end int until it is not null.
	 * 
	 * @param array
	 *            is an Edge array
	 * @param begin
	 *            is an int that represents the beginning of the array
	 * @param end
	 *            is an int that represents the end of an array
	 * @return
	 */
	private static int partition(Edge2[] array, int begin, int end) {
		int index = begin + RND.nextInt(end - begin + 1);
		while (array[index] == null) {
			index--;
		}
		int pivot = array[index].weight();
		while (array[end] == null) {
			end--;
		}
		swap(array, index, end);
		for (int i = index = begin; i < end; ++i) {
			if (array[i].compareTo(pivot) <= 0) {
				swap(array, index++, i);
			}
		}
		swap(array, index, end);
		return (index);
	}

	/**
	 * This is a generic Quick Sort algorithm. It handles arrays that are
	 * already sorted, and arrays with duplicate keys.
	 * 
	 * @param array
	 *            an integer array
	 * @param begin
	 *            left boundary of array partition
	 * @param end
	 *            right boundary of array partition
	 **/
	private static void qsort(Edge2[] array, int begin, int end) {
		if (end > begin) {
			int index = partition(array, begin, end);
			qsort(array, begin, index - 1);
			qsort(array, index + 1, end);
		}
	}

	/**
	 * Quicksort algorithm.
	 * 
	 * @param array
	 *            an array of int items.
	 **/
	public static void sort(Edge2[] array) {
		qsort(array, 0, array.length - 1);
	}

}
