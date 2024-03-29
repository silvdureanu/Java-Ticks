package uk.ac.cam.sdu21.tick6;

import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.io.IOException;
import java.util.List;
import uk.ac.cam.acr31.life.World;

public class GuiLife extends JFrame {

 PatternPanel patternPanel;
 ControlPanel controlPanel;
 GamePanel gamePanel;

 public GuiLife() {
  super("GuiLife");
  setSize(640, 480);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  setLayout(new BorderLayout());
  JComponent optionsPanel = createOptionsPanel();
  add(optionsPanel, BorderLayout.WEST);
  JComponent gamePanel = createGamePanel();
  add(gamePanel, BorderLayout.CENTER);
 }

 private JComponent createOptionsPanel() {
  Box result = Box.createVerticalBox();
  result.add(createSourcePanel());
  result.add(createPatternPanel());
  result.add(createControlPanel());
  return result;
 }

 private void addBorder(JComponent component, String title) {
  Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
  Border tb = BorderFactory.createTitledBorder(etch,title);
  component.setBorder(tb);
 }


 private JComponent createGamePanel() {
  JPanel holder = new JPanel();
  addBorder(holder,Strings.PANEL_GAMEVIEW);
  GamePanel result = new GamePanel();
  holder.add(result);
  gamePanel = result;
  return new JScrollPane(holder);
 }


 private JComponent createSourcePanel() {
  SourcePanel result = new SourcePanel();
  addBorder(result,Strings.PANEL_SOURCE);
  return result; 
 }

 private JComponent createPatternPanel() { 
  patternPanel = new PatternPanel();
  addBorder(patternPanel,Strings.PANEL_PATTERN);
  return patternPanel;
}


 private JComponent createControlPanel() { 
  controlPanel = new ControlPanel();
  addBorder(controlPanel,Strings.PANEL_CONTROL);
  return controlPanel;
}

 public static void main(String[] args) {
  GuiLife gui = new GuiLife();
  try {
    String url="http://www.cl.cam.ac.uk/teaching/current/ProgJava/life.txt";
    List<Pattern> list = PatternLoader.loadFromURL(url);
    gui.patternPanel.setPatterns(list);
    World w = gui.controlPanel.initialiseWorld(list.get(1));
    gui.gamePanel.display(w);
    } catch (IOException ioe) {} catch(PatternFormatException e) {System.out.println("Bad pattern format");}
  gui.setVisible(true);
 }
}
