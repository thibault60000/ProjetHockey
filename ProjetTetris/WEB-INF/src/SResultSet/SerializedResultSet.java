package SResultSet;

/**A duplicate of java.sql.ResultSet which can be used in place of java.sql.ResultSet when using RMI.*/
public class SerializedResultSet implements java.io.Serializable {

  private XList lstColumnName = new XList();
  private java.util.LinkedList lstColumnData = new java.util.LinkedList();
  private int currentRecord = -1;
  private int totalRecords = 0;

public SerializedResultSet() {
    currentRecord = -1;
    totalRecords = 0;
}

/**
* Moves the cursor down one row from its current position in the result set.
* Initially the cursor is at the top of the first row.
*
* @return <code>true</code> if the new current row is valid;
* <code>false</code> otherwise.
* @throws Exception
* */
public boolean next() throws Exception {
    int totalRecords = recordCount();
    if (totalRecords == 0) {
      currentRecord = 0;
      return false;
    }
    if (currentRecord == -1) {
      currentRecord = 0;
      return true;
    }
    if (currentRecord < totalRecords && currentRecord >= 0 &&
        currentRecord != (totalRecords - 1)) {
      currentRecord++;
      return true;
    }
    return false;
}

/**
* Returns the total number of records in the resultset.
*
* @return <code>integer</code> the total number of records in the result set.
* */
public int recordCount() {
    try {
      java.util.LinkedList columnData = (java.util.LinkedList) lstColumnData.
          get(0);
      return columnData.size();
    }
    catch (Exception ex) {
      return 0;
    }
}

/**
* Positions the cursor at the first value of the result set.
*
* @return <code>true</code> if the first row is valid;
* <code>false</code> otherwise.
* @throws Exception
* */
public boolean first() throws Exception {
    currentRecord = -1;
    return true;
}

/**
* Positions the cursor at the last value of the table result set.
*
* @return <code>true</code> if the first row is valid;
* <code>false</code> otherwise.
* @throws Exception
* */
public boolean last() throws Exception {
    currentRecord = recordCount() - 1;
    return true;
}

/**
* Get the field value specifed by the column name and the row.
*
* @param columnName The name of the table column.
* @param row The row number int the column.
* @return An <code>Object</code> containing the data.
* @throws Exception if the row number is greater than the size of the
* column data or given the column name is invalid.
* */
private Object getField(String columnName, int row) throws Exception {
    if (row == -1) {
      currentRecord++;
      row = currentRecord;
    }
    int iColumnIndex = Integer.valueOf( (String) lstColumnName.searchItem(
        columnName.toUpperCase())).intValue();
    java.util.LinkedList columnData = ( (java.util.LinkedList) lstColumnData.
                                       get(iColumnIndex - 1));
    return columnData.get(row);
}

/**
* Get a String value specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>String</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public String getString(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return null;
    }
    else {
      return obj.toString();
    }
}

/**
* Get Date and Time specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>String</code> containing the date and time.
* @throws Exception if an invalid column Name is given.
* */
public String getDateTime(String columnName) throws Exception {
    java.sql.Timestamp dateTime = (java.sql.Timestamp)this.getField(columnName,
        currentRecord);
    if (dateTime != null) {
      java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
          "MMM-dd-yyyy HH:mm:ss");
      return dateFormat.format(new java.util.Date(dateTime.getTime()));
    }
    else {
      return null;
    }
}

/**
* Get Date specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>String</code> containing the date.
* @throws Exception if an invalid column Name is given.
* */
public String getDate(String columnName) throws Exception {
    java.sql.Timestamp dateTime = (java.sql.Timestamp)this.getField(columnName,
        currentRecord);
    if (dateTime != null) {
      java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MMM-dd-yyyy");
      return dateFormat.format(new java.util.Date(dateTime.getTime()));
    }
    else {
      return null;
    }
}

/**
* Get Time specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>String</code> containing the time.
* @throws Exception if an invalid column Name is given.
* */
public String getTime(String columnName) throws Exception {
    java.sql.Timestamp dateTime = (java.sql.Timestamp)this.getField(columnName,
        currentRecord);
    if (dateTime != null) {
      java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
          "HH:mm:ss");
      return dateFormat.format(new java.util.Date(dateTime.getTime()));
    }
    else {
      return null;
    }
}

/**
* Get an Object specified by the column name.
*
* @param columnName The name of the table column.
* @return An <code>Object</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public Object getObject(String columnName) throws Exception {
    return this.getField(columnName, currentRecord);
}

/**
* Get an integer value specified by the column name.
*
* @param columnName The name of the table column.
* @return An <code>integer</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public int getInteger(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return 0;
    }
    else {
      return ( (java.math.BigDecimal) obj).intValue();
    }
}

/**
* Get a long value specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>long</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public long getLong(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return 0;
    }
    else {
      try {
        return ( (java.math.BigDecimal) obj).longValue();
      }
      catch (Exception ex) {
        try {
          return ( (Long) obj).longValue();
        }
        catch (Exception ex1) {
          return ( (Integer) obj).longValue();
        }
      }
    }
}

/**
* Get a double value specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>double</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public double getDouble(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return 0;
    }
    else {
      try {
        return ( (java.math.BigDecimal) obj).doubleValue();
      }
      catch (Exception ex) {
        try {
          double ret = ( (Double) obj).doubleValue();
          return ret;
        }
        catch (Exception e) {
          return 0.0;
        }
      }
    }
}


/**
* Get a java.sql.Timestamp Object specified by the column name.
*
* @param columnName The name of the table column.
* @return An <code>java.sql.Timestamp Object</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public java.sql.Timestamp getTimestamp(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return null;
    }
    else {
      return ( (java.sql.Timestamp) obj);
    }
}

/**
* Get a double value specified by the column name.
*
* @param columnName The name of the table column.
* @return A <code>double</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public double getDecimal(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return 0;
    }
    else {
      return ( (Double) obj).doubleValue();
    }
}

/**
* Get a integer value specified by the column name.
*
* @param columnName The name of the table column.
* @return An <code>integer</code> containing the data.
* @throws Exception if an invalid column Name is given.
* */
public int getInt(String columnName) throws Exception {
    Object obj = this.getField(columnName, currentRecord);
    if (obj == null) {
      return 0;
    }
    else {
      try {
        return ( (Integer) obj).intValue();
      }
      catch (Exception ex) {
        try {
          return ( (java.math.BigDecimal) obj).intValue();
        }
        catch (Exception ex1) {
          return ( (Long) obj).intValue();
        }
      }
    }
}


/**
* Adds column data to the list.
*
* @param ColumnNumber The index of the column.
* @param newValue The new value to be added.
* */
public void addColumnData(int ColumnNumber, Object newValue) {
    ((java.util.LinkedList) lstColumnData.get(ColumnNumber - 1)).addLast(newValue);
}

/**
* Adds column name to the list.
*
* @param columnName The name of the column.
* @param columnSequence The index of the column.
* */
public void addColumn(String columnName, int columnSequence) {
    lstColumnName.addItem(columnName.toUpperCase(),
                          String.valueOf(columnSequence));
    java.util.LinkedList linkData = new java.util.LinkedList();
    lstColumnData.addLast(linkData);
    ( (java.util.LinkedList) lstColumnData.get(columnSequence - 1)).clear();
  }
}
