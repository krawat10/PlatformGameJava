/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import platformgame.Keyboard;

/**
 *
 * @author Mateusz
 */
public class GridView extends View {

    public static View getInstance() {
        if (uniqueObject == null) {
            uniqueObject = new GridView();
        }
        return uniqueObject;
    }
    
    
    @Override
    public void setVisibility()
    {
        super.setVisibility();
        this.numberOfFieldAroundOneField = 8;
    }
    
    
    
    @Override
    public void draw(Graphics g, int x, int y) {
        Color c = new Color(0, 0, 0);
        g.setColor(c);

        g.drawRect((x * sizeOfRectangle), (y * sizeOfRectangle), ((sizeOfRectangle)), ((sizeOfRectangle)));
        int xStringPosition = (x * sizeOfRectangle) + (sizeOfRectangle) / 2;
        int yStringPosition = (y * sizeOfRectangle) + sizeOfRectangle / 2;
        g.drawString(gridOfObjects[x][y], xStringPosition, yStringPosition);

    }

    @Override
    public Point positionAround(int x, int y) {        
        Random random = new Random();
        int direction = random.nextInt(8);
        Point newField = getPositionAround(x, y, direction);        
        return newField;
        
    }
    
    @Override
    public Point getPositionAround(int x, int y, int indexOfField)
    {
        Point p = new Point();
        switch (indexOfField) {
            case 0: {
                p.x = x;
                p.y = y + 1;
                break;
            }
            case 1: {
                p.x = x + 1;
                p.y = y + 1;
                break;
            }
            case 2: {
                p.x = x + 1;
                p.y = y;
                break;
            }
            case 3: {
                p.x = x + 1;
                p.y = y - 1;
                break;
            }
            case 4: {
                p.x = x;
                p.y = y - 1;
                break;
            }
            case 5: {
                p.x = x - 1;
                p.y = y - 1;
                break;
            }
            case 6: {
                p.x = x - 1;
                p.y = y;
                break;
            }
            case 7: {
                p.x = x - 1;
                p.y = y + 1;
                break;
            }
        }
        return p;
    }
    
    @Override
    protected void addLabel(int x, int y) {
        JLabel label = new JLabel("", SwingConstants.CENTER);
        label.setName(x + " " + y);
        label.setSize(sizeOfRectangle, sizeOfRectangle);
        label.setBounds((x * sizeOfRectangle), (y * sizeOfRectangle),
                sizeOfRectangle, sizeOfRectangle);
        addLabelListener(label, x, y);
        this.add(label);
    }

    @Override
    public Point newDirection(int x, int y, Keyboard.KeyName key) {        
        int newFieldIndex;
        switch (key) {
            case UP: {
                newFieldIndex = 4;
                break;
            }
            case DOWN: {
                newFieldIndex = 0;
                break;
            }
            case LEFT: {
                newFieldIndex = 6;
                break;
            }
            case RIGHT: {
                newFieldIndex = 2;
                break;
            }
            default:
            {
               newFieldIndex = -1;
            }
        }
        Point newField = getPositionAround(x, y, newFieldIndex);        
        return newField;
    }

}
