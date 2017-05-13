/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import platformgame.Keyboard;
import platformgame.World;

/**
 *
 * @author Mateusz
 */
public class Human extends Animal {

    int AlzureShieldTour;
    boolean AlzureShield;

    public Human(World world) {
        super(world);
    }

    public Human(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    protected void InitFeathures() {
        this.name = "H";
        this.initative = 4;
        this.strength = 5;
    }

    public boolean getAzureShield() {
        return AlzureShield;
    }

    public int getAzureShieldTour() {
        return AlzureShieldTour;
    }

    public void setAzureShield(boolean AlzureShield) {
        this.AlzureShield = AlzureShield;
    }

    public void setAzureShieldTour(int tours) {
        this.AlzureShieldTour = tours;
    }

    @Override
    void multiplication() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPushBackAttack(Organism attacker) {
        if (this.AlzureShield) {
            int i = 8;
            boolean isNewSpot = false;
            while (!isNewSpot && (i-- != 0)) {
                newXY = this.newRandomPositionAround();
                if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.OPEN) {
                    attacker.draw(newXY.x, newXY.y);
                    isNewSpot = true;
                }
            }
            return true;
        } else {
            return super.isPushBackAttack(attacker);
        }
    }

    @Override
    public void action() {
        Keyboard.KeyName direcrion = this.world.getImput();
        if (direcrion == Keyboard.KeyName.DISACTIVATED) {
            return;
        }
        

        this.AlzureShieldTour--;
        if ((this.AlzureShieldTour == 0) && this.AlzureShield) {
            this.AlzureShield = false;
            this.world.getView().setNewMessage("Alzure Shield deactivated.");
        }

        if (direcrion == Keyboard.KeyName.SHIELD) {
            if (this.AlzureShieldTour <= -5) {
                this.AlzureShield = true;
                this.AlzureShieldTour = 5;
                this.world.getView().setNewMessage("Azure shield activated!");
            }
        } else {
            this.newXY = this.world.getView().newDirection(x, y, direcrion);
        

        if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.OPEN) {
            this.draw(newXY.x, newXY.y);
        } else if (this.world.checkPosition(this.newXY.x, this.newXY.y) == World.positionStatus.MONSTER) {
            if (this.attack(newXY.x, this.newXY.y)) {
                this.draw(newXY.x, this.newXY.y);
            } else {
                return;
            }
        }
        }
    }

    @Override
    protected Organism newInstance(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
