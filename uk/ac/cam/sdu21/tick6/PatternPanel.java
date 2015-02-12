package uk.ac.cam.sdu21.tick6;

import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.util.List;
import java.util.ArrayList;

public class PatternPanel extends JPanel {

 private JList guiList;
 
 public PatternPanel() {
  super();
  setLayout(new BorderLayout());
  guiList = new JList();
  add(new JScrollPane(guiList));
 }

 public void setPatterns(List<Pattern> list) {
  ArrayList<String> names = new ArrayList<String>();

  for(Pattern i: list) {
    names.add(i.getName() + " (" + i.getAuthor()+")");
  }
  
  guiList.setListData(names.toArray());
 } 
}