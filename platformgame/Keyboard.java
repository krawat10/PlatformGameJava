/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Mateusz
 */
public class Keyboard extends KeyAdapter implements IObservable{
    
    ArrayList<IObserver> subscribers;
    KeyName activeKey; 

    
    public Keyboard() 
    {
    subscribers = new ArrayList<>();

           
    }
    
    
    public enum KeyName
    {
    UP, DOWN, LEFT, RIGHT;
    }
    
    @Override
    public void AddSubscriber(IObserver newObserver) {
        subscribers.add(newObserver);
    }

    @Override
    public void RemoveSubscriber(IObserver observerToDelete) {
        subscribers.remove(observerToDelete);
    }

    @Override
    public void NotifySubscribers() {
        System.out.println("Notifuy");
        for(IObserver subscriber : subscribers)
        {
            subscriber.update(activeKey);
        }
    }
    
    @Override
        public void keyPressed(KeyEvent event) {        
         
            int keyCode = event.getKeyCode();
            if (keyCode == event.VK_LEFT)
            {
                activeKey = KeyName.LEFT;
            }
            if (keyCode == event.VK_RIGHT)
            {
                activeKey = KeyName.RIGHT;
            }
            if (keyCode == event.VK_UP)
            {
                activeKey = KeyName.UP;
            }
            if (keyCode == event.VK_DOWN)
            {
                activeKey = KeyName.DOWN;
            }
            NotifySubscribers();
            System.out.println("Pressed " + event.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent event) {
        }
}
