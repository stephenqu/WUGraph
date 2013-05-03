/* HashTable2.java */
 
package dict;
import list.*;
 
/**
 *  HashTable2 implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTable2 class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  This HashTable2 has been modified from hw6 to include resizing.
 *
 **/
 
public class HashTable2 implements Dictionary {
 
  /**
   *  Place any data fields here.
   **/
   protected List[] table;
   protected int size = 0;
   protected boolean shouldResize;
 
 
  /** 
   *  Construct a new empty hash table with a default size.
   **/
  public HashTable2() {
    table = new List[7];
    shouldResize = true;
  }
    
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
 
  public HashTable2(int sizeEstimate) {
    // start = reciprical of (desiredLoadFactor * sizeEstimate)
    // 5/4 ----> load factor about .8
    // 11/8 ----> load factor about .725
    // 13/8 ----> load factor about .6
    int start = sizeEstimate * 13 / 8;
    int tableSize = findPrime(start);
     
    table = new List[tableSize];
    shouldResize = false;
  }  
   
  // used for resizing the table
  private HashTable2(int length, boolean resize) {
    table = new List[length];
    shouldResize = resize;
  }
   
  /** 
   *  Calculates a prime number based on the current loadFactor
   **/
  private int findPrime() {
    if (loadFactor() >= .75) {
      return findPrime(table.length * 2);
    }
    if (loadFactor() <= .25) {
      return findPrime(table.length / 2);
    }
    return findPrime(this.size);
  }
   
   
  /** 
   *  Calculates a prime number based on the desired table length
   **/
  private int findPrime(int tableLength) {
    if (size < 5) {
      return 7;
    }
   
    int length = tableLength * 3/2;
    boolean[] primeArray = new boolean[length];
     
    // fill array with "true" values from index 2 to the end
    for (int x = 2; x < primeArray.length; x++) {
      primeArray[x] = true;
    }
     
    // Sieve of Eratosthenes
    for (int x = 2; x <= Math.sqrt(length); x++) {
      if (primeArray[x]) {
        for (int j = 1; (j * x) < primeArray.length; j++) {
          primeArray[j * x] = false;
        }
      }
    }
     
    // return a prime number
    int start = tableLength;
     
    for (int current = start; current < primeArray.length; current++) {
      if (primeArray[current]) {
        return current;
      }
    }
     
    // if no prime number is found between 5/8 array length and the end of the array,
    // go backwards
    for (int current = start; current > 0; current--) {
      if (current < primeArray.length && primeArray[current]) {
        return current;
      }
    }
     
    // it should not get here...
    return 7;
  }
 
  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/
 
  int compFunction(int code) {
    int length = table.length;
    int comp = ((53*code + 7) % 86028157) % length;
    return Math.abs(comp);
  }
 
  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/
 
  public int size() {
    return size;
  }
 
  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/
 
  public boolean isEmpty() {
    return size == 0;
  }
   
  private double loadFactor() {
    return (double) size / table.length;
  }
 
  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/
 
  public Entry insert(Object key, Object value) {
     
    // check the load factor and resize accordingly
    if (shouldResize) {
      resize();
    }
   
    // add Entry to hashtable
    Entry e = new Entry();
    e.key = key;
    e.value = value;
     
    List l;
    int index = compFunction(key.hashCode());
     
    if (table[index] == null) {
      l = new SList();
      table[index] = l;
    } else {
      l = table[index];
    }
    l.insertFront(e);
    size++;
    return e;
  }
   
   
  /**
   *  Resizes the table if the load factor exceeds .75 or goes below .25
   *  Either doubles the size or halves it.
   **/
  private void resize() {
    if (loadFactor() < .75 && loadFactor() > .25 || size <= 5) {
      return;
    }
     
    /*
    System.out.println("Resizing!   current size: " + size +
                                ", current table: " + table.length +
                                ", load factor: " + loadFactor()        );
    */
     
    HashTable2 newTable = new HashTable2(findPrime(), false);
     
    for (List l: table) {
      try {
        ListNode cur = l.front();
        for (int i = l.length(); i > 0; i-- ) {
          Entry curEntry = ((Entry) cur.item());
          Object curKey = curEntry.key();
          newTable.insert(curKey, curEntry.value());
          cur = cur.next();
        }
      } catch (InvalidNodeException e) {
        // don't do anything, because there's something wrong with the List code
      } catch (NullPointerException e) {
        // don't do anything, because we just want the loop to continue with the next index
      }  
    }
     
    assert this.size == newTable.size;
    this.table = newTable.table;    
  }
 
  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
 
  public Entry find(Object key) {
    int index = compFunction(key.hashCode());
     
    // if index is empty
    if (table[index] == null) {
      return null;
    }
     
    // otherwise a list is in the index
    try {
      List l = table[index];
      ListNode cur = l.front();
      for (int i = l.length(); i > 0; i-- ) {
        Entry curEntry = ((Entry) cur.item());
        Object curKey = curEntry.key();
        if (curKey.equals(key)) {
          return curEntry;
        }
        cur = cur.next();
      }
    } catch (InvalidNodeException e) {
      // don't do anything, because there's something wrong with the List code
    }    
    return null;    
  }
 
  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */
 
  public Entry remove(Object key) {
    // if entry is not found (key.equals()), return null
    int index = compFunction(key.hashCode());
     
    // if index is empty, was not found
    if (table[index] == null) {
      return null;
    }
     
    List l = table[index];
    ListNode cur = l.front();
    try {
      for (int i = l.length(); i > 0; i-- ) {
        Entry curEntry = ((Entry) cur.item());
        Object curKey = curEntry.key();
        if (curKey.equals(key)) {
          // if entry is found, remove entry and return it
          size --;
          cur.remove();
          return curEntry;
        }
        cur = cur.next();
      }
    } catch (InvalidNodeException e) {
      // don't do anything, because there's something wrong with the List code
    }    
     
    // was not found
    return null;
  }
 
  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for (int x = 0; x < table.length; x++ ){
      table[x] = null;
    }
    size = 0;
  }
 
  public int printTableAndCountCollisions() {
    int collisions = 0;
    int n = 0;
    int i;
    int N = table.length;
    for (i = 0; i < N; i++) {
      System.out.print(i + "\t");
      if (table[i] != null) {
        int len = table[i].length();
        n+= len;
        for (int l = 0; l < len; l++) {
          System.out.print("* ");
          if (l > 0) {
            collisions++;
          }
        }     
      }
      System.out.println();
    }
    double expCol = n - N + N * Math.pow(1 - 1 / (double) N, n);
    System.out.println("Expected collisions: " + expCol);
    return collisions;
  }
   
  public static void main(String[] args) {
    HashTable2.findPrimeTest();
  }
  public static void findPrimeTest() {
    HashTable2 t = new HashTable2();
    for (int i = 100; i < 1000; i = i + 100) {
      int p = t.findPrime(i * 13 / 8);
      double load_factor = i / (double) p;
      System.out.println("size estimate: " + i + " prime array: " + p + " load factor: " + load_factor);
    }   
  }
}