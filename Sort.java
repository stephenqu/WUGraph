import graph.Edge;
import java.util.Random;

public class Sort {
	
	static final Random RND = new Random();

	/**
	   *  Method to swap two Objects in an array.
	   *  @param a an array of Objects.
	   *  @param index1 the index of the first int to be swapped.
	   *  @param index2 the index of the second int to be swapped.
	   **/
	private static void swap(Object[] array, int i, int j) {
		Object tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	/**
	 * partition(Edge[] array, int begin, int end) takes in an Edge array, and two ints: the beginning and end. If index
	 * or end is null, subtract the index/end int until it is not null. Partition sorts the Edge array and returns a 
	 * new int index
	 */
	private static int partition(Edge[] array, int begin, int end) {
		int index = begin + RND.nextInt(end - begin + 1);
		while (array[index] == null) {
			index--;
		}
		int pivot = array[index].weight();
		while (array[end] == null) {
			end --;
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
	   *  This is a generic Quick Sort algorithm. It handles arrays that are already sorted, 
	   *  and arrays with duplicate keys.
	   *  @param a       an integer array
	   *  @param lo0     left boundary of array partition
	   *  @param hi0     right boundary of array partition
	   **/
	private static void qsort(Edge[] array, int begin, int end) {
		if (end > begin) {
			int index = partition(array, begin, end);
			qsort(array, begin, index - 1);
			qsort(array, index + 1, end);
		}
	}

	 /**
	   *  Quicksort algorithm.
	   *  @param a an array of int items.
	   **/
	public static void sort(Edge[] array) {
		qsort(array, 0, array.length - 1);
	}
	
}
