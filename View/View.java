/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 *
 * @author Mateusz
 */
public class View extends JFrame implements IView{
    
    public View() 
    {
        initUI();
    }

    private void initUI()
    {        
        setTitle("Simple example");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setVisibility()
    {
        EventQueue.invokeLater(() -> {
            View ex = new View();
            ex.setVisible(true);
        });
    }
}
