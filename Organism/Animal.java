/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import platformgame.World;
import View.*;
import java.awt.Point;

/**
 *
 * @author Mateusz
 */
public abstract class Animal extends Organism {
   
    //protected Point newXY;
    public Animal(int x, int y, World world) {
        super(x, y, world);
    }

    public Animal(World world) {
        super(world);
    }

    @Override
    public void action() {
        boolean isMoved = false;
        int count = 20;
        while (!isMoved && (count-- > 0)) {
            newXY = this.newRandomPositionAround();
            isMoved = move();

        }
    }

    protected boolean move() {
        if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.OPEN) {
            this.draw(newXY.x, newXY.y);
            return true;
        } else if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.MONSTER) {
            if (this.attack(newXY.x, newXY.y)) {
                this.draw(newXY.x, newXY.y);
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    protected boolean moveToOpenPosition() {
        if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.OPEN) {
            this.draw(newXY.x, newXY.y);
            return true;
        }
        return false;
    }

    boolean attack(int enemyX, int enemyY) {
        return this.world.attackMonsterAtPosition(enemyX, enemyY, this);
    }

    @Override
    public boolean isPushBackAttack(Organism attacker) {
        if (attacker.getClass().isInstance(this)) {
            multiplication();
            return true;
        } else {
            return super.isPushBackAttack(attacker);
        }
    }

}
