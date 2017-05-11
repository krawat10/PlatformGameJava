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
public abstract class Plant extends Organism {

    protected int chanceToMultiplication;

    public Plant(World world) {
        super(world);
    }

    public Plant(int x, int y, World world) {
        super(x, y, world);

    }

    @Override
    protected abstract Organism newInstance(int x, int y);

    @Override
    protected void InitFeathures() {
        this.initative = 0;
        chanceToMultiplication = 8;
    }

    @Override
    public void action() {
        if (isAbleToMultiplication()) {
            multiplication();
        }
    }

    boolean isAbleToMultiplication() {
        Random random = new Random();
        return (random.nextInt(chanceToMultiplication - 1) == 1);
    }

}
