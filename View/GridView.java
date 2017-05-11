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
    public void draw(Graphics g, int x, int y) {
        Color c = new Color(0, 0, 0);
        g.setColor(c);

        g.drawRect((x * sizeOfRectangle), (y * sizeOfRectangle), ((sizeOfRectangle)), ((sizeOfRectangle)));
        int xStringPosition = (x * sizeOfRectangle) + (sizeOfRectangle) / 2;
        int yStringPosition = (y * sizeOfRectangle) + sizeOfRectangle / 2;
        g.drawString(gridOfObjects[x][y], xStringPosition, yStringPosition);

    }

    @Override
    public int[] positionAround(int x, int y) {
        int newX = x;
        int newY = y;
        Random random = new Random();
        int direction = random.nextInt(8);
        switch (direction) {
            case 0: {
                newX = x;
                newY = y + 1;
                break;
            }
            case 1: {
                newX = x + 1;
                newY = y + 1;
                break;
            }
            case 2: {
                newX = x + 1;
                newY = y;
                break;
            }
            case 3: {
                newX = x + 1;
                newY = y - 1;
                break;
            }
            case 4: {
                newX = x;
                newY = y - 1;
                break;
            }
            case 5: {
                newX = x - 1;
                newY = y - 1;
                break;
            }
            case 6: {
                newX = x - 1;
                newY = y;
                break;
            }
            case 7: {
                newX = x - 1;
                newY = y + 1;
                break;
            }
        }
        int[] newPositionTab = new int[2];
        newPositionTab[0] = newX;
        newPositionTab[1] = newY;
        return newPositionTab;
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
    public int[] newDirection(int x, int y, Keyboard.KeyName key) {
        int[] newXY = new int[2];
        switch (key) {
            case UP: {
                newXY[0] = x;
                newXY[1] = y - 1;
                break;
            }
            case DOWN: {
                newXY[0] = x;
                newXY[1] = y + 1;
                break;
            }
            case LEFT: {
                newXY[0] = x - 1;
                newXY[1] = y;
                break;
            }
            case RIGHT: {
                newXY[0] = x + 1;
                newXY[1] = y;
                break;
            }
            default:
            {
               newXY[0] = x;
                newXY[1] = y; 
            }
        }
        return newXY;
    }

}
