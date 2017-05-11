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
public class Antelope extends Animal
{

    public Antelope(int x, int y, World world) {
        super(x, y, world);
    }

    public Antelope(World world) {
        super(world);
    }
    
    @Override
    public void action()
    {
        for (int i = 0; i < 2; i++) {
            super.action();
        }
    }
    
    @Override
    public boolean isPushBackAttack(Organism attacker)
    {
        Random random = new Random();
        int chance = random.nextInt(1);
        if((chance == 0) && (attacker.getName() != this.name))
        {
            super.action();
            return false;
        }
        else
        {
            return super.isPushBackAttack(attacker);
        }
    }
    
    @Override
    protected Organism newInstance(int x, int y) {
        return new Antelope(x, y, world);
    }

    @Override
    protected void InitFeathures() {
        this.name = "An";
	this.initative = 4;
	this.strength = 4;
    }
    
}
