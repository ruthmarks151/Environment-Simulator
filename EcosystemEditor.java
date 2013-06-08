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
    
    addButton=new JButton ("Add");
    saveButton=new JButton ("Save");
    addButton.addActionListener(this);
    saveButton.addActionListener(this);
    super.add(addButton);
    super.add(saveButton);  
    
    setVisible(true);}
  
  void loadPops(){
    String manifest=edited.manifest();
    
    while (manifest.indexOf('\n')!=-1){
      String line=manifest.substring(0,manifest.indexOf('\n'));   
      populationRows.add(new PopulationRow(line));
      manifest=manifest.substring(manifest.indexOf('\n')+1,manifest.length());
    }
    
    for(PopulationRow pr:populationRows){
      super.add(pr);
    }
  }
  
  
  public void actionPerformed(ActionEvent e){
    System.out.println("Action event");
    
    if (e.getSource().equals(saveButton)){
      for(PopulationRow pr:populationRows){
        pr.getPopChange();
        
      }
    }
  }
}

/**********************************************************************************************************************/
class PopulationRow extends JPanel {
  private JLabel species;
  private JTextField population;
  private int popInitial;
  
  public int getPopChange(){
    int change;
    try{  
      change = Integer.parseInt(population.getText())-popInitial;
      popInitial=Integer.parseInt(population.getText());
    } catch (NumberFormatException e){
      population.setText(""+popInitial);
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