/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.orbs;

import game.SummativeGame;
import java.awt.Graphics;

/**
 *
 * @author carmc9538
 */
public class YellowOrb extends Orb{
    
    public static int roomX;
    public static int roomY;
    public static int x;
    public static int y;
    public static int l;
    public static int w;
    
    public YellowOrb(int x, int y, int orbRoomX, int orbRoomY){
        super(x, y, orbRoomX, orbRoomY);
        YellowOrb.roomX = orbRoomX;
        YellowOrb.roomY = orbRoomY;
        YellowOrb.x = x;
        YellowOrb.y = y;
        
        //l = image length;
        //w = image width;
        l = 15;
        w = 15;
    }
    
    @Override
    public void draw(Graphics g){
        g.setColor(SummativeGame.gameYellow);
        g.fillOval(x-(w/2), y-(l/2), w, l);
    }
        
    public static int getOrbRoomX(){
        return roomX;
    }
    public static int getOrbRoomY(){
        return roomY;
    }
}
