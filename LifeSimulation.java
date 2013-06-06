import javax.swing.*;

class Simulation extends JPanel
{
  private static Grid myGrid;
  
  //Getters
  public static Grid getGrid(){return myGrid;}
  
  public static void main(String[] args){
  myGrid=new Grid(1,1);
  myGrid.getEcosystem(0,0);
  for(int i=0;i<10;i++)
    myGrid.getEcosystem(0,0).add(new Plant (myGrid.getEcosystem(0,0),"Fern",2));//Create a fern
  for(int i=0;i<1;i++)
    myGrid.getEcosystem(0,0).add(new Herbivore (myGrid.getEcosystem(0,0),"Bunny",5));//Create a fern
  
    System.out.println(myGrid.getEcosystem(0,0).manifest());
  
  }
}