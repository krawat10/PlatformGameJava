/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mateusz
 */
public class GridView extends View
{
    int sizeOfRectangle = 25;
         
    public static View getInstance()
    {
     if(uniqueObject == null)
     {
         uniqueObject = new GridView();
     }
     return uniqueObject;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                draw(g, j, i);
            }
        }
    }    
    
    @Override
    public void draw(Graphics g, int x, int y) {        
        Color c = new Color(0, 0, 0);
        g.setColor(c);
        g.drawRect((x * sizeOfRectangle), (y * sizeOfRectangle), ((sizeOfRectangle)), ((sizeOfRectangle)));        
    }
    
    @Override
    public void drawNewObject(int x, int y)
    {
        sizeX = 1;
        this.initViewSettings();
        f.getContentPane().add(this, BorderLayout.CENTER);       
        f.setVisible(true);   
    }  
}


