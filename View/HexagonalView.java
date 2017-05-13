/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.View.uniqueObject;
import View.PolygonButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Random;
import javafx.scene.layout.Border;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import platformgame.Keyboard;

/**
 *
 * @author Mateusz
 */
public class HexagonalView extends View {

    private double R;
    private double r;
    private double size;

    public static View getInstance() {
        if (uniqueObject == null) {
            uniqueObject = new HexagonalView();
        }
        return uniqueObject;
    }

    @Override
    public void setVisibility()
    {
        super.setVisibility();
        this.numberOfFieldAroundOneField = 6;
    }
    
    @Override
    public void draw(Graphics g, int x, int y) {
        Polygon p = new Polygon();
        initHexSettings();
        setSizeOfHexagon(x, y, p);
        drawStringInHexagon(g, x, y);
        g.drawPolygon(p);
    }

    private void drawStringInHexagon(Graphics g, int x, int y) {
        double shiftX = 0;

        if (y % 2 != 0) {
            shiftX = 1.5 * R;
        }

        int pointX = (int) (20 + shiftX + 3 * R * x + size * Math.cos(3 * 2 * Math.PI / 6));
        int pointY = (int) (20 + y * r + size * Math.sin(3 * 2 * Math.PI / 6));

        pointX += (int) (R / 2);
        g.drawString(gridOfObjects[x][y], pointX, pointY);
    }

    @Override
    public Point positionAround(int x, int y) {
        int newX = x;
        int newY = y;
        Random random = new Random();
        int direction = random.nextInt(6);
        Point newField = getPositionAround(x, y, direction);
        return newField;
    }

    private void initHexSettings() {
        R = 0.5 * ((size * Math.cos(0 * 2 * Math.PI / 6))
                - (size * Math.cos(3 * 2 * Math.PI / 6)));
        r = Math.pow(3, 0.5) / 2 * R;
        size = sizeOfRectangle / 4;
    }

    @Override
    protected void addLabel(int x, int y) {
        JLabel label = new JLabel("s", SwingConstants.CENTER);
        initHexSettings();
        Polygon p = new Polygon();
        setSizeOfHexagon(x, y, p);
        PolygonButton btn = new PolygonButton(p);
        addLabelListener(btn, x, y);
        add(btn);
    }

    private Polygon setSizeOfHexagon(int x, int y, Polygon polygon) {
        double shiftX = 0;

        if (y % 2 != 0) {
            shiftX = 1.5 * R;
        }
        int pointX;
        int pointY;
        for (int i = 0; i < 6; i++) {
            pointX = (int) (20 + shiftX + 3 * R * x + size * Math.cos(i * 2 * Math.PI / 6));
            pointY = (int) (20 + y * r + size * Math.sin(i * 2 * Math.PI / 6));
            polygon.addPoint(pointX, pointY);
            if (i == 3) {
                pointX += (int) (R / 2);
            }
        }
        return polygon;
    }

    @Override
    public Point newDirection(int x, int y, Keyboard.KeyName key) {        
        int newFieldIndex;
        switch (key) {
            case DOWN: {
                newFieldIndex = 3;
                break;
            }
            case UP: {
                newFieldIndex = 0;
                break;
            }
            case LEFT: {
                if(y%2 == 0)
                    newFieldIndex = 4;
                else
                    newFieldIndex = 5;
                break;
            }
            case RIGHT: {
                if(y%2 == 0)
                    newFieldIndex = 2;
                else
                    newFieldIndex = 1;
                break;
            }
            default: {
                newFieldIndex = -1;
            }
        }
        Point newPos = getPositionAround(x, y, newFieldIndex);        
        return newPos;
    }

    @Override
    public Point getPositionAround(int x, int y, int indexOfField) {
        Point p = new Point();
        if (y % 2 == 0) {
            switch (indexOfField) {
                case 0: {
                    p.x = x;
                    p.y = y - 2;
                    break;
                }
                case 1: {
                    p.x = x;
                    p.y = y - 1;
                    break;
                }
                case 2: {
                    p.x = x;
                    p.y = y + 1;
                    break;
                }
                case 3: {
                    p.x = x;
                    p.y = y + 2;
                    break;
                }
                case 4: {
                    p.x = x - 1;
                    p.y = y + 1;
                    break;
                }
                case 5: {
                    p.x = x - 1;
                    p.y = y - 1;
                    break;
                }
                case -1: {
                    p.x = x;
                    p.y = y;
                    break;
                }
                default: {
                    p.x = x;
                    p.y = y;
                    break;
                }
            }
        } else {
            switch (indexOfField) {
                case 0: {
                    p.x = x;
                    p.y = y - 2;
                    break;
                }
                case 1: {
                    p.x = x + 1;
                    p.y = y - 1;
                    break;
                }
                case 2: {
                    p.x = x + 1;
                    p.y = y + 1;
                    break;
                }
                case 3: {
                    p.x = x;
                    p.y = y + 2;
                    break;
                }
                case 4: {
                    p.x = x;
                    p.y = y + 1;
                    break;
                }
                case 5: {
                    p.x = x;
                    p.y = y - 1;
                    break;
                }
                case -1: {
                    p.x = x;
                    p.y = y;
                    break;
                }
                default: {
                    p.x = x;
                    p.y = y;
                    break;
                }
            }
        }
        return p;
    }
}
