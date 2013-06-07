class PreferenceTable{
  private String [] prefered;
  private int [] preference;
  
  PreferenceTable(){
  prefered=new String[0];
    preference=new int[0];
  }
  public void add(String line){
    System.out.println("Parseing "+line);
    String name=line.substring(0,(line.indexOf(" ")-1));
    String number= line.substring((line.indexOf(" ")+1),line.length());
    int value=Integer.parseInt(number);
  }
  
  private void addString(String name){
    String[]newTable=new String[prefered.length+1];
    
    for(int i=0;i<prefered.length;i++){//Standard value copying for loop
      newTable[i]=prefered[i];
    }
    
    newTable[newTable.length]=name;
    
    prefered=newTable;
  }
  
  private void addInt(int value){
    int[]newTable=new int[preference.length+1];
    
    for(int i=0;i<preference.length;i++){//Standard value copying for loop
      newTable[i]=preference[i];
    }
    
    newTable[newTable.length]=value;
    
    preference=newTable;
  }
}