package uk.ac.cam.sdu21.tick7;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.ButtonGroup;
import uk.ac.cam.acr31.life.World;
import java.awt.Graphics;
import java.awt.Dimension;


public class GamePanel extends JPanel {

 private int zoom = 10; //Number of pixels used to represent a cell
 private int width = 1; //Width of game board in pixels
 private int height = 1;//Height of game board in pixels
 private World current = null;


public Dimension getPreferredSize() {
  return new Dimension(width, height);
}

protected void setZoom(int value) {
  zoom = value;
}

 protected void paintComponent(Graphics g) {
  if (current == null) return;
  g.setColor(java.awt.Color.WHITE);
  g.fillRect(0, 0, width, height);
  current.draw(g, width, height);
  if (zoom > 4) {
   g.setColor(java.awt.Color.LIGHT_GRAY);
   int i;
   
   for(i=0; i<height; i+=10)
    g.drawLine(0,i,width,i);

   for(i=1; i<width; i+=10)
    g.drawLine(i,0,i,height);


  }
 }

 private void computeSize() {
  if (current == null)  return;
  int newWidth = current.getWidth() * zoom;
  int newHeight = current.getHeight() * zoom;
  if (newWidth != width || newHeight != height) {
   width = newWidth;
   height = newHeight;
   revalidate(); //trigger the GamePanel to re-layout its components
  }
 }

 public void display(World w) {
  current = w;
  computeSize();
  repaint();
 }
}