/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformgame;

/**
 *
 * @author Mateusz
 */
public interface IObservable {
    public void AddSubscriber(IObserver newObserver);
    public void RemoveSubscriber(IObserver observerToDelete);
    public void NotifySubscribers();
}
