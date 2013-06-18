/**********************************************************************************************************************/
class PreferenceTable{
  private String [] prefered;
  private int [] preference;
  
  PreferenceTable(){
    // create empty preference table with empty preference lists
    prefered=new String[0];
    preference=new int[0];
  }
  public int getPrefFor(String thing){
    // iterate through array
    for (int i=0;i<prefered.length;i++){
      // if you find the thing you want the preference for
      if(prefered[i].equals(thing)){
        // return the integer preference value
        return preference[i];}
      
    }
    // if not found, return 0
    return 0;
  }
  
  public void add(String line){
    // separate the string into the name part before the colon
    String name=line.substring(0,(line.indexOf(":")));
    // separate the number part after the colon
    String number= line.substring((line.indexOf(":")+1),line.length());
    // parse the number string into an int
    int value=Integer.parseInt(number);
    // add the string to the table
    addString(name);
    // add the int to the table
    addInt(value);
  }
  
  private void addString(String name){
    // make new string table one longer
    String[]newTable=new String[prefered.length+1];
    
    // iterate through array
    for(int i=0;i<prefered.length;i++){
      // copy each value over
      newTable[i]=prefered[i];
    }
    
    // put the string to be added in the last slot
    newTable[newTable.length-1]=name;
    
    // point the string table reference to the new table
    prefered=newTable;
  }
  
  private void addInt(int value){
    // make new int table one longer
    int[]newTable=new int[preference.length+1];
    
    // iterate through array
    for(int i=0;i<preference.length;i++){
      // copy each value over
      newTable[i]=preference[i];
    }
    
    // put the int to be added into the last slot
    newTable[newTable.length-1]=value;
    
    // point the string table reference to the new table
    preference=newTable;
  }
}