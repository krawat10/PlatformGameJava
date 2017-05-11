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
                int[] newXY = this.newRandomPositionAround();
                if (this.world.checkPosition(newXY[0], newXY[1]) == World.positionStatus.OPEN) {
                    attacker.draw(newXY[0], newXY[1]);
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
        int[] newXY = new int[2];

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
            newXY = this.world.getView().newDirection(x, y, direcrion);
        

        if (this.world.checkPosition(newXY[0], newXY[1]) == World.positionStatus.OPEN) {
            this.draw(newXY[0], newXY[1]);
        } else if (this.world.checkPosition(newXY[0], this.newXY[1]) == World.positionStatus.MONSTER) {
            if (this.attack(newXY[0], this.newXY[1])) {
                this.draw(newXY[0], this.newXY[1]);
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
