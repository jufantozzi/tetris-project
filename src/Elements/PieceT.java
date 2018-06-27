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
public class PieceT extends Element {
    
    public PieceT() {      
        super();
        this.image = ImageLoader.loadImage("/pieceT.png");
        setMatrix(0);
    }
    public void setMatrix(int pos){
        switch(pos){    
            case 0:
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==1 & j==1){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else if(j==2 & i<3){
                            this.m[this.rotationPos][i][j] = true;
                        }
                    }
                }
            break;
            
            case 1:
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==2 & j==2){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else if(i==1 & j>0){
                            this.m[this.rotationPos][i][j] = true;
                        }
                    }
                }
                 break;
            case 2:
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(j==3 & i==1){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else if(j==2 & i<3){
                            this.m[this.rotationPos][i][j] = true;
                        }
                    }
                }
                 break;
            case 3:
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==0 & j==2){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else if(i==1 & j>0){
                            this.m[this.rotationPos][i][j] = true;
                        }
                    }
                }
                 break;
        }
    }
}
