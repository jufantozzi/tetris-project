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
import maps.GameMap1;
import util.Assets;
import util.Constants;
import game.Screen;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.Random;
import maps.GameMap2;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author ju
 */
public class GameState extends State implements Serializable {

    private boolean isPaused;
    static double ticks, gameSpeed;
    public Element curPiece, nextPiece;
    public static GameMap1 map1;
    public static GameMap2 map2;
    private Graphics g;
    Random rand = new Random();
    public static long score;
    private int pauseOption, mapSelect;
    private boolean gameOver;
    
    public GameState(Graphics g, int mapSelect){
        this.curPiece = this.getRandPiece(rand.nextInt(7));
        this.nextPiece = this.getRandPiece(rand.nextInt(7));
        this.pauseOption = 0;
        this.mapSelect = mapSelect;
        this.g = g;
        this.gameOver = false;
        gameSpeed = 1;
        ticks = 0;
        score = 0;
        if(mapSelect == 0) map1 = new GameMap1(g);
        else map2 = new GameMap2(g);
    }
    
    @Override
    public void tick() {
            gameSpeed = 1 + (score / 1000)* 0.3;
            if(!isPaused & !gameOver){
            ticks++;   //gameSpeed >= 60 at gameSpeed = 1, means it will run 1 time per sec
            if(ticks*gameSpeed >= 60){                              //As gameSpeed grows, game gets faster
                if(mapSelect == 0){
                    if(!curPiece.moveDown(curPiece, mapSelect)){                   //if cannot move piece down, draw it on the map
                        map1.updateMap(curPiece);                       //generate a new piece
                        map1.checkLineCompletion();
                        curPiece = nextPiece; 
                        if(!GameMap1.isValid(curPiece)){
                            gameOver = true;
                        }
                        nextPiece = this.getRandPiece(rand.nextInt(7));
                        
                    }
                }
                else if(mapSelect == 1){
                    if(!curPiece.moveDown(curPiece, mapSelect)){                   //if cannot move piece down, draw it on the map
                        map2.updateMap(curPiece);                       //generate a new piece
                        map2.checkLineCompletion();
                        curPiece = nextPiece;
                        if(!GameMap2.isValid(curPiece)){
                            gameOver = true;
                        }
                        nextPiece = this.getRandPiece(rand.nextInt(7)); 
                    }
                }
                ticks = 0;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackScreen(g);
        if(mapSelect == 0) map1.drawMap(g);
                      else map2.drawMap(g);
        
        curPiece.drawThis(g); 
        
        drawNextPiece(nextPiece, g); 
        //desenho do score
        drawScore(g);
        
        if(isPaused){
            this.drawPauseScreen(g);
        }
        if(gameOver){
            this.drawGameOver(g);
        }
    }
    
    private void drawPauseScreen(Graphics g){
        if(pauseOption==0){
            g.drawImage(Assets.pauseScreenQ, 50, 250, 400, 200, null);
        }
        else if(pauseOption == 1){
            g.drawImage(Assets.pauseScreenS, 50, 250, 400, 200, null);
        }
        else{
            g.drawImage(Assets.pauseScreenR, 50, 250, 400, 200, null);
        }
    }
    
    private void drawBackScreen(Graphics g){
        g.drawImage(Assets.backgroundPiece, 0, 0, null);
    }   
    
    private void drawNextPiece(Element element, Graphics g){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(element.m[element.getRotationPos()][i][j]){
                    g.drawImage(element.getImage(), (Constants.cellSize-10)*(16+i)-7, (Constants.cellSize-10)*(23+j)+5,
                            Constants.cellSize-10 ,Constants.cellSize-10 , null);
                }
            }
        }
    }
    
    private void drawScore(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(String.valueOf(score), Constants.cellSize * 11, 485);
    }
    
    public static void setTick(int i){
        ticks=i;
    }

    
    private void drawGameOver(Graphics g){
        g.drawImage(Assets.gameOverS, 180, 200, 250, 180, null);
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
    

    public void saveOption(GameState g){

        try{
            FileOutputStream saveFile = new FileOutputStream("/home/ju/NetBeansProjects/Game/save.dat");

            ObjectOutputStream gameData = new ObjectOutputStream(saveFile);

            gameData.writeObject(g);
            gameData.flush();
            gameData.close();

            saveFile.flush();
            saveFile.close();

            System.out.println("Save done!");  //Colocar no lugar da tela do menu de saída
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void loadOption(){
            
        try{
            FileInputStream loadFile = new FileInputStream("/home/ju/NetBeansProjects/Game/save.dat");

            ObjectInputStream objReader = new ObjectInputStream(loadFile);

            objReader.readObject();
            //System.out.println(objReader.readObject());

            objReader.close();
            loadFile.close();

        }catch(Exception e){
            e.printStackTrace();
        }
            

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(!isPaused && !gameOver){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    curPiece.rotate(curPiece, mapSelect);
                    break;
                case KeyEvent.VK_DOWN:
                    curPiece.moveDown(curPiece, mapSelect);
                    break;
                case KeyEvent.VK_LEFT:
                    curPiece.moveLeft(curPiece, mapSelect);
                    break;
                case KeyEvent.VK_RIGHT:
                    curPiece.moveRight(curPiece, mapSelect);
                    break;  
                case KeyEvent.VK_ESCAPE:
                    this.pauseOption = 0;
                    isPaused = true;
                    break;
                case KeyEvent.VK_SPACE:
                    curPiece.fallDown(curPiece, mapSelect);
                    break;
                default:
                    break;
            }
        }
        else{
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    this.pauseOption++;
                    if(pauseOption == 3) pauseOption = 0;
                    break;
                case KeyEvent.VK_LEFT:
                    this.pauseOption--;
                    if(pauseOption == -1) pauseOption = 2;
                    break;
                case KeyEvent.VK_ENTER:
                    if(gameOver) {
                        Screen.removeListener(State.getState());
                        State.setState(new MenuState(g));
                        Screen.getFrame().addKeyListener(State.getState());
                        gameOver = !gameOver;
                    }
                    switch (pauseOption) {
                        case 0:
                            //if selecting quit game
                            Screen.removeListener(State.getState());
                            State.setState(new MenuState(g));
                            Screen.getFrame().addKeyListener(State.getState());
                            break;
                        case 1://selecting save
                            saveOption(this);
                            //save implementation
                            break;
                        //if selecting resume button
                        case 2:
                            this.isPaused = false;
                            break;
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


