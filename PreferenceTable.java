/**********************************************************************************************************************/
class PreferenceTable{
  private String [] prefered;
  private int [] preference;
  
  PreferenceTable(){
    prefered=new String[0];
    preference=new int[0];
  }
  public int getPrefFor(String thing){
    for (int i=0;i<prefered.length;i++){
      if(prefered[i].equals(thing)){
        return preference[i];}
      
    }
    return 0;
  }
  
  public void add(String line){
    String name=line.substring(0,(line.indexOf(":")));
    String number= line.substring((line.indexOf(":")+1),line.length());
    int value=Integer.parseInt(number);
        addString(name);
        addInt(value);
  }
  
  private void addString(String name){
    String[]newTable=new String[prefered.length+1];
    
    for(int i=0;i<prefered.length;i++){//Standard value copying for loop
      newTable[i]=prefered[i];
    }
    
    newTable[newTable.length-1]=name;
    
    prefered=newTable;
  }
  
  private void addInt(int value){
    int[]newTable=new int[preference.length+1];
    
    for(int i=0;i<preference.length;i++){//Standard value copying for loop
      newTable[i]=preference[i];
    }
    
    newTable[newTable.length-1]=value;
    
    preference=newTable;
  }
}