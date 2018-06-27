/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import States.GameState;
import States.State;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.Spring.height;
/**
 *
 * @author ju
 */
public class Screen extends javax.swing.JFrame{
    private static JFrame screen;
    private String title;
    private int widht, height;
    private Canvas canvas;
    
    public Screen(String title, int widht, int height){
        this.title = title;
        this.widht = widht;
        this.height = height;
        screen = new JFrame(title);
        screen.setSize(widht, height);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setResizable(false);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(widht, height));
        canvas.setFocusable(false);
        
        screen.add(canvas);
        screen.pack();
        
    }
    
    public static void setListener(State state){
        screen.addKeyListener(State.getState());
    }
    public static void removeListener(State state){
        screen.removeKeyListener(State.getState());
    }
    
    public Canvas getCanvas(){
        return this.canvas;
    }
    
    static public JFrame getFrame(){
        return screen;
    }
}


