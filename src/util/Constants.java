/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author ju
 */
    public class Constants {
    //FPS CONTROL
    public static final int fps = 60;
    public static final double TimePerTick = 1000000000 / fps;
    //SCREEN SIZE CONTROL
    public static final int cellSize = 35;
    public static int sideOffset = 4; 
    public static int downOffset = 3;
    //to keep a 10:15 game screen ratio, do not change anything below here
    public static final int screenH = (cellSize * (15 + downOffset));//  600 / 40 = 15 blocos de altura. 200 = status bar
    public static final int screenW = (cellSize * (10 + sideOffset));      //  400 / 40 = 10 blocos na largura
    //MAP CONTROL
    public static final int pI = 1, pJ = 2, pL = 3, pO = 4, pS = 5, pT = 6, pZ = 7;
    public static final int xMaxPos = 9;
    public static final int yMaxPos = 14;
    
    
}
