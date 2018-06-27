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
        setMatrix();
    }
    private void setMatrix(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(j==1 & i>0 & i<3){
                    this.m[rotationPos][i][j] = true;
                }
                else this.m[rotationPos][i][j] = j==2 & i < 2;
            }
        }
    }
}