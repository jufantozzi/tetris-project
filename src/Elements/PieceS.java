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
public class PieceS extends Element {
    
    public PieceS() {
        super();
        this.image = ImageLoader.loadImage("/pieceS.png");
        setMatrix(0);
    }
    public void setMatrix(int pos){
        switch(pos){
            case 0:
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(j==1 & i>0 & i<3){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = j==2 & i < 2;
                    }
                }
                break;
        
            case 1:
//            rotatePos++;
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==2 & j>1){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = i==1 & j>0 & j<3;
                    }
                }
                break;
                
            case 2:
//           rotatePos++;
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(j==3 & i<2){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = j==2 & i>0 & i<3;
                    }
                }
                break;
            case 3:
//                rotatePos++
                for(int i=0;i<4;i++){
                    for(int j=0;j<4;j++){
                        if(i==0 & j>0 & j<3){
                            this.m[this.rotationPos][i][j] = true;
                        }
                        else this.m[this.rotationPos][i][j] = i==1 & j>1;
                    }
                }
                break;
        }
    }
}

