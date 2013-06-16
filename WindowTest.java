import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class WindowTest{
  public static void main(String[]args){
    JFrame jf=new JFrame("Control Bar Test");
    
    jf.setPreferredSize(new Dimension(720,720));
    jf.setLayout(new BorderLayout());
    
    ControlBar cb=new ControlBar(new MainWindow(new Grid(16,16)));
    jf.add(cb,BorderLayout.SOUTH);
    
    jf.setVisible(true);
    
  }

}