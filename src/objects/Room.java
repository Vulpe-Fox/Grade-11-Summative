/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author carmc9538
 */
public class Room {
    public int roomType;
    
    public Room(int roomType, int roomLocationX, int roomLocationY){
        roomType = this.roomType;
    }
    
    public int getRoomType(){
        return roomType;
    }
}
