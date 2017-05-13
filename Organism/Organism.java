/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import java.awt.Point;
import platformgame.World;

/**
 *
 * @author Mateusz
 */
public abstract class Organism implements Comparable<Organism> {

    protected boolean isAlive;
    protected World world;
    protected int strength;
    protected int initative;
    protected char symbol;
    protected int x;
    protected int y;
    protected static int counter;
    protected String name;
    protected Point newXY;

    //Constructors
    public Organism(int x, int y, World world) {
        this.setIsAlive(true);
        this.world = world;
        this.x = x;
        this.y = y;
        newXY = new Point();
        InitFeathures();
        this.world.getView().drawObject(x, y, name);

    }
    
    public Organism()
    {
        
    }

    protected abstract Organism newInstance(int x, int y);   

    public Organism(World _world) {
        this.isAlive = true;
        this.world = _world;        
        newXY = this.world.GetFreeSpot();
        this.x = newXY.x;
        this.y = newXY.y;
        InitFeathures();
        this.world.getView().setNewMessage(this.getClass().getSimpleName() + " is initalized");
        this.world.getView().drawObject(x, y, name);
    }

    protected abstract void InitFeathures();

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int value) {
        this.strength = value;
    }

    public int getInitative() {
        return this.initative;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int value) {
        this.x = value;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int value) {
        this.y = value;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean newStatus) {
        this.isAlive = newStatus;
        if (!newStatus) {
            this.world.getView().setNewMessage(this.getClass().getSimpleName() + " is dead.");
            this.world.getView().deleteObject(this.x, this.y);
        }
    }

    public abstract void action();

    static boolean AIsLessThanB(Organism A, Organism B) {
        return A.getStrength() < B.getStrength();
    }

    void draw(int x, int y) {
        this.world.getView().deleteObject(this.x, this.y);
        this.world.getView().drawObject(x, y, name);
        this.x = x;
        this.y = y;
    }

    public boolean isPushBackAttack(Organism attacker) {
        if (AIsLessThanB(this, attacker)) {
            this.setIsAlive(false);
            return false;
        } else {
            attacker.setIsAlive(false);
            return true;
        }
    }

    void multiplication() {
        int i = 8;
        boolean isMoved = false;

        while (!isMoved && (i-- != 0)) {
            newXY = this.newRandomPositionAround();
            if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.OPEN) {
                this.world.addCreature(newInstance(newXY.x, newXY.y));
                isMoved = true;
            }
        }
    }

    void moveTo(int desireX, int desireY) {

    }

    protected Point newRandomPositionAround() {
        return this.world.getView().positionAround(this.x, this.y);
    }

    @Override
    public int compareTo(Organism o) {
        return o.initative - this.initative;
    }

}
