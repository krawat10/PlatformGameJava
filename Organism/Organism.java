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
public class Organism {
    
    	protected boolean isAlive;
	protected World world;
	protected int strength;
	protected int initative;
	protected char symbol;
	protected int x;
	protected int y;
	protected static int counter;
	protected String name;
        
        //Constructors
    	public Organism(int x, int y, World world)
        {
            
        }
        
	public Organism(World world)
        {
            
        }        
	
        //Methods
	public int getStrength()
        {
            return this.strength;
        }
        
	public void setStrength(int value)
        {
            this.strength = value;
        }
        
	public int getInitative()
        {
            return this.initative;
        }
        
	public int getX()
        {
            return this.x;
        }
        
	public void setX(int value)
        {
            this.x = value;
        }
        
	public int getY()
        {
            return this.y;
        }
        
        public void setY(int value)
        {
            this.y = value;
        }
        
	public String getName()
        {
            return this.name;
        }
        
	public boolean getIsAlive()
        {
            return this.isAlive;
        } 
               
	public void setIsAlive(boolean newStatus)
        {
            this.isAlive = newStatus;
        }
        
	public void action()
        {
            
        }
        
	static boolean AIsLessThanB (Organism A, Organism B)
        {
            return A.getStrength() < B.getStrength();
        }
        
	protected boolean isPushBackAttack(Organism attacker)
        {
            return AIsLessThanB(attacker, this);
        }
        
	void moveTo(int desireX, int desireY)
        {
        
        }
        
        protected int newRandomPositionAround(int x, int y)
        {
            return 0;
        }

}
