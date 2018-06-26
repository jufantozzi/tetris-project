/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import util.ImageLoader;
import java.awt.image.BufferedImage;

/**
 *
 * @author ju
 */
public class Assets {
    public static BufferedImage backgroundPiece, pieceI,pieceJ, pieceL, pieceO, 
            pieceS, pieceT, pieceZ, topScreen, pauseScreenQ, pauseScreenR;
    
    
    public static void init(){
        backgroundPiece = ImageLoader.loadImage("/boardBackground.png");        
        pieceI = ImageLoader.loadImage("/pieceI.png");
        pieceJ = ImageLoader.loadImage("/pieceJ.png");
        pieceL = ImageLoader.loadImage("/pieceL.png");
        pieceO = ImageLoader.loadImage("/pieceO.png");
        pieceS = ImageLoader.loadImage("/pieceS.png");
        pieceT = ImageLoader.loadImage("/pieceT.png");
        pieceZ = ImageLoader.loadImage("/pieceZ.png");
        topScreen = ImageLoader.loadImage("/topScreen.png");
        pauseScreenQ = ImageLoader.loadImage("/pauseScreenQ.png");
        pauseScreenR = ImageLoader.loadImage("/pauseScreenR.png");

    }
}
