/* HashTableChained.java */

import list.*;

/**
 *  HashTableChained implements a hash table with chaining.
 *  All objects used must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 **/

public class HashTableChained{

  /**
   *  Place any data fields here.
   **/
    private List[] table;
    private int size;


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
      double load_factor = 0.75;
      int n = (int) ((double) (sizeEstimate) / load_factor);
      size = 0;
      table = new DList[computePrime(n)];
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/ 

  public HashTableChained() {
      table = new DList[101];
      size = 0;
  }

  /**
   *  Computes the smallest prime greater than or equal to n
   *
   *  @param n is the number to compute the next prime
   *  @return the smallest prime greater than n, or n if n is prime
   **/
    
  private int computePrime(int n){
    //from number theory, a prime of size int is guaranteed in this bound
    for (int i = n; i <= n+320; i++){
      if (isPrime(i)){
	  return i;
      }
    }
    return n;
  }

  /**
   *  Computes whether this number is prime.
   *
   *  @param n is the number whose primality is to be computed
   *  @return true if this number is prime;
   **/

    private boolean isPrime(int n){
	if (n == 2){
	    return true;
	}
	if (n < 2 || (n%2 == 0)){
	    return false;
	}
	for (int i=3; i*i <= n;i += 2){
	    if (n%i == 0){
		return false;
	    }
	}
	return true;
    }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
      int len = table.length;
      int p = computePrime(Math.abs(len*100)); //In case len is some REALLY large number ie close to 2^31
      return mod(mod(13*code+9551,p),len);
  }

  /**
   *  Computes a mod b. b must be greater than 0
   *
   *  @params a,b
   *  @return a mod b
   **/
    
  public int mod(int a, int b){
      if (a<0){
	  a += (Integer.MAX_VALUE + a)/b *b;
      }
      return a%b;
  }

  /** 
   *  Returns the number of objects stored in the table.
   *  @return number of entries in the table.
   **/

  public int size() {
      return size;
  }

  /** 
   *  Tests if the table is empty.
   *
   *  @return true if the table has no objects; false otherwise.
   **/

  public boolean isEmpty() {
      return size==0;
  }

  /**
   *  Inserts this Object into the hash table.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param item the object to be inserted
   **/

  public void insert(Object item) {
      int index = compFunction(item.hashCode());
      size++;
      table[index].insertBack(item);
  }

  /** 
   *  Search for the specified Object
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param item the search item.
   *  @return the item if it is in the hash table, or null if item is not in
   *          the table.
   **/

  public Object find(Object item) {
      int index = compFunction(item.hashCode());
      DList list = (DList) table[index];
      for (DListNode node: list){
	  try{
	      if (item == node.item()){
		  return node.item();
	      }
	  }catch (InvalidNodeException e){
	      System.err.print(e);
	      e.printStackTrace(); //This should never happen
	  }
      }
    return null;
  }

  /** 
   *  Remove the specified object. If such an object is found,
   *  remove it from the table and return it; otherwise return null.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param item the item to be removed.
   *  @return the item if it is in the hash table, or null if item is not in
   *          the table.
   */

  public Object remove(Object item) {
      int index = compFunction(item.hashCode());
      DList list = (DList) table[index];
      for (DListNode node: list){
	  try{
	      if (item == node.item()){
		  size--;
		  Object removed = node.item();
		  node.remove();
		  return removed;
	      }
	  }catch (InvalidNodeException e){
	      System.err.print(e);
	      e.printStackTrace(); //This should never happen
	  }
      }
    return null;
  }

  /**
   *  Remove all items from the table.
   */
  public void makeEmpty() {
      table = new DList[table.length];
      size = 0;
  }

}
