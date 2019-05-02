/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.receptacles;

import game.SummativeGame;
import java.awt.Color;
import java.awt.Graphics;
import objects.Entity;

/**
 *
 * @author carmc9538
 */
public class RedReceptacle extends Entity{
    
    public static int roomX;
    public static int roomY;
    public static int x;
    public static int y;
    public static int l;
    public static int w;
    
    public RedReceptacle(int x, int y, int receptacleRoomX, int receptacleRoomY) {
        super(x, y);
        
        RedReceptacle.roomX = receptacleRoomX;
        RedReceptacle.roomY = receptacleRoomY;
        RedReceptacle.x = x;
        RedReceptacle.y = y;
        
        //l = image length;
        //w = image width;
        l = 15;
        w = 15;
    }
    
    @Override
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x-(5*w/8), y-(5*l/8), 5*w/4, 5*l/4);
        g.setColor(SummativeGame.gameRed);
        g.fillOval(x-(w/2), y-(l/2), w, l);
    }
    
    public static int getReceptacleRoomX(){
        return roomX;
    }
    public static int getReceptacleRoomY(){
        return roomY;
    }
    public static void setReceptacleRoom(int x, int y){
        roomX = x;
        roomY = y;
    }
    
}
