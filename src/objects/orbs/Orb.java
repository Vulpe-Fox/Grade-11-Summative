/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects.orbs;

import objects.Entity;

/**
 *
 * @author carmc9538
 */
public class Orb extends Entity{
    
    protected static int orbRoomX;
    protected static int orbRoomY;
    
    public Orb(int x, int y, int orbRoomX, int orbRoomY) {
        super(x, y);
        Orb.orbRoomX = orbRoomX;
        Orb.orbRoomY = orbRoomY;
    }
    
    public static int getOrbRoomX(){
        return orbRoomX;
    }
    public static int getOrbRoomY(){
        return orbRoomY;
    }
    
    public void setRoom(int x, int y){
        orbRoomX = x;
        orbRoomY = y;
    }
}
