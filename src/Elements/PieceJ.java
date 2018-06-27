/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elements;

import util.ImageLoader;

/**
 *
 * @author ju
 */
public class PieceJ extends Element {
    
    public PieceJ() {
        super();
        this.image = ImageLoader.loadImage("/pieceJ.png");
        setMatrix(0);
    }

    public void setMatrix(int pos){
        switch(pos){
            case 0:    
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==0 & j==1){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = i!=3 & j==2;
                    }
                }
                break;
                
            case 1:
                //this.rotationPos++;
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==2 && j==1){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = i==1 & j>=1;
                    }
                }
                break;
            case 2:
                //this.rotationPos++;
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==2 && j==3){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = i!=3 & j==2;
                    }
                }
                break;
            case 3:
                //this.rotationPos++;
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==0 && j==3){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = i==1 & j>0;
                    }
                }
                break;
        }
    }
}