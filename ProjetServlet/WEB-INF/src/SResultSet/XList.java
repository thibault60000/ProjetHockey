package SResultSet;

/**This class may be used in place of Hashtable.*/
public class XList extends java.util.Hashtable implements java.io.Serializable
{

public XList ()
{
}

/**
* Get the total numbers of objects in the list.
*
* @return <code>int</code> the total items in the list.
* */
public int countItem ()
{
    return super.size () ;
}

/**
* An Iterator Object which may be used to iterate the list.
*
* @return java.util.Iterator Object
* */
public java.util.Iterator getIterator ()
{
    return this.values ().iterator () ;
}

/**
* This method add a new value with key in the list.
*
* @param key The key where the object is to be placed.
* @param value The value to be stored.
* @return <code>true</code> if the value is successfully inserted in the list;<code>false</code> otherwise.
* */
public boolean addItem ( Object key, Object value )
{
// if key already exist in the list
    if (super.containsKey(key)) {
      System.out.println("Key already exist..");
      return false;
    }
    else { // end of IF condition
      try {
        super.put(key, value);
        return true;
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
        return false;
      }
    }
}

/**
* This method replace an existing value with a new value in the list.
*
* @param oldkey The key of the object to be replaced.
* @param newvalue The new value of the Object.
* @return <code>true</code> if the value is successfully replaced in the list;<code>false</code> otherwise.
* */
public boolean replaceItem ( Object oldkey, Object newvalue )
{
    if (! (super.containsKey(oldkey))) {
      System.out.println("Key does not exist..");
      return false;
    }
    else {
      try {
        super.put(oldkey, newvalue);
        return true;
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
        return false;
      }
    }
}

/**
* This method returns a value from the list.
*
* @param key The key of the value to be searched.
* @return <code>Object</code>
* */
public Object searchItem ( Object key )
{
    try {
      return super.get(key);
    }
    catch (Exception ex) {
      System.out.println(ex.toString());
      return null;
    }
}

/**
* Delete a value from the list.
*
* @param key The key of the value to be deleted.
* @return <code>true</code> if the item is deleted successfully; <code>false</code> otherwise.
* */
public boolean deleteItem ( Object key )
{
    if (! (super.containsKey(key))) {
      System.out.println("Error Occured: Key does not exist");
      return false;
    }
    else {
      try {
        super.remove(key);
        return true;
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
        return false;
      }
    }
}

/**
* Test that if list is empty.
*
* @return <code>true</code> if the list is empty; <code>false</code> otherwise.
* */
public boolean isEmpty ()
{
    return super.isEmpty () ;
}

/**
* Removes all the vlaue from the list.
*
* @return <code>true</code> if the list is emptied successfully; <code>false</code> otherwise.
* */
public boolean clearList ()
{
    try {
      super.clear();
      return true;
    }
    catch (Exception ex) {
      System.out.print(ex.toString());
      return false;
    }
}
} //End of Class
