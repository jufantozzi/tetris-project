/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import States.GameState;
import util.Constants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Element{
    protected BufferedImage image;
    protected int xPos, yPos, rotationPos;
    public boolean[][] m = new boolean[4][4];
    
    public Element(){
        this.xPos = 3;
        this.yPos = -1;  //(GUIimg space)
    }
    
    public void drawThis(Graphics g){    
        for(int i=0;i<4;i++){
 
            for(int j=0;j<4;j++){
                
                if(m[i][j]){
                
                    g.drawImage(image, ((i + this.xPos) * Constants.cellSize) + 2, ((j + this.yPos + Constants.downOffset)* Constants.cellSize)+1,
                            Constants.cellSize, Constants.cellSize, null);
                }
            }
        }
    }
    
    public int getXPos(){
        return this.xPos;
    }
   
    public int getYPos(){
        return this.yPos;
    }
    
    public void setXPos(int x){
        this.xPos = x;
        //if(isValidPosition) return true else false
    }
    
    public void setYPos(int y){
        this.yPos = y;
        //if(isValidPosition) return true else false
    }
    
    public int getLowestY() {
        int maxVal = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(m[i][j]){
                    if(j > maxVal)
                    maxVal = j;
                }
            }
        }
        return (maxVal + this.getYPos() -1);
    }

    public int getLowestX() {
        int minVal = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(m[i][j]){
                    return (i + this.getXPos()+1);
                }
            }
        }
        return 0;
    }

    public int getHighestX() {
        int maxVal = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(m[i][j]){
                    maxVal = i;
                }
            }
        }
        return (maxVal + this.getXPos() - 1);
    }
    
    public boolean moveRight(Element e){
        this.xPos += 1;
        if(GameState.isValid(e)){
            return true;
        }
        else{
            this.xPos--;
            return false;
        }
    }
    
    public boolean moveLeft(Element e){
        this.xPos -= 1;
        if(GameState.isValid(e)){
            return true;
        }
        else{
            this.xPos++;
            return false;
        }
    }
    
    public boolean moveDown(Element e){
        this.yPos += 1;
        if(GameState.isValid(e)){
            return true;
        }
        else{
            this.yPos--;
            return false;
        }
    }
    
    public void rotate(Element piece){
        /*if(piece instanceof PieceI){
        
        }
        else if(piece instanceof PieceJ){
        
        }
        else if(piece instanceof PieceL){
        
        }
        else if(piece instanceof PieceO){
        
        }
        else if(piece instanceof PieceS){
        
        }
        else if(piece instanceof PieceT){
        
        }
        else if(piece instanceof PieceZ){
        
        }*/
    }

}
