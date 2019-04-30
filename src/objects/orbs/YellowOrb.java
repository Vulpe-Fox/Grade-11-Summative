/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.orbs;

import game.MapGen;
import game.SummativeGame;
import java.awt.Graphics;

/**
 *
 * @author carmc9538
 */
public class YellowOrb extends Orb{
    
    public static int roomX;
    public static int roomY;
    
    public YellowOrb(int x, int y, int orbRoomX, int orbRoomY){
        super(x, y, orbRoomX, orbRoomY);
        YellowOrb.roomX = orbRoomX;
        YellowOrb.roomY = orbRoomY;
        
        //l = image length;
        //w = image width;
        l = 15;
        w = 15;
    }
    
    @Override
    public void draw(Graphics g){
        if(SummativeGame.user.getRoomX() == roomX && SummativeGame.user.getRoomY() == roomY){
            g.fillOval(x-(w/2), y-(l/2), w, l);
        }
    }
}
