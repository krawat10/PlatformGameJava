package View;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//from  w  ww . j  a va2s .  c o  m
import javax.swing.JComponent;
import javax.swing.JFrame;

class PolygonButton extends JComponent implements MouseListener
     {
  protected String text;

  protected Polygon polygon;

  protected Rectangle rectangle;

  protected boolean isActive;

  protected static PolygonButton button;

  public PolygonButton(Polygon p) {
    polygon = p;    
    setOpaque(false);
    addMouseListener(this);    
    rectangle = new Rectangle(polygon.getBounds());
    rectangle.grow(1, 1);
    setBounds(rectangle);
    polygon.translate(-rectangle.x, -rectangle.y);
  }  

  @Override
  public void mouseClicked(MouseEvent e) {      
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {    
  }

  @Override
  public void mouseEntered(MouseEvent e) {    
  }
}