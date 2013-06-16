import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class ManifestArea extends JPanel {
  
  ManifestArea(ArrayList<Manifest> manifest){
    
    super.setLayout(new GridLayout (manifest.size(),2));
        super.setPreferredSize(new Dimension(200,manifest.size()*30));
    for (Manifest species : manifest)
    {
      System.out.println(species.getSpecies()+":"+species.getPop());
      super.add(new JLabel (species.getSpecies()));
      super.add(new JLabel (Integer.toString(species.getPop())));
    }
    
    super.setVisible(true);

  }
  
  public void refresh (ArrayList<Manifest> manifest){
    super.removeAll();
    super.revalidate();
    super.setLayout(new GridLayout (manifest.size(),2));
        super.setPreferredSize(new Dimension(200,manifest.size()*30));
    
    for (Manifest species : manifest)
    {
      super.add(new JLabel (species.getSpecies()));
      super.add(new JLabel (Integer.toString(species.getPop())));
    }
    
    super.setVisible(true);

  }
}