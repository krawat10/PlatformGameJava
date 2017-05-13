/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

import Organism.Antelope;
import Organism.CyberSheep;
import Organism.Grass;
import Organism.Human;
import Organism.SosnowskyBorsch;
import Organism.Wolf;
import View.GridView;
import View.View;

/**
 *
 * @author Mateusz
 */
public class PlatformGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World word = new World(5, 7);
        word.addCreature(new Human(word));
        word.addCreature(new Antelope(word));
       // word.addCreature(new Grass(word));
        word.addCreature(new Wolf(word));
        //word.addCreature(new CyberSheep(word));
        //word.addCreature(new SosnowskyBorsch(word));
        word.drawWorld();

    }

}
