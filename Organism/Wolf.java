/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import platformgame.World;

/**
 *
 * @author Mateusz
 */
public class Wolf extends Animal {

    public Wolf(int x, int y, World world) {
        super(x, y, world);
    }

    public Wolf(World world) {
        super(world);
    }

    @Override
    protected void InitFeathures() {
        this.name = "W";
        this.initative = 5;
        this.strength = 9;
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new Wolf(x, y, this.world);
    }

}
