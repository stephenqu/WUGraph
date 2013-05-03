/* Entry.java */

/**
 * A class for dictionary entries.
 * 
 * DO NOT CHANGE THIS FILE. It is part of the interface of the Dictionary ADT.
 **/

public class Entry {

	protected Object key;
	protected Object value;

	/**
	 * This entry constructor creates an entry that consists of a key and a
	 * value. Used for Kruskal's algorithm
	 * 
	 * @param k
	 *            is an Object that represents the key
	 * @param v
	 *            is an Object that represents the value
	 */
	public Entry(Object k, Object v) {
		key = k;
		value = v;
	}

	public Entry() {
	}

	/**
	 * The method key() returns the key of the entry
	 * 
	 * @return Object which is the key of the entry
	 */
	public Object key() {
		return key;
	}

	/**
	 * The method value() returns the value of the given entry
	 * 
	 * @return Object which is the value of the entry
	 */
	public Object value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object) Overrides the default java
	 * equals method
	 * 
	 * @param other is an Object that "this" is comparing to
	 * 
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Object other) {
		if (other instanceof Entry) {
			return (this.key).equals(((Entry) other).key);
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode() Overrides the default java hash code method
	 *  
	 * @return integer that represents the hash code
	 */
	public int hashCode() {
		return this.key.hashCode();
	}

}