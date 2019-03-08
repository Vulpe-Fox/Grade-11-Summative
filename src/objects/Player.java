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
    
    public Player(int x, int y){
        super(x, y);
        
        //length = image length;
        //width = image width;
        //Debug:
        l = 10;
        w = 10;
    }
    
    public void draw(Graphics g){
        move();
        //TODO: Input image here to place at X/Y pos
        //Debug:
        g.setColor(SummativeGame.gameDebug);
        g.fillOval(x, y, w, l);
    }
    
    private void move(){
        
    }
    
    public void adjustXPos(int i){
        xAdjust = i;
    }
    
}
