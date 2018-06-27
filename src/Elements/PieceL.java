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
public class PieceL extends Element {
    
    public PieceL() {
        super();
        this.image = ImageLoader.loadImage("/pieceL.png");
        setMatrix();
    }
    private void setMatrix(){
        for(int i=0;i<4;i++){           
            for(int j=0;j<4;j++){           
                if(i==2 & j==1){
                    this.m[rotationPos][i][j] = true;
                }
                else this.m[rotationPos][i][j] = i!=3 & j==2;
            }
        }
    }
}