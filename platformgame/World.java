/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

import Organism.Human;
import Organism.Organism;
import View.GridView;
import View.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;
import java.util.List;
import javafx.print.Collation;

/**
 *
 * @author Mateusz
 */
public class World implements IObserver, IOOperation {

    public enum positionStatus {
        FOBBIDEN, MONSTER, OPEN
    };

    private int sizeX;
    private int sizeY;
    private Keyboard.KeyName activeKey = Keyboard.KeyName.DISACTIVATED;
    private int numberOfCreatures = 0;
    private List<Organism> organism;
    private View view;

    public World(int sizeX, int sizeY) {
        view = HexagonalView.getInstance();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initView();
        view.getKeyListener().AddSubscriber(this);        
        organism = new ArrayList<Organism>();
    }

    private void initView() {
        view.setSizeX(sizeX);
        view.setSizeY(sizeY);
        view.setVisibility();
        view.setWorld(this);
    }

    public Organism getCrature(int x, int y) {
        for (Organism org : organism) {
            if ((org.getX() == x) && (org.getY() == y)) {
                return org;
            }
        }
        return null;
    }

    public void drawWorld() {
        this.getView().repaint();
        //tour();
    }

    private void ClearDeadMonster() {
        for (int j = 0; j < organism.size(); j++) {
            if (!organism.get(j).getIsAlive()) {
                
                organism.remove(j);
                numberOfCreatures--;
            }
        }
    }

    public void tour() {
        Collections.sort(organism);

        //
        //Reverse iteration (Most initative creatures are in the end).
        //
        Organism duplicate = organism.get(organism.size() - 1);
        for (int j = 0; j < organism.size(); j++) {
            organism.get(j).action();
            if (duplicate == organism.get(j)) {
                continue;
            }
            duplicate = organism.get(j);
            this.ClearDeadMonster();
            this.view.repaint();
        }
        this.activeKey = Keyboard.KeyName.DISACTIVATED;
    }

    public Point GetFreeSpot() {
        boolean isInitalized = false;
        int randX = 2;
        int randY = 1;
        Random random = new Random();
        while (!isInitalized) {
            randX = random.nextInt(this.sizeX - 1);
            randY = random.nextInt(this.sizeY - 1);
            //
            //If program find place where is a free spot, then while loop should stop and there
            //will be place a creature
            //
            if (checkPosition(randX, randY) == positionStatus.OPEN) {
                isInitalized = true;
            }
        }
        Point p = new Point(randX, randY);
        return p;
    }

    public boolean attackMonsterAtPosition(int x, int y, Organism attacker) {
        boolean isDefended;
        for (Organism org : organism) {
            if ((org.getX() == x) && (org.getY() == y)) {
                String message = 
                        attacker.getClass().getSimpleName() + " attack " + org.getClass().getSimpleName();
                this.getView().setNewMessage(message);
                isDefended = org.isPushBackAttack(attacker);
                return !isDefended;
            }
        }
        return false;
    }

    public void addCreatureFromGrid(int x, int y, String name) {
        if (checkPosition(x, y) == positionStatus.OPEN) {
            OrganismFactory org = new OrganismFactory(x, y, this);
            this.addCreature(org.getInstance(name));
        }

    }

    public List<Organism> getList() {
        return organism;
    }

    public Keyboard.KeyName getImput() {
        return this.activeKey;
    }

    public void setNumberOfCreatures(int newNumberOfCreatures) {
        this.numberOfCreatures = newNumberOfCreatures;
    }

    public int getNumberOfCreatures() {
        return numberOfCreatures;
    }

    public void addCreature(Organism newOrganism) {
        organism.add(newOrganism);
        numberOfCreatures++;
    }

    public positionStatus checkPosition(int x, int y) {
        if ((x < 0) || (x > this.sizeX - 1) || (y < 0) || (y > this.sizeY - 1)) {
            return positionStatus.FOBBIDEN;
        }

        for (Organism org : organism) {
            if ((org.getX() == x) && (org.getY() == y)) {
                return positionStatus.MONSTER;

            }
        }
        return positionStatus.OPEN;
    }

    public View getView() {
        return view;
    }

    @Override
    public void update(Object object) {
       this.activeKey = (Keyboard.KeyName)object; 
    }

    @Override
    public void Save(File file) {
        try {
            FileOutputStream fileOut
                    = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Serialize(out);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void Load(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Deserialize(in);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Override
    public void Serialize(ObjectOutputStream out) {
        try {
            String gridName = view.getClass().getSimpleName();
            System.out.println(gridName);
            out.writeUTF(gridName);
            out.writeInt(sizeX);
            out.writeInt(sizeY);
            out.writeInt(organism.size());
            for (int j = 0; j < organism.size(); j++) {
                if (organism.get(j).getIsAlive()) {
                    out.writeUTF(organism.get(j).getClass().getSimpleName());
                    out.writeInt(organism.get(j).getX());
                    out.writeInt(organism.get(j).getY());
                }
                if (organism.get(j) instanceof Human) {
                    System.out.println("isHuman");
                    out.writeBoolean(true);
                    Human human = (Human) organism.get(j);
                    out.writeBoolean(human.getAzureShield());
                    out.writeInt(human.getAzureShieldTour());
                } else {
                    out.writeBoolean(false);
                }
            }
        } catch (IOException i) {
            System.err.println(i);
        }

    }

    @Override
    public void Deserialize(ObjectInputStream in) {
        try {
            this.organism.clear();
            this.numberOfCreatures = 0;

            String gridName = in.readUTF();
            this.sizeX = in.readInt();
            this.sizeY = in.readInt();
            view.closeWindow();
            if ("GridView".equals(gridName)) {
                view = GridView.getInstance();
            } else {
                view = HexagonalView.getInstance();
            }

            initView();
            view.initResizedGrid(sizeX, sizeY);
            view.getKeyListener().AddSubscriber(this);
            int count = in.readInt();
            OrganismFactory org = new OrganismFactory(this);
            for (int j = 0; j < count; j++) {
                String name = in.readUTF();
                int sizeX = in.readInt();
                int sizeY = in.readInt();
                boolean isHuman = in.readBoolean();
                if (isHuman) {
                    boolean AzureShield = in.readBoolean();
                    int AzureShieldTour = in.readInt();
                    Human human = (Human) org.getInstance(sizeX, sizeY, name);
                    human.setAzureShield(AzureShield);
                    human.setAzureShieldTour(AzureShieldTour);
                    this.addCreature(human);
                } else {
                    this.addCreature(org.getInstance(sizeX, sizeY, name));
                }
                System.out.println(name);
            }
        } catch (IOException i) {
            System.err.println(i);
        }
    }
}
