/**********************************************************************************************************************/
class EcosystemEditor extends JPanel{
  
  
  
}

class PopulationRow extends JPanel {
  private JLabel species;
  private JTextField population;
  
  PopulationRow(String line){
    String name=line.substring(0,(line.indexOf(":")-1));
    String number= line.substring((line.indexOf(":")+1),line.length());
    int pop=Integer.parseInt(number);
    
    species=new JLabel(name);
    population=new JTextField(pop+"",6);
    
    super.add(species);
    super.add(population);
    
    setLayout(new FlowLayout());
    pack();
        
    setVisible(true);
  }
  
}