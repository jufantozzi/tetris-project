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
            pieceS, pieceT, pieceZ, topScreen, pauseScreenR, pauseScreenS, pauseScreenQ, //gamestate
            gameOverS,
            menuScreenStart, menuScreenSelect, menuScreenLoad, menuScreenExit;   //menu
            
    
    
    public static void init(){
        backgroundPiece = ImageLoader.loadImage("/boardBackground.png");        
        pieceI = ImageLoader.loadImage("/pieceI.png");
        pieceJ = ImageLoader.loadImage("/pieceJ.png");
        pieceL = ImageLoader.loadImage("/pieceL.png");
        pieceO = ImageLoader.loadImage("/pieceO.png");
        pieceS = ImageLoader.loadImage("/pieceS.png");
        pieceT = ImageLoader.loadImage("/pieceT.png");
        pieceZ = ImageLoader.loadImage("/pieceZ.png");
        pauseScreenR = ImageLoader.loadImage("/pauseScreenR.png");
        pauseScreenS = ImageLoader.loadImage("/pauseScreenS.png");
        pauseScreenQ = ImageLoader.loadImage("/pauseScreenQ.png");
        menuScreenStart = ImageLoader.loadImage("/menuScreenStart.png");
        menuScreenSelect = ImageLoader.loadImage("/menuScreenSelect.png");
        menuScreenLoad = ImageLoader.loadImage("/menuScreenLoad.png");
        menuScreenExit = ImageLoader.loadImage("/menuScreenExit.png");
        gameOverS = ImageLoader.loadImage("/gameOverS.png");
    }
}
