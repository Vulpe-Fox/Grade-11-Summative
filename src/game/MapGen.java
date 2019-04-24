package game;

import java.util.ArrayList;
import java.util.Random;

import objects.Room;

public class MapGen {

	public static Room[][] map = new Room[8][8];
	
	public static Integer[] greenOrb = new Integer[2];
	public static Integer[] redOrb = new Integer[2];
	public static Integer[] blueOrb = new Integer[2];
	public static Integer[] yellowOrb = new Integer[2];
	
	public static Integer[] greenRecepticle = new Integer[2];
	public static Integer[] redRecepticle = new Integer[2];
	public static Integer[] blueRecepticle = new Integer[2];
	public static Integer[] yellowRecepticle = new Integer[2];
	
	public static Integer[] trickRoom = new Integer[2];
	public static Integer[] startingRoom = new Integer[2];

	public static ArrayList<Integer> rooms = new ArrayList<>();
	public static ArrayList<Integer[]> coordinates = new ArrayList<>();

	public static boolean top;
	public static boolean right;
	public static boolean bottom;
	public static boolean left;
	public static boolean undefTop;
	public static boolean undefRight;
	public static boolean undefBottom;
	public static boolean undefLeft;
	
	public static boolean coordinatesFound;
	public static boolean duplicatePoints;
        public static boolean doesMapExist;

	public static int verticalEdge;
	public static int horizontalEdge;
	public static int type;
	public static int position;

	public static Random random = new Random();

	public MapGen() {

	}

	public static void generateMap() {
		// Select edge adjustments (1-7)
		verticalEdge = random.nextInt(6) + 1;
		horizontalEdge = random.nextInt(6) + 1;
		// Generate rooms, one by one, setting their type to -1
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Room(-1, i, j);
			}
		}
		// Set room types, one by one
                while(true){
                    doesMapExist = true;
                    for (int i = 0; i < map.length; i++) {
                            for (int j = 0; j < map[i].length; j++) {
                                    map[i][j].setRoomType(chooseRoomType(i, j));
                                    if(map[i][j].getRoomType() >= 10) {
                                            System.out.print(map[i][j].getRoomType() + " ");
                                    } else {
                                            System.out.print("0" + map[i][j].getRoomType() + " ");
                                    }
                            }
                            System.out.println("");
                    }
                    for(int i = 0; i < map.length; i++){
                        for(int j = 0; j < map.length; j++){
                            if(map[i][j].getRoomType() == -1){
                                doesMapExist=false;
                            }
                        }
                    }
                    if(doesMapExist){
                        break;
                    }
                    System.out.println();
                }
		System.out.println("Vertical edge shift: " + verticalEdge);
		System.out.println("Horizontal edge shift: " + horizontalEdge);
		
		setRooms();
                
                SummativeGame.user.setXPosition(557);
                SummativeGame.user.setYPosition(425);
                
		//Print what's in rooms to the console
		for(int i = 0; i < coordinates.size(); i++) {
			System.out.println(coordinates.get(i)[0] + ", " + coordinates.get(i)[1]);
		}
	}

	/* Room types: 
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
         * 11: N
         * 12: E
         * 13: S
         * 14: W
	 */

	private static void setRooms() {
		coordinates.clear();
		coordinatesFound = false;
		greenOrb[0] = random.nextInt(8);
		greenOrb[1] = random.nextInt(8);
		coordinates.add(greenOrb);
		while(!coordinatesFound) {
			redOrb[0] = random.nextInt(8);
			redOrb[1] = random.nextInt(8);
			if(compareCoordinates(redOrb, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(redOrb);
		coordinatesFound = false;
		while(!coordinatesFound) {
			blueOrb[0] = random.nextInt(8);
			blueOrb[1] = random.nextInt(8);
			if(compareCoordinates(blueOrb, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(blueOrb);
		coordinatesFound = false;
		while(!coordinatesFound) {
			yellowOrb[0] = random.nextInt(8);
			yellowOrb[1] = random.nextInt(8);
			if(compareCoordinates(yellowOrb, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(yellowOrb);
		coordinatesFound = false;
		while(!coordinatesFound) {
			greenRecepticle[0] = random.nextInt(8);
			greenRecepticle[1] = random.nextInt(8);
			if(compareCoordinates(greenRecepticle, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(greenRecepticle);
		coordinatesFound = false;
		while(!coordinatesFound) {
			redRecepticle[0] = random.nextInt(8);
			redRecepticle[1] = random.nextInt(8);
			if(compareCoordinates(redRecepticle, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(redRecepticle);
		coordinatesFound = false;
		while(!coordinatesFound) {
			blueRecepticle[0] = random.nextInt(8);
			blueRecepticle[1] = random.nextInt(8);
			if(compareCoordinates(blueRecepticle, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(blueRecepticle);
		coordinatesFound = false;
		while(!coordinatesFound) {
			yellowRecepticle[0] = random.nextInt(8);
			yellowRecepticle[1] = random.nextInt(8);
			if(compareCoordinates(yellowRecepticle, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(yellowRecepticle);
		coordinatesFound = false;
		while(!coordinatesFound) {
			trickRoom[0] = random.nextInt(8);
			trickRoom[1] = random.nextInt(8);
			if(compareCoordinates(trickRoom, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(trickRoom);
		coordinatesFound = false;
		while(!coordinatesFound) {
			startingRoom[0] = random.nextInt(8);
			startingRoom[1] = random.nextInt(8);
			if(compareCoordinates(startingRoom, coordinates)) {
				coordinatesFound = true;
			}
		}
		coordinates.add(startingRoom);
	}
        //Checks to ensure coordinates don't coincide
	private static boolean compareCoordinates(Integer[] type, ArrayList<Integer[]> coord) {
		duplicatePoints = false;
		for(int i = 0; i < coord.size(); i++) {
			if(coord.get(i)[0].equals(type[0]) && coord.get(i)[1].equals(type[1])) {
				duplicatePoints = true;
			}
		}
            return !duplicatePoints;
	}

	public static int chooseRoomType(int posX, int posY) {
		if (posX == 0 && posY == 0) {
			// First room always open to all sides
			type = 10;
		} else {
			// Generates list of possibilities to choose from
			type = generateList(posX, posY);
		}

		return type;
	}

	public static int generateList(int posY, int posX) {
		//Reset room configurations
		rooms.clear();
		top = false;
		right = false;
		bottom = false;
		left = false;
		undefTop = false;
		undefRight = false;
		undefBottom = false;
		undefLeft = false;
		int relativeX;
		int relativeY;
                
		// Above room
		// If passes top threshhold, adjust X position left, but ensure the posX doesn't go out of bounds
		if (posY - 1 < 0) {
			relativeY = 7;
			if (posX - verticalEdge < 0) {
				relativeX = posX - verticalEdge + 8;
			} else {
				relativeX = posX - verticalEdge;
			}
		} else {
			// If doesn't pass top threshhold, X position remains the same
			relativeY = posY - 1;
			relativeX = posX;
		}
		// Check whether top room is south-facing or undefined
		if (map[relativeY][relativeX].getRoomType() == 0 || map[relativeY][relativeX].getRoomType() == 1
			|| map[relativeY][relativeX].getRoomType() == 2 || map[relativeY][relativeX].getRoomType() == 7
                        || map[relativeY][relativeX].getRoomType() == 8 || map[relativeY][relativeX].getRoomType() == 9
                        || map[relativeY][relativeX].getRoomType() == 10 || map[relativeY][relativeX].getRoomType() == 13) {
			top = true;
		}
		if (map[relativeY][relativeX].getRoomType() == -1) {
			undefTop = true;
		}

		// Right room
		// If passes right threshhold, adjust Y position downwards, but ensure the posY doesn't go out of bounds
		if (posX + 1 > 7) {
			relativeX = 0;
			if (posY + horizontalEdge > 7) {
				relativeY = posY + horizontalEdge - 8;
			} else {
				relativeY = posY + horizontalEdge;
			}
		} else {
			// If doesn't pass right threshhold, Y position remains the same
			relativeY = posY;
			relativeX = posX + 1;
		}
		// Check whether right room is west-facing or undefined
		if (map[relativeY][relativeX].getRoomType() == 2 || map[relativeY][relativeX].getRoomType() == 3
				|| map[relativeY][relativeX].getRoomType() == 5 || map[relativeY][relativeX].getRoomType() == 6
				|| map[relativeY][relativeX].getRoomType() == 8 || map[relativeY][relativeX].getRoomType() == 10 
                                || map[relativeY][relativeX].getRoomType() == 14) {
			right = true;
		}
		if (map[relativeY][relativeX].getRoomType() == -1) {
			undefRight = true;
		}

		// Bottom room
		// If passes bottom threshhold, adjust X position to the right, but ensure the posX doesn't go out of bounds
		if (posY + 1 > 7) {
			relativeY = 0;
			if (posX + verticalEdge > 7) {
				relativeX = posX + verticalEdge - 8;
			} else {
				relativeX = posX + verticalEdge;
			}
		} else {
			// If doesn't pass bottom threshhold, X position remains the same
			relativeY = posY + 1;
			relativeX = posX;
		}
		// Check whether bottom room is north-facing or undefined
		if (map[relativeY][relativeX].getRoomType() == 0 || map[relativeY][relativeX].getRoomType() == 1  || map[relativeX][relativeY].getRoomType() == 2 
				|| map[relativeY][relativeX].getRoomType() == 3 || map[relativeY][relativeX].getRoomType() == 4
				|| map[relativeY][relativeX].getRoomType() == 5 || map[relativeY][relativeX].getRoomType() == 10
                                || map[relativeY][relativeX].getRoomType() == 11) {
			bottom = true;
		}
		if (map[relativeY][relativeX].getRoomType() == -1) {
			undefBottom = true;
		}
		
		// Left room
		// If passes left threshhold, adjust Y position upwards, but ensure the posY doesn't go out of bounds
		if (posX - 1 < 0) {
			relativeX = 7;
			if (posY - horizontalEdge < 0) {
				relativeY = posY - horizontalEdge + 8;
			} else {
				relativeY = posY - horizontalEdge;
			}
		} else {
			// If doesn't pass left threshhold, Y position remains the same
			relativeY = posY;
			relativeX = posX - 1;
		}
		// Check whether left room is east-facing or undefined
		if (map[relativeY][relativeX].getRoomType() == 0 || map[relativeY][relativeX].getRoomType() == 3
				|| map[relativeY][relativeX].getRoomType() == 4 || map[relativeY][relativeX].getRoomType() == 6
				|| map[relativeY][relativeX].getRoomType() == 7 || map[relativeY][relativeX].getRoomType() == 8
				|| map[relativeY][relativeX].getRoomType() == 10 || map[relativeY][relativeX].getRoomType() == 12) {
			left = true;
		}
		if (map[relativeY][relativeX].getRoomType() == -1) {
			undefLeft = true;
		}
		// Logically make arraylist of possible rooms, then randomly choose from them
		// Type 0
		if (((top && right && bottom) || (top && right && undefBottom) || (top && undefRight && undefBottom)
				|| (top && undefRight && bottom) || (undefTop && right && bottom) || (undefTop && right && undefBottom)
				|| (undefTop && undefRight && undefBottom) || (undefTop && undefRight && bottom)) && !left) {
			rooms.add(0);
		}
		// Type 1
		if(((top && bottom) || (top && undefBottom) || (undefTop && bottom) || (undefTop && undefBottom)) && !right && !left) {
			rooms.add(1);
		}
		// Type 2
		if (((top && left && bottom) || (top && left && undefBottom) || (top && undefLeft && undefBottom)
				|| (top && undefLeft && bottom) || (undefTop && left && bottom) || (undefTop && left && undefBottom)
				|| (undefTop && undefLeft && undefBottom) || (undefTop && undefLeft && bottom)) && !right) {
			rooms.add(2);
		}
		// Type 3
		if (((top && left && right) || (top && left && undefRight) || (top && undefLeft && undefRight)
				|| (top && undefLeft && right) || (undefTop && left && right) || (undefTop && left && undefRight)
				|| (undefTop && undefLeft && undefRight) || (undefTop && undefLeft && right)) && !bottom) {
			rooms.add(3);
		}
		// Type 4
		if(((top && right) || (top && undefRight) || (undefTop && right) || (undefTop && undefRight)) && !left && !bottom) {
			rooms.add(4);
		}
		// Type 5
		if(((top && left) || (top && undefLeft) || (undefTop && left) || (undefTop && undefLeft)) && !right && !bottom) {
			rooms.add(5);
		}
		// Type 6
		if(((right && left) || (right && undefLeft) || (undefRight && left) || (undefRight && undefLeft)) && !top && !bottom) {
			rooms.add(6);
		}
		// Type 7
		if(((bottom && right) || (bottom && undefRight) || (undefBottom && right) || (undefBottom && undefRight)) && !left && !top) {
			rooms.add(7);
		}
		// Type 8
		if (((right && left && bottom) || (right && left && undefBottom) || (right && undefLeft && undefBottom)
				|| (right && undefLeft && bottom) || (undefRight && left && bottom) || (undefRight && left && undefBottom)
				|| (undefRight && undefLeft && undefBottom) || (undefRight && undefLeft && bottom)) && !top) {
			rooms.add(8);
		}
		// Type 9
		if(((bottom && left) || (bottom && undefLeft) || (undefBottom && left) || (undefBottom && undefLeft)) && !right && !top) {
			rooms.add(9);
		}
		// Type 10
		if((top && right && bottom && left) || (top && right && bottom && undefLeft) || (top && right && undefBottom && left) || (top && right && bottom && undefLeft)
				|| (top && undefRight && bottom && left) || (top && undefRight && bottom && undefLeft) || (top && undefRight && undefBottom && left) || (top && undefRight && bottom && undefLeft)
				|| (undefTop && right && bottom && left) || (undefTop && right && bottom && undefLeft) || (undefTop && right && undefBottom && left) || (undefTop && right && bottom && undefLeft)
				|| (undefTop && undefRight && bottom && left) || (undefTop && undefRight && bottom && undefLeft) || (undefTop && undefRight && undefBottom && left) || (undefTop && undefRight && bottom && undefLeft)) {
			rooms.add(10);
		}
                //Type 11
                if((top || undefTop) && !left && !right && !bottom && !undefLeft && !undefBottom && !undefRight){
                    rooms.add(11);
                }
                //Type 12
                if((right || undefRight) && !left && !top && !bottom  && !undefLeft && !undefBottom && !undefTop){
                    rooms.add(12);
                }
                //Type 13
                if((bottom || undefBottom) && !left && !right && !top  && !undefLeft && !undefTop && !undefRight){
                    rooms.add(13);
                }
                //Type 14
                if((left || undefLeft) && !top && !right && !bottom  && !undefTop && !undefBottom && !undefRight){
                    rooms.add(14);
                }
		//Chooses from arraylist
		if(rooms.size() > 0) {
                        /*System.out.println("Rooms List:");
                        for(int i = 0; i < rooms.size(); i++){
                            System.out.println(rooms.get(i));
                        }*/
			position = random.nextInt(rooms.size());
			return rooms.get(position);
		} else {
                    System.out.println();
                    System.out.println(top + " " + right + " " + bottom + " " + left);
                    System.out.println(undefTop + " " + undefRight + " " + undefBottom + " " + undefLeft);
                    return -1;
		}
	}

}
