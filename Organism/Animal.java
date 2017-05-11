/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import platformgame.World;
import View.*;

/**
 *
 * @author Mateusz
 */
public abstract class Animal extends Organism {

    protected int[] newXY = new int[2];

    public Animal(int x, int y, World world) {
        super(x, y, world);
    }

    public Animal(World world) {
        super(world);
    }

    @Override
    public void action() {
        boolean isMoved = false;
        while (!isMoved) {
            newXY = this.newRandomPositionAround();
            isMoved = move();

        }
    }

    protected boolean move() {
        if (this.world.checkPosition(newXY[0], newXY[1]) == World.positionStatus.OPEN) {
            this.draw(newXY[0], newXY[1]);
            return true;
        } else if (this.world.checkPosition(newXY[0], newXY[1]) == World.positionStatus.MONSTER) {
            if (this.attack(newXY[0], newXY[1])) {
                this.draw(newXY[0], newXY[1]);
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    boolean attack(int enemyX, int enemyY) {
        return this.world.attackMonsterAtPosition(enemyX, enemyY, this);
    }

    @Override
    public boolean isPushBackAttack(Organism attacker) {
        if (this.getName().equals(attacker.getName())) {
            multiplication();
            return true;
        } else {
            return super.isPushBackAttack(attacker);
        }
    }
}
