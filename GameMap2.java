/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import Elements.Element;
import Elements.PieceI;
import Elements.PieceJ;
import Elements.PieceL;
import Elements.PieceO;
import Elements.PieceS;
import Elements.PieceT;
import Elements.PieceZ;
import States.GameState;
import static States.GameState.map1;
import static States.GameState.score;
import java.awt.Font;
import util.Assets;
import java.awt.Graphics;
import util.Constants;


/**
 *
 * @author ju
 */
public class GameMap2 {
    public static int[][] map = new int[10][18]; // primeiro numero: >> <<  / segundo numero: up, down
    private Graphics g;
    
    
    public GameMap2(Graphics g) {
        this.g = g;
    }
    
    public int[][] getMap() {
        return this.map;
    }
    
    public void drawMap(Graphics g){
        setCustomMap();
        
        for(int i=0;i<10;i++){
            for(int j=0;j<18;j++){
                if(map[i][j] > 0){
                    switch (map[i][j]) {
                        case Constants.pI:
                            g.drawImage(Assets.pieceI, ((i) * Constants.cellSize) , ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        case Constants.pJ:
                            g.drawImage(Assets.pieceJ, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        case Constants.pL:
                            g.drawImage(Assets.pieceL, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        case Constants.pO:
                            g.drawImage(Assets.pieceO, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        case Constants.pS:
                            g.drawImage(Assets.pieceS, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        case Constants.pT:
                            g.drawImage(Assets.pieceT, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        case Constants.pZ:
                            g.drawImage(Assets.pieceZ, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                                    Constants.cellSize, Constants.cellSize, null);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    public static boolean isValid(Element element){
        if(element.getHighestY() < -1){ //check bottom screen border
            return false;
        }
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
                //necessario: posicao do elemento; mapa;
                if(element.m[element.getRotationPos()][i][j]){
                    if(map[i+element.getXPos()][j+element.getYPos()] != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void updateMap(Element piece){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(piece.m[piece.getRotationPos()][i][j]){
                    if(piece instanceof PieceI){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pI;
                    }
                    else if(piece instanceof PieceJ){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pJ;
                    }
                    else if(piece instanceof PieceL){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pL;
                    }
                    else if(piece instanceof PieceO){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pO;
                    }
                    else if(piece instanceof PieceS){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pS;
                    }
                    else if(piece instanceof PieceT){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pT;
                    }
                    else if(piece instanceof PieceZ){
                        map[i + piece.getXPos()][j + piece.getYPos()] = Constants.pZ;
                    }
                }
            }
        }
    }
    
    public void checkLineCompletion(){
        int completedLines = 0;
        for(int j=17;j>=0;j--){
            int aux = 0;
            
            for(int i=0;i<10;i++){
                if(map[i][j] == 0){  //caso algum quadrado da linha seja igual a zero, não quebrar a linha
                    aux++;
                }
            }
            if(aux == 0){           //caso todas as linhas sejam diferentes de zero, quebrar a linha
                completedLines++;
                for(int k=j;k>0;k--){//nesse caso, k>0 vai ate a penultima posicao (ultima posicao nao sobrepoe linha)
                    for(int i=0;i<10;i++){
                        map[i][k] = map[i][k-1];
                    }
                }
                for(int i=0;i<10;i++){
                    map[i][0] = 0;
                }
                j++;
            }
        }
        switch(completedLines){
            case 1: GameState.score += 50;
                    break;
            case 2: GameState.score += 200;
                    break;
            case 3: GameState.score += 800;
                    break;
            case 4: GameState.score += 3200;
                    break;
        }
        
    }

    
    private void setCustomMap() {
        map[3][9] = 1;
        map[3][8] = 1;
        map[2][9] = 1;
        map[7][8] = 1;
        map[6][9] = 1;
        map[7][9] = 1;
        map[6][8] = 1;
        map[2][8] = 1;
    }
}
