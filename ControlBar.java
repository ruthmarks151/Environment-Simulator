/**********************************************************************************************************************/
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

class ControlBar extends JPanel{
  protected Timer t;
  protected TimerControls tc;
  protected SaveControls sc;
  protected HeatMapControls hc;
  protected MainWindow parent;
  
  public MainWindow getParent(){return parent;}
  
  ControlBar(MainWindow creator){
    parent=creator;
    
    super.setPreferredSize(new Dimension(720,172));
    setLayout(new BorderLayout());
    
    // add timer, save, and heatmap controls to the bar
    
    tc=new TimerControls(this);
    sc=new SaveControls(parent.getGrid(),this);
    hc=new HeatMapControls(this);
    add(tc,BorderLayout.WEST);
    add(hc,BorderLayout.CENTER);
    add(sc,BorderLayout.EAST);
    
    
    super.repaint();
    super.setVisible(true);
    System.out.println("Done Constucting");
  }
  
}
/**********************************************************************************************************************/
class HeatMapControls extends JPanel implements ActionListener{
  
  public JComboBox species;
  private ControlBar parent;
  
  HeatMapControls(ControlBar creator){
    super();
    parent=creator;
    
    setLayout(new BorderLayout());
    super.setPreferredSize(new Dimension(255,170));
    

    String[]organisms=SpeciesTable.getOrganisms();
    String[]options=new String [organisms.length+1];
    
    options[0]="None";
    for(int i=0;i<organisms.length;i++){
      options[i+1]=organisms[i];
    }
    
    species=new JComboBox(options);
    species.addActionListener(this);
    for (String str:SpeciesTable.getOrganisms()){
      
    }
   
    super.add(species,BorderLayout.SOUTH);
    super.add(new Gradient(),BorderLayout.CENTER);
    super.add(new JLabel("Least"),BorderLayout.WEST);
    super.add(new JLabel("Most"),BorderLayout.EAST);
        super.add(new JLabel("Heat Map Controls"),BorderLayout.NORTH);
    super.setVisible(true);   
  }
  
  public void actionPerformed(ActionEvent e) {

    parent.getParent().getGrid().setHeatMap(species.getSelectedItem().toString());//I now regret the parent model
   parent.getParent().refresh();  
  }
  
 
}
/**********************************************************************************************************************/
class Gradient extends JPanel{
  Gradient(){
  super.setPreferredSize(new Dimension(35,255));
  }  
  @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (int x=0;x<255;x++){
        g.setColor(new Color(x,0,255-x));
        g.fillRect(x,10,1,100);
      }
      
    }
}
/**********************************************************************************************************************/
class SaveControls extends JPanel implements ActionListener{
  private JButton save;
  private JButton load;
  private Grid grid;
  private ControlBar parent;
  private JTextField fileName;
  
  SaveControls(Grid currentGrid,ControlBar creator){
    parent=creator;
    grid=currentGrid; 
    super.setPreferredSize(new Dimension(200,172));
    setLayout(new GridLayout(3,0));
    
    fileName=new JTextField("SaveName");
    save=new JButton("Save");
    load=new JButton("Load");
    
    save.addActionListener(this);
    load.addActionListener(this);
    
    super.add(fileName);
    super.add(save);
    super.add(load);
    
    super.repaint();
    super.setVisible(true);
  }
  private String fileName(){
    fileName.setText(fileName.getText().replaceAll("\\s",""));
    return fileName.getText();
  }
  
  public void actionPerformed(ActionEvent e){
    if(e.getSource().equals(save))
      new GridSaver(grid, fileName());
    if(e.getSource().equals(load)){
      GridLoader gl = new GridLoader(grid, fileName());
      try{
        grid.setMap(gl.read());
      }catch (IOException ioex){
        System.out.println("File load Failed ioex");
      }
    }
    parent.getParent().refresh(); 
  }
}
/**********************************************************************************************************************/

class TimerControls extends JPanel implements ActionListener, ChangeListener{
  private ControlBar parent;
  private JButton pausePlay;
  private JSlider speed;  
  
  
  TimerControls(ControlBar creator){
    parent=creator;
    super.setPreferredSize(new Dimension(200,172));
    setLayout(new BorderLayout());
    
    pausePlay=new JButton ("Play");
    speed=makeJSlider();
    
    pausePlay.addActionListener(this);
    speed.addChangeListener(this);
    
    super.add(pausePlay,BorderLayout.WEST);
    super.add(speed,BorderLayout.EAST);
    super.add(new JLabel(" Simulation Speed Controls"),BorderLayout.NORTH);
    
    super.repaint();
    super.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e){
    if (e.getSource().equals(pausePlay)){
      if(pausePlay.getText().equals("Play")){
        pausePlay.setText("Pause");
        LifeSimulation.timer().start();
      }else{
        pausePlay.setText("Play");
        LifeSimulation.timer().stop();
      } 
      
    }
  }
  
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
      int delay =1000* (int)source.getValue();
      LifeSimulation.timer().setDelay(delay);
    }
  }
  
  
  private JSlider makeJSlider (){
    
    JSlider slider=new JSlider(JSlider.VERTICAL,0, 10, 1);
    slider.setMajorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setSnapToTicks(true);
    
    Hashtable labelTable = new Hashtable();
    for (int i=0;i<=10;i+=2){
      labelTable.put( new Integer(i), new JLabel(i+" Seconds") );
    }
    slider.setLabelTable(labelTable);
    slider.setPaintLabels(true);
    return slider;
  }
  
}