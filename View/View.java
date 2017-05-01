/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javafx.scene.chart.Chart;
import javax.swing.JFrame;
import javax.swing.*;
import platformgame.IObserver;
import platformgame.Keyboard;
/**
 *
 * @author Mateusz
 */
public abstract class View extends JPanel implements IView
{
    
        protected static View uniqueObject;
        protected String messasges;
	protected static boolean isInstance = false;
	protected int sizeX;
	protected int sizeY;
	protected char screen[][];
        protected View view;
        protected JFrame f;
        protected Keyboard input;

        
        
    View() {}
      
    
    public void setSizeX(int value)
    {
        this.sizeX = value;
    }
    
    public void setSizeY(int value)
    {
        this.sizeY = value;
    }    
    
    protected void initViewSettings()
    {       
        f = new JFrame();      
        initInput();
        f.getContentPane().add(this, BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        f.setVisible(true);
    }
    
    private void initInput()
    {
        input = new Keyboard();
        f.addKeyListener(input);           
    }
    
    public Keyboard getKeyListener()
    {
        return input;
    }
    
    
    
    public void draw(Graphics g, int x, int y) {
        Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        g.setColor(c);
        g.fillRect((int) (Math.random() * 400), (int) (Math.random() * 300), (int) (Math.random() * 40), (int) (Math.random() * 40));
    }
    
    @Override
    public void setVisibility()
    {
        initViewSettings();
        EventQueue.invokeLater(() -> {            
            this.setVisible(true);
        });
    }
    
    @Override
    public void RefreshScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawObject(char symbolOfObject, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteObject(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public abstract void drawNewObject(int x, int y);

    
}
