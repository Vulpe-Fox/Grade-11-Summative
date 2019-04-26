/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import game.SummativeGame;
import java.awt.Graphics;

/**
 *
 * @author carmc9538
 */
public class Player extends Entity{
    
    private int xAdjust;
    private int yAdjust;
    
    public static int roomX;
    public static int roomY;
    
    public static int itemHeld = 0;
    
    public Player(int x, int y){
        super(x, y);
        
        //l = image length;
        //w = image width;
        l = 10;
        w = 10;
    }
    
    @Override
    public void draw(Graphics g){
        move();
        //No Item
        if(itemHeld == 0){
            g.setColor(SummativeGame.gameDebug);
        }
        //Green Orb
        if(itemHeld == 1){
            g.setColor(SummativeGame.gameGreen);
        }
        //Red Orb
        if(itemHeld == 2){
            g.setColor(SummativeGame.gameRed);
        }
        //Blue Orb
        if(itemHeld == 3){
            g.setColor(SummativeGame.gameBlue);
        }
        //Yellow Orb
        if(itemHeld == 4){
            g.setColor(SummativeGame.gameYellow);
        }
        g.fillOval(x-(w/2), y-(l/2), w, l);
    }
    
    private void move(){
        x += xAdjust;
        y += yAdjust;
    }
    
    public void adjustXPos(int i){
        xAdjust = i;
    }
    
    public void adjustYPos(int i){
        yAdjust = i;
    }
    
    public void setXPosition(int i){
       x = i; 
    }
    
    public void setYPosition(int i){
       y = i; 
    }
    
    public int getXPosition(){
        return x;
    }
    
    public int getYPosition(){
        return y;
    }
    
    public void setPosition(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    public void setRoom(int x, int y){
        roomX = x;
        roomY = y;
    }
    
    public int getRoomX(){
        return roomX;
    }
    
    public int getRoomY(){
        return roomY;
    }
    
}
