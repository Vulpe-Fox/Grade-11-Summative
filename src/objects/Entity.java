/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Graphics;

/**
 *
 * @author carmc9538
 */
public class Entity {
    
    protected static int x;
    protected static int y;
    protected static int l;
    protected static int w;
    
    public Entity(int xPos, int yPos){
        Entity.x = xPos;
        Entity.y = yPos;
    }
    
    public int getXPos(){
        return x;
    }
    public int getYPos(){
        return y;
    }
    public int getLength(){
        return l;
    }
    public int getWidth(){
        return w;
    }
    
    public void draw(Graphics g){
        
    }
}
