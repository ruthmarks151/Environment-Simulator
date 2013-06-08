/**********************************************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class EcosystemEditor extends JPanel implements ActionListener{
  private Ecosystem edited;
  private ArrayList<PopulationRow> populationRows;
  private JButton saveButton;
  private JButton addButton;
  
  EcosystemEditor(Ecosystem toBeEdited){
    //Variable management        
    edited =toBeEdited;
    populationRows=new ArrayList<PopulationRow>();
    
    //Layout Buisness
    super.setPreferredSize(new Dimension(240,768));
    setLayout(new FlowLayout());
    
    loadPops();
    
    //Add 2 JButtons
    addButton=new JButton ("Add");
    saveButton=new JButton ("Save");
    addButton.addActionListener(this);
    saveButton.addActionListener(this);
    super.add(addButton);
    super.add(saveButton);  
    
    setVisible(true);}
  
  void loadPops(){
    String manifest=edited.manifest();//Gets the manifest from the ecosystem
    
    while (manifest.indexOf('\n')!=-1){//While there are still newlines in the string
      String line=manifest.substring(0,manifest.indexOf('\n'));//Get the first line of text  
      populationRows.add(new PopulationRow(line));//Add a population row to the arraylist
      manifest=manifest.substring(manifest.indexOf('\n')+1,manifest.length());//Remove the line that was just processed from the string
    }
    
    for(PopulationRow pr:populationRows){//Add every population row to the panel
      super.add(pr);
    }
  }
  
  //Changes the population in the Ecosystem
  private void populationChange(String secies,int change ){
    
  }
  
  //Action Event 
  public void actionPerformed(ActionEvent e){
    System.out.println("Action event");
    
    if (e.getSource().equals(saveButton)){
      for(PopulationRow pr:populationRows){
        int change;
        change=pr.getPopChange();
        if (change>0)
          edited.add(pr.getSpecies(),change);
        else
          edited.remove(pr.getSpecies(),Math.abs(change));
      }
    }
    System.out.println(edited.manifest());
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
        throw new NumberFormatException();
      
    } catch (NumberFormatException e){//If the user did not enter a valid int
      System.out.println("Number format!");
      population.setText(""+popInitial);//Reset the population field to the last valid value
      change=0;
    } 
    return change;
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