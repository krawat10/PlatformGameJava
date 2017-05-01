/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Mateusz
 */
public interface IView {    
    
    public void RefreshScreen();
    public void drawObject(char symbolOfObject, int x, int y);
    public void deleteObject(int x, int y);
    public void setVisibility();
}
