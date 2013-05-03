package graph;

public class Edge2 {
	private Object vertex1;
	private Object vertex2;
	private int weight;

	/**
	 * Constructor for the Edge2 class that takes in vertex1, vertex2, and 
	 * weight as parameters.
	 */
	public Edge2(Object vertex1, Object vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	/**
	 * vertex1() returns the first vertex of the Edge2
	 */
	public Object vertex1() {
		return this.vertex1;
	}

	/**
	 * vertex2() returns the second vertex of the Edge2
	 */
	public Object vertex2() {
		return this.vertex2;
	}

	/**
	 * weight() returns the weight of the Edge2
	 */
	public int weight() {
		return this.weight;
	}
	
	/**
	 * isSameEdge2(Object Edge21, Object Edge22) takes in Edge2s and checks if their respective vertexes are equal. 
	 * If so, isSameEdge2(Object Edge21, Object Edge22) returns true, and if not, it returns false.
	 */
	public boolean isSameEdge2(Object Edge21, Object Edge22) {
		if ((Edge21 == this.vertex1 && Edge22 == this.vertex2)
				|| (Edge21 == this.vertex2 && Edge22 == this.vertex1)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * compareTo(int pivot) takes in an int (the pivot), and compares the weight of the Edge2 to the pivot.
	 * If the weight of the Edge2 is greater, a positive number is returned, and if not, a negative number 
	 * is returned. The Values 1 and -1 are arbitrarily chosen.
	 */
	public int compareTo(int pivot) {
		if (this.weight > pivot) {
			return 1;
		} else {
			return -1;
		}
	}

}
