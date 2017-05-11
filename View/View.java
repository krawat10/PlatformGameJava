/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.chart.Chart;
import javax.swing.JFrame;
import javax.swing.*;
import platformgame.*;
import javax.swing.JFileChooser;
import platformgame.Keyboard;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Mateusz
 */
public abstract class View extends JPanel implements IView, ActionListener {

    protected static View uniqueObject;
    protected String[] messages;
    protected static boolean isInstance = false;
    protected int sizeX;
    protected int sizeY;
    protected int numberOfVisibleMessage = 3;
    protected char screen[][];
    protected View view;
    protected JFrame f;
    protected Keyboard input;
    protected String[][] gridOfObjects;
    private JComboBox OrganismList;
    protected int sizeOfRectangle = 50;
    private World world;
    JButton open;
    JButton save;
    JButton tour;

    public View() {
    }

    public void closeWindow() {
        uniqueObject = null;
        Window w = SwingUtilities.getWindowAncestor(this);
        this.removeAll();
        w.setVisible(false);
    }

    View(View view) {
        this.f = view.f;
        this.OrganismList = view.OrganismList;
        this.input = view.input;
        this.world = view.world;
        this.open = view.open;
        this.save = view.save;
        this.sizeOfRectangle = view.sizeOfRectangle;
    }

    public static void deleteInstance() {
        uniqueObject = null;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setSizeX(int value) {
        this.sizeX = value;
    }

    public void setSizeY(int value) {
        this.sizeY = value;
    }

    public void dotGrid() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                gridOfObjects[i][j] = ".";
            }
        }
    }

    protected void initViewSettings() {
        f = new JFrame();
        this.setLayout(null);
        initMessage();
        initInput();

        f.getContentPane().add(this, BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize((int) (sizeOfRectangle * sizeX + 123), (int) (sizeOfRectangle * sizeY + 40));
        f.setVisible(true);

    }

    private void initMessage() {
        messages = new String[numberOfVisibleMessage];
        for (int i = 0; i < numberOfVisibleMessage; i++) {
            messages[i] = "";
        }
    }

    private void initInput() {
        input = new Keyboard();
        f.addKeyListener(input);
    }

    public Keyboard getKeyListener() {
        return input;
    }

    public void draw(Graphics g, int x, int y) {
        Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        g.setColor(c);
        g.fillRect((int) (Math.random() * 400), (int) (Math.random() * 300), (int) (Math.random() * 40), (int) (Math.random() * 40));
    }

    public void drawMessages(Graphics g, int numberOfMessage) {
        int xStringPosition = sizeX * (sizeOfRectangle) + (sizeOfRectangle) / 2;
        int yStringPosition = (numberOfMessage * 25) + 25 / 2;
        g.drawString(messages[numberOfMessage], xStringPosition, yStringPosition);
    }

    @Override
    public void setVisibility() {
        if (sizeX != 0 && sizeY != 0) {
            gridOfObjects = new String[sizeX][sizeY];
            this.dotGrid();
            addJComboBox();
            this.initButtons();
            this.addSaveLoadButtons();
        }

        initViewSettings();
        EventQueue.invokeLater(() -> {
            this.setVisible(true);
        });
    }

    public void initResizedGrid(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        gridOfObjects = new String[sizeX][sizeY];
        dotGrid();
        resizeComponents();
        repaint();
    }

    private void resizeComponents() {
        OrganismList.setBounds((sizeX * sizeOfRectangle), ((sizeY) * 25),
                100, 24);
        OrganismList.setFocusable(false);
        open.setBounds((sizeX * sizeOfRectangle), ((sizeY - 2) * 25),
                100, 24);
        OrganismList.setFocusable(false);
        save.setBounds((sizeX * sizeOfRectangle), ((sizeY - 1) * 25),
                100, 24);
        OrganismList.setFocusable(false);
        f.setSize((int) (sizeOfRectangle * sizeX + 123), (int) (sizeOfRectangle * sizeY + 40));
        initMessage();
        initButtons();
    }

    private void addSaveLoadButtons() {
        open = new JButton("Open");
        open.setBounds((sizeX * sizeOfRectangle), ((sizeY - 2) * 25),
                100, 24);        
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showDialog(view, "Open");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    world.Load(file);
                    System.out.println("Opening: " + file.getName() + ".");
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        }
        );
        open.setFocusable(false);
        this.add(open);
        
        save = new JButton("Save");
        save.setBounds((sizeX * sizeOfRectangle), ((sizeY - 1) * 25),
                100, 24);       
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showDialog(view, "Save");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    world.Save(file);
                    System.out.println("Opening: " + file.getName() + ".");
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        }
        );
        save.setFocusable(false);
        this.add(save);
        
        tour = new JButton("New tour");
        tour.setBounds((sizeX * sizeOfRectangle), ((sizeY - 3) * 25),
                100, 24);       
        tour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.tour();
            }
        }
        );
        tour.setFocusable(false);
        this.add(tour);

    }
    public abstract int[] newDirection(int x, int y, Keyboard.KeyName key);
    private void addJComboBox() {
        String[] petStrings = {"Wolf", "Antelope", "Grass", "Sheep", "CyberSheep"};

        OrganismList = new JComboBox(petStrings);
        OrganismList.setSelectedIndex(4);
        OrganismList.setBounds((sizeX * sizeOfRectangle), ((sizeY) * 25),
                100, 24);
        OrganismList.setFocusable(false);
        OrganismList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("change selected item" + OrganismList.getSelectedItem());

            }
        });
        this.add(OrganismList);
    }

    protected abstract void addLabel(int x, int y);

    protected void addLabelListener(JComponent label, int x, int y) {
        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent event) {
                LabelEvent(x, y);
            }
        });
    }

    protected void LabelEvent(int x, int y) {
        System.out.println("click");
        world.addCreatureFromGrid(x, y, (String) OrganismList.getSelectedItem());
    }

    protected void initButtons() {
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                addLabel(j, i);
            }
        }
    }

    @Override
    public void refreshScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawObject(int x, int y, String name) {
        gridOfObjects[x][y] = name;

        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                draw(g, j, i);
            }
        }

        for (int i = 0; i < numberOfVisibleMessage; i++) {
            drawMessages(g, i);
        }

    }

    public void drawControls() {

    }

    @Override
    public void deleteObject(int x, int y) {
        gridOfObjects[x][y] = "";
        this.repaint();
    }

    public abstract int[] positionAround(int x, int y);

    public void setNewMessage(String message) {

        for (int i = messages.length - 1; i > 0; i--) {
            messages[i] = messages[i - 1];
        }
        messages[0] = message;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hello");

    }
}
