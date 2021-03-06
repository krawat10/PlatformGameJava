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
public class Grass extends Plant {

    public Grass(World world) {
        super(world);
    }

    public Grass(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new Grass(x, y, world);
    }

    @Override
    protected void InitFeathures() {
        super.InitFeathures();
        this.name = "g";
    }

}
