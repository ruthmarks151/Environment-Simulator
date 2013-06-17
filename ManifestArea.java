import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class ManifestArea extends JPanel {
  
  ManifestArea(ArrayList<Manifest> manifest){
    
    refresh(manifest);
  }
  
  public void refresh (ArrayList<Manifest> manifest){
    super.removeAll();
    super.revalidate();
    super.setLayout(new GridLayout ((manifest.size()+2),2));
    super.setPreferredSize(new Dimension(200,(2+manifest.size())*30));
    
    super.add(new JLabel ("              Global"));
    super.add(new JLabel (" Populations"));
    super.add(new JLabel (" "));
    super.add(new JLabel (" "));
    for (Manifest species : manifest)
    {
      super.add(new JLabel ("     "+species.getSpecies()));
      super.add(new JLabel (Integer.toString(species.getPop())));
    }
    
    super.setVisible(true);
    
  }
}