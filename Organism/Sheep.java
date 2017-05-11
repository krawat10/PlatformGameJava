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
public class Sheep extends Animal
{
    
     public Sheep(int x, int y, World world) {
        super(x, y, world);
    }

    public Sheep(World world) {
        super(world);
    }    

    @Override
    protected void InitFeathures() {
        this.name = "Sh";
	this.initative = 5;
	this.strength = 9;	
    }

    @Override
    protected Organism newInstance(int x, int y) {
       return new Sheep(x, y, this.world);
    }
    
}
