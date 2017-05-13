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
public class SowThistle extends Plant{
    public SowThistle(World world) {
        super(world);
    }

    public SowThistle(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new SowThistle(x, y, world);
    }

    @Override
    protected void InitFeathures() {
        super.InitFeathures();
        this.name = "st";
    }
    
    @Override
    public void action()
    {
        for (int i = 0; i < 3; i++) {
            super.action();
        }
    }
}
