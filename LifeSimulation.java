import javax.swing.*;

class Simulation extends JPanel
{
  private static Grid myGrid;
  
  //Getters
  public static Grid getGrid(){return myGrid;}
  
  public static void main(String[] args){
  myGrid=new Grid(1,1);
  myGrid.getEcosystem(0,0);
  myGrid.getEcosystem(0,0).add(new Plant (myGrid.getEcosystem(0,0),"Fern"));//Create a fern
    myGrid.getEcosystem(0,0).add(new Plant (myGrid.getEcosystem(0,0),"Fern"));//Create a fern
      myGrid.getEcosystem(0,0).add(new Plant (myGrid.getEcosystem(0,0),"Fern"));//Create a fern
    myGrid.getEcosystem(0,0).add(new Carnivore (myGrid.getEcosystem(0,0),"Kitty"));//Create a fern
  
    System.out.println(myGrid.getEcosystem(0,0).manifest());
  
  }
}