/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import java.util.Random;
import platformgame.World;

/**
 *
 * @author Mateusz
 */
public class Turtle extends Animal {

    public Turtle(int x, int y, World world) {
        super(x, y, world);
    }

    public Turtle(World world) {
        super(world);
    }

    @Override
    public void action() {
        Random random = new Random();
        int isMove = random.nextInt(4);
        if (isMove == 0) {
            super.action();
        }
    }

    @Override
    protected void InitFeathures() {
        this.name = "T";
        this.initative = 5;
        this.strength = 9;
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new Turtle(x, y, this.world);
    }

    @Override
    public boolean isPushBackAttack(Organism attacker) {
        if (attacker.getStrength() >= 5) {
            this.setIsAlive(false);
            return false;
        } else if (attacker instanceof Turtle) {
            multiplication();
            return false;
        } else {
            return true;
        }
    }
}
