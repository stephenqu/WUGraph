/* Entry.java */
 
/**
 *  A class for dictionary entries.
 *
 *  DO NOT CHANGE THIS FILE.  It is part of the interface of the
 *  Dictionary ADT.
 **/
 
public class Entry {
 
  protected Object key;
  protected Object value;
 
    public Entry(Object k, Object v){
	key = k;
	value = v;
    }
    
    public Entry(){
    }

  public Object key() {
    return key;
  }
 
  public Object value() {
    return value;
  }
    
    public boolean equals(Object other){
	if (other instanceof Entry){
	    return (this.key).equals(((Entry) other).key);
	}else{
	    return false;
	}
    }

    public int hashCode(){
	return this.key.hashCode();
    }
 
}