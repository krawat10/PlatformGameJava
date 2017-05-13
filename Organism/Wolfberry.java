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
public class Wolfberry extends Plant{
    public Wolfberry(World world) {
        super(world);
    }

    public Wolfberry(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new Wolfberry(x, y, world);
    }

    @Override
    protected void InitFeathures() {
        super.InitFeathures();
        this.name = "wb";
        this.strength = 99;
    }
    
    @Override
    public boolean isPushBackAttack(Organism attacker) {        
            this.setIsAlive(false);           
            attacker.setIsAlive(false);
            return true;        
    }
}
