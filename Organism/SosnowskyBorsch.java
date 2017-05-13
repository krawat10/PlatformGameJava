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
public class SosnowskyBorsch extends Plant {

    public SosnowskyBorsch(World world) {
        super(world);
    }

    public SosnowskyBorsch(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new SosnowskyBorsch(x, y, world);
    }

    @Override
    protected void InitFeathures() {
        super.InitFeathures();
        this.name = "SX";
    }

    @Override
    public void action() {
        int numberOfFieldsAround = this.world.getView().getNumberOfFieldsAroundOneField();
        for (int i = 0; i < numberOfFieldsAround; i++) {
            Point nextField = this.world.getView().getPositionAround(x, y, i);
            if (world.checkPosition(nextField.x, nextField.y) == World.positionStatus.MONSTER) {
                if (world.getCrature(nextField.x, nextField.y) instanceof Animal) {
                    Animal animal = (Animal) world.getCrature(nextField.x, nextField.y);
                    if (!animal.getName().equals(this.getName()) && !(animal instanceof CyberSheep)) {
                        animal.setIsAlive(false);
                    }
                }
            }

        }
        super.action();
    }

    @Override
    public boolean isPushBackAttack(Organism attacker) {
        if (attacker instanceof CyberSheep) {
            this.setIsAlive(false);
            return false;
        } else {
            attacker.setIsAlive(false);
            return true;
        }
    }
}
