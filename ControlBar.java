import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

class ControlBar extends JPanel{
  Timer t;
  TimerControls tc;
  ControlBar(MainWindow parent){
    super.setPreferredSize(new Dimension(1024,172));
    setLayout(new BorderLayout());
    
    tc=new TimerControls(this);
    
    super.add(tc,BorderLayout.WEST);
    
    super.repaint();
    super.setVisible(true);
  }
  
}

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
  
  public synchronized void actionPerformed(ActionEvent e){
    if (e.getSource().equals(pausePlay)){
      if(pausePlay.getText().equals("Play")){
        pausePlay.setText("Pause");
        Simulation.timer().start();
      }else{
        pausePlay.setText("Play");
        Simulation.timer().stop();
      } 
      
    }
  }
  
  public synchronized void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (!source.getValueIsAdjusting()) {
      int delay =1000* (int)source.getValue();
      Simulation.timer().setDelay(delay);
    }
  }
  
  
  private synchronized JSlider makeJSlider (){
    
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