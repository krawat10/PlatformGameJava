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
public class Fox extends Animal {
    public Fox(int x, int y, World world) {
        super(x, y, world);
    }

    public Fox(World world) {
        super(world);
    }
    
    private boolean isStronger()
    {
        if(world.getCrature(newXY.x, newXY.y) instanceof Fox)
        {            
            return true;
        }
        else if(this.getStrength() > world.getCrature(newXY.x, newXY.y).getStrength())
        {
            return true;
        }
        
        else 
        {
            System.out.println("Fox is running from bigger animals");
            return false;
        }
    }

    @Override
    protected boolean move() {
        if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.OPEN) {
            this.draw(newXY.x, newXY.y);
            return true;
        } else if (this.world.checkPosition(newXY.x, newXY.y) == World.positionStatus.MONSTER) {
            if(isStronger())
            {           
            if (this.attack(newXY.x, newXY.y)) {
                this.draw(newXY.x, newXY.y);
                return true;
            } else {                
                return true;
            }
            }
        }
        return false;
    }
    
    @Override
    protected void InitFeathures() {
        this.name = "F";
        this.initative = 5;
        this.strength = 9;
    }

    @Override
    protected Organism newInstance(int x, int y) {
        return new Fox(x, y, this.world);
    }
}
