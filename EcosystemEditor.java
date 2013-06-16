/**********************************************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class EcosystemEditor extends JPanel implements ActionListener{
  private Ecosystem edited;
  private ArrayList<PopulationRow> populationRows;
  private JButton saveButton;
  
  private SpeciesAdder sa;
  private MainWindow parent;
  
  public Ecosystem getEditted(){return edited;}
  
  public void replace (){
    System.out.println("Requesting Replacement");
    parent.refresh();
  }
  
  EcosystemEditor(Ecosystem toBeEdited,MainWindow creator){
    //Variable management        
    edited =toBeEdited;
    parent=creator;
    populationRows=new ArrayList<PopulationRow>();
    
    //Layout Buisness
    super.setPreferredSize(new Dimension(200,250));
    setLayout(new FlowLayout());
    
    loadPops();
    
    //Add 2 JButtons
    saveButton=new JButton ("Save");
    
    saveButton.addActionListener(this);
    //Declare a species adder
    sa = new SpeciesAdder(this);
    
    super.add(sa);//add it
    
    super.add(saveButton);  
    
    setVisible(true);}
  
  public void loadPops(){
    String manifest=edited.manifest();//Gets the manifest from the ecosystem
    
    while (manifest.indexOf('\n')!=-1){//While there are still newlines in the string
      String line=manifest.substring(0,manifest.indexOf('\n'));//Get the first line of text  
      populationRows.add(new PopulationRow(line));//Add a population row to the arraylist
      //Remove the line that was just processed from the string
      manifest=manifest.substring(manifest.indexOf('\n')+1,manifest.length());
    }
    
    for(PopulationRow pr:populationRows){//Add every population row to the panel
      super.add(pr);
    }
  }
  
  //Changes the population in the Ecosystem
  public void populationChange(String secies,int change ){
    if (change>0)
      edited.add(secies,change);
    else
      edited.remove(secies,Math.abs(change));
  }
  
  private void checkForPopChanges(){
    for(PopulationRow pr:populationRows){
      int change;
      change=pr.getPopChange();
      populationChange(pr.getSpecies(),change);
      
    }
    for(PopulationRow pr:populationRows){
      if (pr.noPop())
        replace();
    }
  }
  //Action Event 
  public void actionPerformed(ActionEvent e){
    //Adding from the row
    
    String chosenSpecies=sa.species.getSelectedItem().toString();
    int chosenPop;
    
    try{  
      if (Integer.parseInt(sa.population.getText())>=0){      
        chosenPop=Integer.parseInt(sa.population.getText());
        populationChange(chosenSpecies,chosenPop);
        replace();
      }else 
        throw new NumberFormatException();
    }catch (NumberFormatException ex){//If the user did not enter a valid int
      System.out.println("Number format!");
      sa.population.setText(""+0);//Reset the population field to the last valid value
    }
    
    //Check for pop changes
    checkForPopChanges();    
    
  }
  
  
  
}


/**********************************************************************************************************************/
class PopulationRow extends JPanel {
  private JLabel species;
  private JTextField population;
  private int popInitial;
  
  public String getSpecies(){return species.getText();}
  
  public int getPopChange(){
    int change;
    try{  
      if (Integer.parseInt(population.getText())>=0){      
        change = Integer.parseInt(population.getText())-popInitial;//Get the change from the last initial value
        popInitial=Integer.parseInt(population.getText());//Set the initial
      }else 
        throw new NumberFormatException();} 
    catch (NumberFormatException e){//If the user did not enter a valid int
      System.out.println("Number format!");
      population.setText(""+popInitial);//Reset the population field to the last valid value
      change=0;
    } 
    return change;
  }    
  
  public boolean noPop (){
    if(population.getText().equals("0"))
      return true;
    return false;
  }
  
  PopulationRow(String line){
    String name=line.substring(0,(line.indexOf(":")));
    String number= line.substring((line.indexOf(":")+1),line.length());
    popInitial=Integer.parseInt(number);
    
    setLayout(new BorderLayout());
    super.setPreferredSize(new Dimension(200,30));
    species=new JLabel(name);
    population=new JTextField(popInitial+"",3);
    
    super.add(species,BorderLayout.WEST);
    super.add(population,BorderLayout.EAST);
    setVisible(true);
  }
  
  
}

/**********************************************************************************************************************/
class SpeciesAdder extends JPanel{
  public JComboBox species;
  public JTextField population;
  private EcosystemEditor parent;
  
  SpeciesAdder(EcosystemEditor editor){   
    super();
    parent=editor;
    
    setLayout(new BorderLayout());
    super.setPreferredSize(new Dimension(200,30));
    
    species=new JComboBox(SpeciesTable.getOrganisms());
    
    for (String str:SpeciesTable.getOrganisms()){
      
    }
    
    population=new JTextField(0+"",3);
    
    
    super.add(species,BorderLayout.WEST);
    super.add(population,BorderLayout.EAST);
    
    super.setVisible(true);
    
  }  
  
  
}
