import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class ManifestArea extends JPanel {
  
  ManifestArea(ArrayList<Manifest> manifest){
    refresh(manifest);
  }
  
  public void refresh (ArrayList<Manifest> manifest){
    // remove everything from the manifest area
    super.removeAll();
    // revalidate
    super.revalidate();
    // set the layout depending on the size of the manifest
    super.setLayout(new GridLayout ((manifest.size()+2),2));
    // set preferred size based on size of the manifest
    super.setPreferredSize(new Dimension(200,(2+manifest.size())*30));
    // add jlabels for "global population"
    super.add(new JLabel ("              Global"));
    super.add(new JLabel (" Populations"));
    // add 2 useless labels for new line padding
    super.add(new JLabel (" "));
    super.add(new JLabel (" "));
    // iterate through manifest
    for (Manifest species : manifest)
    {
      // add label for species
      super.add(new JLabel ("     "+species.getSpecies()));
      // add label for population
      super.add(new JLabel (Integer.toString(species.getPop())));
    }
    
    // set visible
    super.setVisible(true);
    
  }
}