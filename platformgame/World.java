/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

import Organism.Organism;
import View.GridView;
import View.*;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;


/**
 *
 * @author Mateusz
 */
public class World implements IObserver{

private void serialize(World world){}
private	void deserialize(World world){}
private	void save(){}
private	void load(){}
private	void ClearDeadMonster(){}	
private	int sizeX;
private	int sizeY;
private	 Keyboard input;
private	int numberOfCreatures = 0;
private	Organism[] organism;
private	View view;    


public World(int sizeX, int sizeY)
{
 view = GridView.getInstance();
 view.setSizeX(10);
 view.setSizeY(10);
 view.setVisibility();
 input = view.getKeyListener(); 
 input.AddSubscriber(this);
}

//public	bool attackMonsterAtPosition(int x, int y, Organism attacker){}

//public	Organism getCrature(int x, int y)
//{
    
//}

public	void drawWorld()
{
    
}

public	void tour(){}

public void setNumberOfCreatures(int newNumberOfCreatures)
{
    this.numberOfCreatures = newNumberOfCreatures;
}

public	int getNumberOfCreatures()
{
    return numberOfCreatures;
}

public	void addCreature(Organism newOrganism)
{

}

public	char checkPosition(int x, int y)
{
    return 'm';
}

public	View getView()
{
    return view;
}
        
//public	int* GetFreeSpot(){}
//public	Organism[] getList(){}
//public	int getImput(){}

    @Override
    public void update(Object object) 
    {
        this.getView().drawNewObject(2, 2);
    }
}
