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
    public int[] positionAround(int x, int y) {
        int newX = x;
        int newY = y;
        Random random = new Random();
        int direction = random.nextInt(6);
        if (y % 2 == 0) {
            switch (direction) {
                case 0: {
                    newX = x;
                    newY = y - 2;
                    break;
                }
                case 1: {
                    newX = x;
                    newY = y - 1;
                    break;
                }
                case 2: {
                    newX = x;
                    newY = y + 1;
                    break;
                }
                case 3: {
                    newX = x;
                    newY = y + 2;
                    break;
                }
                case 4: {
                    newX = x - 1;
                    newY = y + 1;
                    break;
                }
                case 5: {
                    newX = x - 1;
                    newY = y - 1;
                    break;
                }
            }
        } else {
            switch (direction) {
                case 0: {
                    newX = x;
                    newY = y - 2;
                    break;
                }
                case 1: {
                    newX = x + 1;
                    newY = y - 1;
                    break;
                }
                case 2: {
                    newX = x + 1;
                    newY = y + 1;
                    break;
                }
                case 3: {
                    newX = x;
                    newY = y + 2;
                    break;
                }
                case 4: {
                    newX = x;
                    newY = y + 1;
                    break;
                }
                case 5: {
                    newX = x;
                    newY = y - 1;
                    break;
                }
            }
        }
        int[] newPositionTab = new int[2];
        newPositionTab[0] = newX;
        newPositionTab[1] = newY;
        return newPositionTab;
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
    public int[] newDirection(int x, int y, Keyboard.KeyName key) {
        int[] newXY = new int[2];
        if(y%2 == 0)
        {
        switch (key) {
            case DOWN: {
                newXY[0] = x;
                newXY[1] = y + 2;
                break;
            }
            case UP: {
                newXY[0] = x;
                newXY[1] = y - 2;
                break;
            }
            case LEFT: {
                newXY[0] = x - 1;
                newXY[1] = y - 1;
                break;
            }
            case RIGHT: {
                newXY[0] = x;
                newXY[1] = y - 1;
                break;
            }
            default: {
                newXY[0] = x;
                newXY[1] = y;
            }
        }
        }
        else
        {
            switch (key) {
            case DOWN: {
                newXY[0] = x;
                newXY[1] = y + 2;
                break;
            }
            case UP: {
                newXY[0] = x;
                newXY[1] = y - 2;
                break;
            }
            case LEFT: {
                newXY[0] = x;
                newXY[1] = y + 1;
                break;
            }
            case RIGHT: {
                newXY[0] = x + 1;
                newXY[1] = y + 1;
                break;
            }
            default: {
                newXY[0] = x;
                newXY[1] = y;
            }
            }
        }
        return newXY;
    }
}
