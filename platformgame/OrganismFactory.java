/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

import Organism.*;
import javafx.print.Collation;

/**
 *
 * @author Mateusz
 */
public class OrganismFactory {

    private int x;
    private int y;
    private World world;

    public OrganismFactory(int x, int y, World world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }

    public OrganismFactory(World world) {
        this.world = world;
    }

    public Organism getInstance(int x, int y, String name) {
        this.x = x;
        this.y = y;
        switch (name) {
            case "Wolf":
                return new Wolf(x, y, world);
            case "Grass":
                return new Grass(x, y, world);
            case "Sheep":
                return new Sheep(x, y, world);
            case "CyberSheep":
                return new CyberSheep(x, y, world);
            case "Antelope":
                return new Antelope(x, y, world);
            case "Human":
                return new Human(x, y, world);
        }
        return null;
    }

    public Organism getInstance(String name) {
        switch (name) {
            case "Wolf":
                return new Wolf(x, y, world);
            case "Grass":
                return new Grass(x, y, world);
            case "Sheep":
                return new Sheep(x, y, world);
            case "CyberSheep":
                return new CyberSheep(x, y, world);
            case "Antelope":
                return new Antelope(x, y, world);
        }
        return null;
    }
}
