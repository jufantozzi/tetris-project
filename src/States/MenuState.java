 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import game.Screen;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import util.Assets;
import util.Constants;

/**
 *
 * @author ju
 */
public class MenuState extends State implements KeyListener {
    Graphics g;
    int menuOption, ticks;
    private boolean mapSelectScreenOn;
    
    
    public MenuState(Graphics g){
        menuOption = 0;
        this.ticks = 0;
        mapSelectScreenOn = false;
        this.g = g;
    }
    
    
    @Override
    public void tick() {
        ticks++;
        if(this.ticks >= 60){
        }
    }

    @Override
    public void render(Graphics g) {
            
        if(this.menuOption%3 == 0){
            g.drawImage(Assets.menuScreenStart, 0, 0, Constants.screenW, Constants.screenH, null);
        }
        else if(this.menuOption%3 == 1 | this.menuOption%3 == -1 ){
            g.drawImage(Assets.menuScreenLoad, 0, 0, Constants.screenW, Constants.screenH, null);
        }
        else if(this.menuOption%3 == 2 | this.menuOption%3 == -2){
            g.drawImage(Assets.menuScreenExit, 0, 0, Constants.screenW, Constants.screenH, null);
        }
        if(this.mapSelectScreenOn) {
            g.drawImage(Assets.menuScreenSelect, 250, 250, 250, 250, null);
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(mapSelectScreenOn) break;
                this.menuOption--;
                if(menuOption == -1) menuOption = 2;
                break;
            case KeyEvent.VK_DOWN:
                if(mapSelectScreenOn) break;
                this.menuOption++;
                if(menuOption == 4) menuOption = 1;
                break;
            case KeyEvent.VK_1:
                if(mapSelectScreenOn){    
                    Screen.removeListener(State.getState());
                    State.setState(new GameState(g, 0));
                    Screen.getFrame().addKeyListener(State.getState());
                }
                break;
            case KeyEvent.VK_2:
                if(mapSelectScreenOn){
                    Screen.removeListener(State.getState());
                    State.setState(new GameState(g, 1));
                    Screen.getFrame().addKeyListener(State.getState());
                }
                break;
            case KeyEvent.VK_ENTER:
                if(menuOption%3 == 0){ //menuOption%3 => selecting start
                    if(!mapSelectScreenOn) mapSelectScreenOn = !mapSelectScreenOn;
                    
                }
                else if(menuOption%3 == 1){ //load game
                }
                else if(menuOption%3 == 2){
                    System.exit(0);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if(mapSelectScreenOn) mapSelectScreenOn = !mapSelectScreenOn;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
