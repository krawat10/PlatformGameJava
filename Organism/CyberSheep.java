/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Organism;

import java.awt.List;
import static java.lang.Math.abs;
import platformgame.World;
import java.util.ArrayList;

/**
 *
 * @author Mateusz
 */
public class CyberSheep extends Animal{
    public CyberSheep(int x, int y, World world) {
        super(x, y, world);
    }

    public CyberSheep(World world) {
        super(world);
    }    
    
    @Override
    public void action()
    {               
	int currentDirectionX;
	int currentDirectionY;
        java.util.List<Organism> list = world.getList();
	for (int i = 0; i < list.size(); i++)
	{
		if (list.get(i).getName() == "sb")
		{			
			currentDirectionX = 0;
			currentDirectionY = 0;			
			if (this.getX() != list.get(i).getX())
			{
				currentDirectionX = (this.getX() > list.get(i).getX()) ? (-1) : 1;
			}				
			if (this.getY() != list.get(i).getY())
			{
				currentDirectionY = (this.getY() > list.get(i).getY()) ? (-1) : 1;
			}							
				newXY[0] = this.getX() + currentDirectionX;
				newXY[1] = this.getY() + currentDirectionY;			
                        move();
                        return;
		}
	}
        super.action();
    }
    
    @Override
    protected void InitFeathures() {
        this.name = "$h";
	this.initative = 4;
	this.strength = 11;	
    }

    @Override
    protected Organism newInstance(int x, int y) {
       return new CyberSheep(x, y, this.world);
    }
}
