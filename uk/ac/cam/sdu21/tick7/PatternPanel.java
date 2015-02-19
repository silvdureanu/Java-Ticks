package uk.ac.cam.sdu21.tick7;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class PatternPanel extends JPanel {

 private JList guiList;
 private Pattern currentPattern;
 List<Pattern> patternList;

 protected abstract void onPatternChange();
 
 public PatternPanel() {
  super();
  currentPattern = null;
  setLayout(new BorderLayout());
  guiList = new JList();
  add(new JScrollPane(guiList));
 }

 public Pattern getCurrentPattern() {
 	return currentPattern;
 }

 public void setPatterns(List<Pattern> list) {
  patternList = list;
  if (list == null) {
     currentPattern = null; //if list is null, then no valid pattern
     guiList.setListData(new String[]{}); //no list item to select
     return;
  }

  ArrayList<String> names = new ArrayList<String>();

  for(Pattern i: list) {
    names.add(i.getName() + " (" + i.getAuthor()+")");
  }
  


  guiList.setListData(names.toArray());
  currentPattern = list.get(0); //select first element in list
  guiList.setSelectedIndex(0);  //select first element in guiList

  guiList.addListSelectionListener(new ListSelectionListener() {
     public void valueChanged(ListSelectionEvent e) {
      if (!e.getValueIsAdjusting() && (patternList != null)) {
       int sel = guiList.getSelectedIndex();
       if (sel != -1) {
        currentPattern = patternList.get(sel);
        onPatternChange();
       }
      }
     }
  });
 } 

}