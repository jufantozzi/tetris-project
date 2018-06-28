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
import java.util.HashSet;
import maps.GameMap1;
import maps.GameMap2;


public abstract class Element{
    protected BufferedImage image;
    protected int xPos, yPos, rotationPos;
    public boolean[][][] m = new boolean[4][4][4];
    Graphics g;

    public Element(){
        rotationPos = 0;
        this.xPos = 3;
        this.yPos = -1;  //(GUIimg space)
    }

    public abstract void setMatrix(int pos);

    
    public void drawThis(Graphics g){    
        for(int i=0;i<4;i++){
 
            for(int j=0;j<4;j++){
                
                if(m[rotationPos][i][j]){
                
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
                if(m[rotationPos][i][j]){
                    if(j > maxVal)
                    maxVal = j;
                }
            }
        }
        return (maxVal + this.getYPos() -1);
    }
    
    public int getHighestY() {
        int maxVal = 3;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(m[rotationPos][i][j]){
                    if(maxVal > j)
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
                if(m[rotationPos][i][j]){
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
                if(m[rotationPos][i][j]){
                    maxVal = i;
                }
            }
        }
        return (maxVal + this.getXPos() - 1);
    }
    
    public BufferedImage getImage(){
        return this.image;
    }
    
    public int getRotationPos(){
        return rotationPos;
    }
    
    public boolean moveRight(Element e, int mapSelect){
        if(mapSelect == 0){
            this.xPos += 1;
            if(GameMap1.isValid(e)){
                return true;
            }
            else{
                this.xPos--;
                return false;
            }
        }
        else{
            this.xPos += 1;
            if(GameMap2.isValid(e)){
                return true;
            }
            else{
                this.xPos--;
                return false;
            }
        }
    }
    
    public boolean moveLeft(Element e, int mapSelect){
        if(mapSelect == 0){
            this.xPos -= 1;
            if(GameMap1.isValid(e)){
                return true;
            }
            else{
                this.xPos++;
                return false;
            }
        }
        else{
            this.xPos -= 1;
            if(GameMap2.isValid(e)){
                return true;
            }
            else{
                this.xPos++;
                return false;
            }
        }
    }
    
    public boolean moveDown(Element e, int mapSelect){
        if(mapSelect == 0){
            this.yPos += 1;
            if(GameMap1.isValid(e)){
                return true;
            }
            else{
                this.yPos--;
                return false;
            }
        }
        else{
            this.yPos += 1;
            if(GameMap2.isValid(e)){
                return true;
            }
            else{
                this.yPos--;
                return false;
            }
        }
    }
    
    public void fallDown(Element e, int mapSelect){
        if(mapSelect == 0){
            this.yPos += 1;
            while(GameMap1.isValid(e)){
                this.yPos++;
            }
            this.yPos--;
            GameState.setTick(60);
        }
        else{
            this.yPos += 1;
            while(GameMap2.isValid(e)){
                this.yPos++;
            }
            this.yPos--;
            GameState.setTick(60);
        }
    }
    
    public void rotate(Element piece, int mapSelect){
        piece.rotationPos++;
        if(this.rotationPos == 4) this.rotationPos = 0;
        piece.setMatrix(this.rotationPos);
        if(mapSelect == 0){
            if(!GameMap1.isValid(piece)) this.rotationPos--;
            if(this.rotationPos == -1) this.rotationPos = 3;
        }
        else{
            if(!GameMap2.isValid(piece)) this.rotationPos--;
            if(this.rotationPos == -1) this.rotationPos = 3;
        }
    }
}
