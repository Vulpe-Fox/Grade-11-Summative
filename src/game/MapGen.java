package game;

import java.util.ArrayList;
import java.util.Random;

import objects.Room;

public class MapGen {

	public static Room[][] map = new Room[8][8];
	
	public static ArrayList<Integer> rooms = new ArrayList<>();
	
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
				map[i][j] = new Room(chooseRoomType(i, j), i, j);
			}
		}
	}
	
	/*Room types:
	 * 0: N-E
	 * 1: N-E-S
	 * 2: N-E-W
	 * 3: E-S
	 * 4: E-S-W
	 * 5: E-W
	 * 6: N-S
	 * 7: N-S-W
	 * 8: N-W
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
		
		return 1;
	}
	
}
