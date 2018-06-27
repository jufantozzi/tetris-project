/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Elements.Element;
import Elements.PieceI;
import Elements.PieceJ;
import Elements.PieceL;
import Elements.PieceO;
import Elements.PieceS;
import Elements.PieceT;
import Elements.PieceZ;
import util.Assets;
import java.awt.Graphics;
import util.Constants;


/**
 *
 * @author ju
 */
public class GameMap1 {
    public static  int[][] map = new int[10][15]; // primeiro numero: >> <<  / segundo numero: up, down
    private Graphics g;
    
    public GameMap1(Graphics g) {
        this.g = g;
    }
    
    public void drawMap(Graphics g){
        for(int i=0;i<10;i++){
            for(int j=0;j<15;j++){
                if(map[i][j] > 0){
                    if(map[i][j] == Constants.pI){
                        g.drawImage(Assets.pieceI, ((i) * Constants.cellSize) , ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                    else if(map[i][j] == Constants.pJ){
                        g.drawImage(Assets.pieceJ, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                    else if(map[i][j] == Constants.pL){
                        g.drawImage(Assets.pieceL, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                    else if(map[i][j] == Constants.pO){
                        g.drawImage(Assets.pieceO, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                    else if(map[i][j] == Constants.pS){
                        g.drawImage(Assets.pieceS, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                    else if(map[i][j] == Constants.pT){
                        g.drawImage(Assets.pieceT, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                    else if(map[i][j] == Constants.pZ){
                        g.drawImage(Assets.pieceZ, ((i) * Constants.cellSize), ((j+Constants.downOffset)* Constants.cellSize),
                            Constants.cellSize, Constants.cellSize, null);
                    }
                }
            }
        }
    }
    
    public void drawOnMap(Element piece){
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
        
        for(int j=14;j>=0;j--){
            int aux = 0;
            
            for(int i=0;i<10;i++){
                if(map[i][j] == 0){  //caso algum quadrado da linha seja igual a zero, não quebrar a linha
                    aux++;
                }
            }
            if(aux == 0){           //caso todas as linhas sejam diferentes de zero, quebrar a linha
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
        
    }
}
