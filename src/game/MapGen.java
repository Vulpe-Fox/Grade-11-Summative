package game;

import java.util.ArrayList;
import java.util.Random;

import objects.Room;

public class MapGen {

	public static Room[][] map = new Room[8][8];
	
	public static ArrayList<Integer> rooms = new ArrayList<>();
        
        public static boolean top;
        public static boolean right;
        public static boolean bottom;
        public static boolean left;
        public static boolean undefTop;
        public static boolean undefRight;
        public static boolean undefBottom;
        public static boolean undefLeft;
	
	public static int verticalEdge;
	public static int horizontalEdge;
	public static int type;
	
	public static Random random = new Random();
	
	public MapGen() {
		
	}
	
	public static void generateMap(){
		//Select edge adjustments (1-7)
		verticalEdge = random.nextInt(6)+1;
		horizontalEdge = random.nextInt(6)+1;
		//Generate rooms, one by one
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Room(-1, i, j);
			}
		}
                //Set room types, one by one
                for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].setRoomType(chooseRoomType(i, j));
			}
		}
	}
	
	/*Room types:
	 * 0: N-E-S
	 * 1: N-S
	 * 2: N-S-W
	 * 3: N-E-W
	 * 4: N-E
	 * 5: N-W
	 * 6: E-W
	 * 7: E-S
	 * 8: E-S-W
	 * 9: S-W
	 * 10: N-S, E-W
	 */
	
	public static int chooseRoomType(int posX, int posY) {
		if(posX == 0 && posY == 0) {
			//First room always open to all sides
			type = 10;
		} else {
			//Generates list of possibilities to choose from
			type = generateList(posX, posY);
		}
		
		return type;
	}
	
	public static int generateList(int posX, int posY) {
                top = false;
                right = false;
                bottom = false;
                left = false;
                int relativeX;
                int relativeY;
		//Above room
                //If passes top threshhold, adjust X position, but ensure the posX doesn't go out of bounds
                if(posY-1 < 0){
                   relativeY = 7;
                   if(posX-horizontalEdge < 0){
                       relativeX = posX-horizontalEdge+8;
                   } else{
                       relativeX = posX-horizontalEdge;
                   }
                } else{
                    //If doesn't pass top threshhold, X position remains the same
                    relativeY = posY-1;
                    relativeX = posX;
                }
                //Check all undefined or southfacing rooms
                if((map[relativeX][relativeY].getRoomType() >= 0 && map[relativeX][relativeY].getRoomType() <= 2) ||
                        (map[relativeX][relativeY].getRoomType() >= 7 || map[relativeX][relativeY].getRoomType() <= 10)){
                   top = true; 
                }
                if(map[relativeX][relativeY].getRoomType() == -1){
                    undefTop = true;
                }
                        
                //Right room
                //If passes right threshhold, adjust Y position, but ensure the posY doesn't go out of bounds
                if(posX+1 > 7){
                   relativeX = 0;
                   if(posY-verticalEdge < 0){
                       relativeY = posY-verticalEdge+8;
                   } else{
                       relativeY = posY-verticalEdge;
                   }
                } else{
                    //If doesn't pass top threshhold, X position remains the same
                    relativeY = posY;
                    relativeX = posX+1;
                }
                //Check all undefined or westfacing rooms
                if(map[relativeX][relativeY].getRoomType() == 2 || map[relativeX][relativeY].getRoomType() == 3 || 
                        map[relativeX][relativeY].getRoomType() == 5 || map[relativeX][relativeY].getRoomType() == 6 || 
                        map[relativeX][relativeY].getRoomType() == 8 || map[relativeX][relativeY].getRoomType() == 10){
                    right = true;
                }
                if(map[relativeX][relativeY].getRoomType() == -1){
                    undefRight = true;
                }
                
                //Bottom room
                //If passes bottom threshhold, adjust X position, but ensure the posX doesn't go out of bounds
                if(posY+1 > 7){
                   relativeY = 0;
                   if(posX+horizontalEdge < 0){
                       relativeX = posX+horizontalEdge-8;
                   } else{
                       relativeX = posX+horizontalEdge;
                   }
                } else{
                    //If doesn't pass top threshhold, X position remains the same
                    relativeY = posY+1;
                    relativeX = posX;
                }
                //Check all undefined or northfacing rooms
                if((map[relativeX][relativeY].getRoomType() >=0 && map[relativeX][relativeY].getRoomType() <= 5) || 
                        map[relativeX][relativeY].getRoomType() == 10){
                    bottom = true;
                }
                if(map[relativeX][relativeY].getRoomType() == -1){
                    undefBottom = true;
                }
                
                //Left room
                //If passes left threshhold, adjust Y position, but ensure the posY doesn't go out of bounds
                if(posX-1 < 0){
                   relativeX = 7;
                   if(posY+verticalEdge > 7){
                       relativeY = posY+verticalEdge-8;
                   } else{
                       relativeY = posY+verticalEdge;
                   }
                } else{
                    //If doesn't pass top threshhold, X position remains the same
                    relativeY = posY;
                    relativeX = posX-1;
                }
                //Check all undefined or eastfacing rooms
                if(map[relativeX][relativeY].getRoomType() == 0 || map[relativeX][relativeY].getRoomType() == 3 ||
                        map[relativeX][relativeY].getRoomType() == 4 || map[relativeX][relativeY].getRoomType() == 6 ||
                        map[relativeX][relativeY].getRoomType() == 7 || map[relativeX][relativeY].getRoomType() == 8 ||
                        map[relativeX][relativeY].getRoomType() == 10){
                    left = true;
                }
                if(map[relativeX][relativeY].getRoomType() == -1){
                    undefLeft = true;
                }
                //Logically make arraylist of possible rooms, then randomly choose from them
                
		return 1;
	}
	
}
