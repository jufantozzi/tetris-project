/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import java.awt.Graphics;
import Elements.Element;
import Elements.PieceI;
import Elements.PieceJ;
import Elements.PieceL;
import Elements.PieceO;
import Elements.PieceS;
import Elements.PieceT;
import Elements.PieceZ;
import control.GameMap1;
import util.Assets;
import util.Constants;
import game.Screen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author ju
 */
public class GameState extends State implements KeyListener{

    private boolean pauseOption, isPaused;
    private int ticks, gameSpeed;
    public final ArrayList<Element> pieceArray = new ArrayList();
    public Element curPiece;
    public static GameMap1 map1;
    Random rand = new Random();
    int x;
    public GameState(Screen screen){
        ticks = 0;
        this.curPiece = this.getRandPiece(rand.nextInt(7));
        this.gameSpeed = 1;
        this.pauseOption = false;
        this.isPaused = false;
    }
    
    @Override
    public void tick() {
        if(!isPaused){
            ticks++;
            if(ticks/gameSpeed >= 60){                              //game speed. As gameSpeed grows, game gets faster
                if(!curPiece.moveDown(curPiece)){                   //if cannot move piece down, draw it on the map
                    map1.drawOnMap(curPiece);                       //generate a new piece
                    map1.checkLineCompletion();
                    curPiece = this.getRandPiece(rand.nextInt(7)); 
                }
                ticks = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(map1 == null){
            map1 = new GameMap1(g);
        }
        drawBackScreen(g);
        map1.drawMap(g);
        curPiece.drawThis(g); //desenha peça na tela
        
        if(isPaused){
            this.drawPauseScreen(g);
        }
    }
    
    private void drawPauseScreen(Graphics g){
        if(!pauseOption){
            g.drawImage(Assets.pauseScreenR, 200, 250, 200, 200, null);
        }
        else{
            g.drawImage(Assets.pauseScreenQ, 200, 250, 200, 200, null);
        }
    }
    
    private void drawBackScreen(Graphics g){
        BufferedImage backgroundImage, topScreen = Assets.topScreen;
        
        g.drawImage(topScreen, 0, 0, 400, 100, null);
        
        backgroundImage = Assets.backgroundPiece;
        //draws background static pieces
        for(int i = 0; i < (Constants.screenW/Constants.cellSize) - Constants.sideOffset; i++){
            
            for(int j = Constants.downOffset; j < Constants.screenH/Constants.cellSize; j++){ //j = 3, backgrounscreen starts 3 tiles down
               
                g.drawImage(backgroundImage, i * Constants.cellSize, j * Constants.cellSize,
                        Constants.cellSize, Constants.cellSize, null);
            } 
        }
        //draws background static pieces       
    }
    
    public static boolean isValid(Element element){
        if(element.getLowestY() >= Constants.yMaxPos){ //check bottom screen border
            return false;
        }
        if(element.getLowestX() <= 0){ //check left side screen border
            return false;
        }
        if(element.getHighestX() >= Constants.xMaxPos){//check right screen border
            return false;
        }
        for(int i=0;i<4;i++){ //check collision with other objects in map
            
            for(int j=0;j<4;j++){
                
                if(element.m[element.getRotationPos()][i][j]){
                    if(GameMap1.map[i+element.getXPos()][j+element.getYPos()] != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
        
    private Element getRandPiece(int n){
        switch(n){
            case 0: return new PieceI();
            case 1: return new PieceJ();
            case 2: return new PieceL();
            case 3: return new PieceO();
            case 4: return new PieceS();
            case 5: return new PieceT();
            case 6: return new PieceZ();
            default: return null;
        }
    }
    
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(!isPaused){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    curPiece.rotate(curPiece);
                    break;
                case KeyEvent.VK_DOWN:
                    curPiece.moveDown(curPiece);
                    break;
                case KeyEvent.VK_LEFT:
                    curPiece.moveLeft(curPiece);
                    break;
                case KeyEvent.VK_RIGHT:
                    curPiece.moveRight(curPiece);
                    break;  
                case KeyEvent.VK_ESCAPE:
                    isPaused = true;
                    break;
                default:
                    break;
            }
        }
        else{
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    this.pauseOption = !this.pauseOption;
                    break;
                case KeyEvent.VK_DOWN:
                    this.pauseOption = !this.pauseOption;
                    break;
                case KeyEvent.VK_ENTER:
                    if(pauseOption) {
                        
                    }
                    else{
                        this.isPaused = false;
                    } 
                        
                    break;      
                case KeyEvent.VK_ESCAPE:
                    isPaused = false;
                    break;
                case KeyEvent.VK_Q:
                    isPaused = false;
                    break;
                default:
                    break;
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}


