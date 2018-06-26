/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import States.GameState;
import States.State;
import util.Assets;
import util.Constants;
import game.Screen;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ju
 */
public class Game implements Runnable{
    //Screen frame
    public Screen screen;
    private final String title;
    private final int height, width;
    public int ticks;
    //thread
    private boolean running = false;
    private Thread thread;
    //graphics
    private BufferStrategy bs;
    private Graphics g;
    //
    public State state;
    public GameMap1 map1;
    
    public Game(String title, int height, int width){
        this.title = title;
        this.height = height;
        this.width = width;
        this.ticks = 0;
    }
    
    private void tick(){
        state.tick();
    }
    
    private void render(){
        //clearing the screen
        bs = screen.getCanvas().getBufferStrategy();
        if(bs == null){
            screen.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        //clearing the screen
        
        state.render(g); //actual rendering
        
        //disposing on screen
        bs.show();
        g.dispose();
        
    }
    
    private void init(){
        screen = new Screen(title, width, height);

        state = new GameState(this.screen);        

        screen.setListener((GameState)state);

        State.setState(state);

        Assets.init();
          
          
    }
    
        @Override
    public void run(){
        double delta = 0;
        long now;
        long lastCheck = System.nanoTime();
        long timer = 0; //
        
        init();
        while(running){
            //running at 60 fps
            now = System.nanoTime();
            delta += (now - lastCheck) / Constants.TimePerTick;
            lastCheck = now;
            //if statement runs 60 times per sec
            if(delta>=1){
                tick();
                render();
                delta--;
                ticks++;
            }
        }
        
       stop();
    }
    
    public synchronized void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}