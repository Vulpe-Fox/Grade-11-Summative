package game;

import audio.Music;
import audio.MusicLoop;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import objects.Player;
import objects.orbs.BlueOrb;
import objects.orbs.GreenOrb;
import objects.orbs.RedOrb;
import objects.orbs.YellowOrb;
import objects.receptacles.BlueReceptacle;
import objects.receptacles.GreenReceptacle;
import objects.receptacles.RedReceptacle;
import objects.receptacles.YellowReceptacle;

public class SummativeGame extends JComponent implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 790622003833586344L;
    
    //Bugfix
    public static Scanner bugfixConsoleInput = new Scanner(System.in);
    public static int bugfixX;
    public static int bugfixY;

    //Screen size
    static final double xScreen = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final double yScreen = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    // Height and Width of our game
    static final int WIDTH = 1097;
    static final int HEIGHT = 877;


    //Title of the window
    String title = "This Game";

    // sets the framerate and delay for our game
    // this calculates the number of milliseconds per frame
    // you just need to select an approproate framerate
    int desiredFPS = 60;
    int desiredTime = Math.round((1000 / desiredFPS));

    // timer used to run the game loop
    // this is what keeps our time running smoothly :)
    Timer gameTimer;
    
    //Randomizer
    public static Random random = new Random();
    public static int randomRoomX;
    public static int randomRoomY;
    
    //Definitions of Position Variables
    public static int area = -1;
    public static Integer[] heldPlayerLocation = new Integer[2];
    public static boolean northSouth;
    
    //(Player)
    public static int playerPosX;
    public static int playerPosY;
    
    public static int savedPlayerPosX;
    public static int savedPlayerPosY;
    
    //Sounds
    public static MusicLoop musicLoop = new MusicLoop();
    public static int songId;
    
    //Definitions of Colours
    public static Color gameGreen = new Color(151, 220, 51);
    public static Color gameRed = new Color(190, 33, 33);
    public static Color gameBlue = new Color(55, 231, 133);
    public static Color gameYellow = new Color(192, 238, 61);
    public static Color gameDebug = new Color(255, 0, 255);
    public static Color gameGrey = new Color(128, 170, 158);
    
    //Definitions of Drawing Points
    public static int xTriangle[] = new int[3];
    public static int yTriangle[] = new int[3];
    
    public static int xQuad[] = new int[4];
    public static int yQuad[] = new int[4];
    
    public static int xHex[] = new int[6];
    public static int yHex[] = new int[6];
    
    public static int xOct[] = new int[8];
    public static int yOct[] = new int[8];
    
    //Definitions of Game Objects
    public static Player user;
    public static GreenOrb greenOrb;
    public static RedOrb redOrb;
    public static BlueOrb blueOrb;
    public static YellowOrb yellowOrb;
    public static GreenReceptacle greenReceptacle;
    public static RedReceptacle redReceptacle;
    public static BlueReceptacle blueReceptacle;
    public static YellowReceptacle yellowReceptacle;
    
    //Game running boolean
    public static boolean mapCreated = false;
    public static boolean gameWon = false;
    
    public static boolean greenComplete = false;
    public static boolean redComplete = false;
    public static boolean blueComplete = false;
    public static boolean yellowComplete = false;
    
    //Variables for the timer
    public static double timeStarted;
    public static double timeEnded;
    public static double timeTaken;
    
    //Images/Fonts
    BufferedImage startButton = loadImage("Start.png");
    BufferedImage premadeButton = loadImage("Premade Map.png");
    BufferedImage exitButton = loadImage("Exit.png");
    BufferedImage resumeButton = loadImage("Resume.png");
    BufferedImage restartButton = loadImage("Restart.png");
    BufferedImage controls = loadImage("Controls.png");
    BufferedImage endGame = loadImage("Endgame Screen.png");
    
    Font arialBlack = new Font("Arial Black", Font.ITALIC, 40);

    // YOUR GAME VARIABLES WOULD GO HERE
    // GAME VARIABLES END HERE    
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)
    public SummativeGame() {
        // creates a windows to show my game
        JFrame frame = new JFrame(title);
        // sets the size of my game
   
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(this);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        this.addMouseMotionListener(m);
        this.addMouseWheelListener(m);
        this.addMouseListener(m);

        // Set things up for the game at startup
        preSetup();

        // Start the game loop
        gameTimer = new Timer(desiredTime, this);
        gameTimer.setRepeats(true);
        gameTimer.start();
    }
    
    
    public BufferedImage loadImage(String filename){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(filename));
        }catch(IOException e){}
        return image;
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE

    	//Background
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
        
        
        if(area == 39){
            g.drawImage(endGame, 0, 0, null);
            
            g.setFont(arialBlack);
            String output = String.format("This run took %.2f minutes", timeTaken/60);
            g.drawString(output, 240, 445);
        }
        if(area != 39 && area != -2 && area != -1){
            //Corners of walls
            g.setColor(gameGrey);
            
            defineHex(xHex, 360, 490, 490, 390, 390, 360);
            defineHex(yHex, 255, 255, 285, 285, 385, 385);
            g.fillPolygon(xHex, yHex, 6);
            
            defineHex(xHex, 750, 620, 620, 720, 720, 750);
            defineHex(yHex, 255, 255, 285, 285, 385, 385);
            g.fillPolygon(xHex, yHex, 6);
            
            defineHex(xHex, 360, 490, 490, 390, 390, 360);
            defineHex(yHex, 645, 645, 615, 615, 515, 515);
            g.fillPolygon(xHex, yHex, 6);
            
            defineHex(xHex, 750, 620, 620, 720, 720, 750);
            defineHex(yHex, 645, 645, 615, 615, 515, 515);
            g.fillPolygon(xHex, yHex, 6);
        }
        //Top wall
        if(area != 39 && area != -2 && area != -1 && area != 0 && area != 1 && area != 2 && area != 3 && area != 4 && area != 5 && area != 10 && area != 11){
            defineQuad(xQuad, 490, 620, 620, 490);
            defineQuad(yQuad, 255, 255, 285, 285);
            g.fillPolygon(xQuad, yQuad, 4);
        }
        //Right wall
        if(area != 39 && area != -2 && area != -1 && area != 0 && area != 3 && area != 4 && area != 6 && area != 7 && area != 8 && area != 10 && area != 12){
            defineQuad(xQuad, 720, 750, 750, 720);
            defineQuad(yQuad, 385, 385, 515, 515);
            g.fillPolygon(xQuad, yQuad, 4);
        }
        //Bottom wall
        if(area != 39 && area != -2 && area != -1 && area != 0 && area != 1 && area != 2 && area != 7 && area != 8 && area != 9 && area != 10 && area != 13){
            defineQuad(xQuad, 490, 620, 620, 490);
            defineQuad(yQuad, 615, 615, 645, 645);
            g.fillPolygon(xQuad, yQuad, 4);
        }
        //Left wall
        if(area != 39 && area != -2 && area != -1 && area != 2 && area != 3 && area != 5 && area != 6 && area != 8 && area != 9 && area != 10 && area != 14){
            defineQuad(xQuad, 390, 360, 360, 390);
            defineQuad(yQuad, 385, 385, 515, 515);
            g.fillPolygon(xQuad, yQuad, 4);
        }
        //Roomtype 10 depends on where you enter, seperate north-south/east-west wall logic:
        if(area == 10 && northSouth){
            defineQuad(xQuad, 720, 750, 750, 720);
            defineQuad(yQuad, 385, 385, 515, 515);
            g.fillPolygon(xQuad, yQuad, 4);
            defineQuad(xQuad, 390, 360, 360, 390);
            defineQuad(yQuad, 385, 385, 515, 515);
            g.fillPolygon(xQuad, yQuad, 4);
        }
        if(area == 10 && !northSouth){
            defineQuad(xQuad, 490, 620, 620, 490);
            defineQuad(yQuad, 255, 255, 285, 285);
            g.fillPolygon(xQuad, yQuad, 4);
            defineQuad(xQuad, 490, 620, 620, 490);
            defineQuad(yQuad, 615, 615, 645, 645);
            g.fillPolygon(xQuad, yQuad, 4);
        }
        
        if(!mapCreated){
            //Title Box
            g.setColor(gameGreen);

            defineTriangle(xTriangle, 1, 200, 200);
            defineTriangle(yTriangle, 1, 130, 250);
            g.fillPolygon(xTriangle, yTriangle, 3);

            defineTriangle(xTriangle, 1097, 920, 920);
            g.fillPolygon(xTriangle, yTriangle, 3);

            g.drawRect(200, 130, 720, 120);
            //Title Box

            //Line
            g.drawLine(575, 130, 545, 250);
            //Line

            //Words
            g.setColor(gameBlue);
            //T
            defineOct(xOct, 220, 280, 280, 260, 260, 240, 240, 220);
            defineOct(yOct, 150, 150, 170, 170, 230, 230, 170, 170);
            g.fillPolygon(xOct, yOct, 8);
            //H
            defineQuad(xQuad, 300, 320, 320, 300);
            defineQuad(yQuad, 150, 150, 230, 230);
            g.fillPolygon(xQuad, yQuad, 4);
            defineQuad(xQuad, 360, 340, 340, 360);
            g.fillPolygon(xQuad, yQuad, 4);
            defineQuad(xQuad, 320, 340, 340, 320);
            defineQuad(yQuad, 182, 182, 198, 198);
            g.fillPolygon(xQuad, yQuad, 4);
            //I
            defineOct(xOct, 380, 440, 440, 420, 420, 400, 400, 380);
            defineOct(yOct, 150, 150, 165, 165, 230, 230, 165, 165);
            g.fillPolygon(xOct, yOct, 8);
            defineQuad(xQuad, 380, 440, 440, 380);
            defineQuad(yQuad, 215, 215, 230, 230);
            g.fillPolygon(xQuad, yQuad, 4);
            //S
            defineOct(xOct, 460, 520, 520, 475, 475, 490, 490, 460);
            defineOct(yOct, 150, 150, 166, 166, 182, 182, 198, 198);
            g.fillPolygon(xOct, yOct, 8);
            defineOct(xOct, 490, 520, 520, 460, 460, 505, 505, 490);
            defineOct(yOct, 182, 182, 230, 230, 214, 214, 198, 198);
            g.fillPolygon(xOct, yOct, 8);
            //Recolour
            g.setColor(gameYellow);
            //G
            defineOct(xOct, 590, 650, 650, 605, 605, 650, 650, 590);
            defineOct(yOct, 150, 150, 166, 166, 214, 214, 230, 230);
            g.fillPolygon(xOct, yOct, 8);
            defineHex(xHex, 620, 650, 650, 635, 635, 620);
            defineHex(yHex, 182, 182, 214, 214, 198, 198);
            g.fillPolygon(xHex, yHex, 6);
            //A
            defineOct(xOct, 670, 730, 730, 715, 715, 685, 685, 670);
            defineOct(yOct, 150, 150, 182, 182, 166, 166, 182, 182);
            g.fillPolygon(xOct, yOct, 8);
            defineOct(xOct, 670, 730, 730, 715, 715, 685, 685, 670);
            defineOct(yOct, 182, 182, 230, 230, 198, 198, 230, 230);
            g.fillPolygon(xOct, yOct, 8);
            //M
            defineOct(xOct, 750, 765, 780, 780, 765, 765, 750, 750);
            defineOct(yOct, 150, 150, 176, 198, 182, 230, 230, 150);
            g.fillPolygon(xOct, yOct, 8);
            defineOct(xOct, 780, 795, 810, 810, 795, 795, 780, 780);
            defineOct(yOct, 176, 150, 150, 230, 230, 182, 198, 176);
            g.fillPolygon(xOct, yOct, 8);
            //E
            defineOct(xOct, 830, 880, 880, 845, 845, 880, 880, 830);
            defineOct(yOct, 150, 150, 166, 166, 214, 214, 230, 230);
            g.fillPolygon(xOct, yOct, 8);
            defineQuad(xQuad, 845, 865, 865, 845);
            defineQuad(yQuad, 182, 182, 198, 198);
            g.fillPolygon(xQuad, yQuad, 4);
            //Images on title screen
                g.drawImage(startButton, 230, 525, null);
                g.drawImage(premadeButton, 460, 525, null);
                g.drawImage(exitButton, 690, 525, null);
                g.drawImage(controls, 335, 275, null);
        } else if(area == -2){
            //Title Box
            g.setColor(gameGreen);

            defineTriangle(xTriangle, 1, 200, 200);
            defineTriangle(yTriangle, 1, 130, 250);
            g.fillPolygon(xTriangle, yTriangle, 3);

            defineTriangle(xTriangle, 1119, 920, 920);
            g.fillPolygon(xTriangle, yTriangle, 3);

            g.drawRect(200, 130, 720, 120);
            //Title Box

            //Line
            g.drawLine(575, 130, 545, 250);
            //Line

            //Words
            g.setColor(gameBlue);
            //T
            defineOct(xOct, 220, 280, 280, 260, 260, 240, 240, 220);
            defineOct(yOct, 150, 150, 170, 170, 230, 230, 170, 170);
            g.fillPolygon(xOct, yOct, 8);
            //H
            defineQuad(xQuad, 300, 320, 320, 300);
            defineQuad(yQuad, 150, 150, 230, 230);
            g.fillPolygon(xQuad, yQuad, 4);
            defineQuad(xQuad, 360, 340, 340, 360);
            g.fillPolygon(xQuad, yQuad, 4);
            defineQuad(xQuad, 320, 340, 340, 320);
            defineQuad(yQuad, 182, 182, 198, 198);
            g.fillPolygon(xQuad, yQuad, 4);
            //I
            defineOct(xOct, 380, 440, 440, 420, 420, 400, 400, 380);
            defineOct(yOct, 150, 150, 165, 165, 230, 230, 165, 165);
            g.fillPolygon(xOct, yOct, 8);
            defineQuad(xQuad, 380, 440, 440, 380);
            defineQuad(yQuad, 215, 215, 230, 230);
            g.fillPolygon(xQuad, yQuad, 4);
            //S
            defineOct(xOct, 460, 520, 520, 475, 475, 490, 490, 460);
            defineOct(yOct, 150, 150, 166, 166, 182, 182, 198, 198);
            g.fillPolygon(xOct, yOct, 8);
            defineOct(xOct, 490, 520, 520, 460, 460, 505, 505, 490);
            defineOct(yOct, 182, 182, 230, 230, 214, 214, 198, 198);
            g.fillPolygon(xOct, yOct, 8);
            //Recolour
            g.setColor(gameYellow);
            //G
            defineOct(xOct, 590, 650, 650, 605, 605, 650, 650, 590);
            defineOct(yOct, 150, 150, 166, 166, 214, 214, 230, 230);
            g.fillPolygon(xOct, yOct, 8);
            defineHex(xHex, 620, 650, 650, 635, 635, 620);
            defineHex(yHex, 182, 182, 214, 214, 198, 198);
            g.fillPolygon(xHex, yHex, 6);
            //A
            defineOct(xOct, 670, 730, 730, 715, 715, 685, 685, 670);
            defineOct(yOct, 150, 150, 182, 182, 166, 166, 182, 182);
            g.fillPolygon(xOct, yOct, 8);
            defineOct(xOct, 670, 730, 730, 715, 715, 685, 685, 670);
            defineOct(yOct, 182, 182, 230, 230, 198, 198, 230, 230);
            g.fillPolygon(xOct, yOct, 8);
            //M
            defineOct(xOct, 750, 765, 780, 780, 765, 765, 750, 750);
            defineOct(yOct, 150, 150, 176, 198, 182, 230, 230, 150);
            g.fillPolygon(xOct, yOct, 8);
            defineOct(xOct, 780, 795, 810, 810, 795, 795, 780, 780);
            defineOct(yOct, 176, 150, 150, 230, 230, 182, 198, 176);
            g.fillPolygon(xOct, yOct, 8);
            //E
            defineOct(xOct, 830, 880, 880, 845, 845, 880, 880, 830);
            defineOct(yOct, 150, 150, 166, 166, 214, 214, 230, 230);
            g.fillPolygon(xOct, yOct, 8);
            defineQuad(xQuad, 845, 865, 865, 845);
            defineQuad(yQuad, 182, 182, 198, 198);
            g.fillPolygon(xQuad, yQuad, 4);
            
                g.drawImage(restartButton, 230, 525, null);
                g.drawImage(resumeButton, 460, 525, null);
                g.drawImage(exitButton, 690, 525, null);
                g.drawImage(controls, 335, 275, null);
        }
        
        
        //Draw user if not on end screen
        if(area != 39){
            try{
                user.draw(g);
            }
            catch(Exception e){}
        }
        
        //Draw orbs if map is created
        if(mapCreated && area != -2){
            if(SummativeGame.user.getRoomX() == GreenOrb.getOrbRoomX() && SummativeGame.user.getRoomY() == GreenOrb.getOrbRoomY()){
                greenOrb.draw(g);
            }
            if(SummativeGame.user.getRoomX() == RedOrb.getOrbRoomX() && SummativeGame.user.getRoomY() == RedOrb.getOrbRoomY()){
                redOrb.draw(g);
            }
            if(SummativeGame.user.getRoomX() == BlueOrb.getOrbRoomX() && SummativeGame.user.getRoomY() == BlueOrb.getOrbRoomY()){
                blueOrb.draw(g);
            }
            if(SummativeGame.user.getRoomX() == YellowOrb.getOrbRoomX() && SummativeGame.user.getRoomY() == YellowOrb.getOrbRoomY()){
                yellowOrb.draw(g);
            }
            if(SummativeGame.user.getRoomX() == GreenReceptacle.getReceptacleRoomX() && SummativeGame.user.getRoomY() == GreenReceptacle.getReceptacleRoomY()){
                greenReceptacle.draw(g);
            }
            if(SummativeGame.user.getRoomX() == RedReceptacle.getReceptacleRoomX() && SummativeGame.user.getRoomY() == RedReceptacle.getReceptacleRoomY()){
                redReceptacle.draw(g);
            }
            if(SummativeGame.user.getRoomX() == BlueReceptacle.getReceptacleRoomX() && SummativeGame.user.getRoomY() == BlueReceptacle.getReceptacleRoomY()){
                blueReceptacle.draw(g);
            }
            if(SummativeGame.user.getRoomX() == YellowReceptacle.getReceptacleRoomX() && SummativeGame.user.getRoomY() == YellowReceptacle.getReceptacleRoomY()){
                yellowReceptacle.draw(g);
            }
        }
        
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        System.out.println(WIDTH + " " + HEIGHT);
        // Any of your pre setup before the loop starts should go here
        //Define player start
        playerPosX = 560;
        playerPosY = 575;
        user = new Player(playerPosX, playerPosY);
        //Music.initialization();
        //Music.setListenerData();
        songId = 1;
        musicLoop.start();
        synchronized(musicLoop){
            try{
                System.out.println("Waiting for music to Initialize...");
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void gameLoop() {
    	if(area == -1 || area == -2) {
    		songId = 1;
    	} else {
    		songId = 2;
    	}
        //Startup menu
        if(!mapCreated){
            if(user.getXPosition() > 885){
                user.setXPosition(230);
            }
            if(user.getXPosition() < 230){
                user.setXPosition(885);
            }
        } 
        //Ingame accessable menu
        else if(area == -2){
            if(user.getXPosition() > 885){
                user.setXPosition(230);
            }
            if(user.getXPosition() < 230){
                user.setXPosition(885);
            }
        }
        //During game events
        else if(area != 39){
            area = MapGen.map[user.getRoomY()][user.getRoomX()].getRoomType();
            
            //Collision logic
            //Topleft corner
                if(user.getXPosition() < 390 && user.getYPosition() < 285){
                    user.setPosition(395, 290);
                }
                if(user.getXPosition() >= 390 && user.getXPosition() <= 490 && user.getYPosition() < 285){
                    user.setPosition(user.getXPosition(), 290);
                }
                if(user.getXPosition() < 390 && user.getYPosition() >= 285 && user.getYPosition() <= 385){
                    user.setPosition(395, user.getYPosition());
                }
            //Topright corner
                if(user.getXPosition() > 720 && user.getYPosition() < 285){
                    user.setPosition(715, 290);
                }
                if(user.getXPosition() <= 720 && user.getXPosition() >= 620 && user.getYPosition() < 285){
                    user.setPosition(user.getXPosition(), 290);
                }
                if(user.getXPosition() > 720 && user.getYPosition() >= 285 && user.getYPosition() <= 385){
                    user.setPosition(715, user.getYPosition());
                }
            //Bottomleft corner
                if(user.getXPosition() < 390 && user.getYPosition() > 615){
                    user.setPosition(395, 610);
                }
                if(user.getXPosition() >= 390 && user.getXPosition() <= 490 && user.getYPosition() > 615){
                    user.setPosition(user.getXPosition(), 610);
                }
                if(user.getXPosition() < 390 && user.getYPosition() <= 615 && user.getYPosition() >= 515){
                    user.setPosition(395, user.getYPosition());
                }
            //Bottomright corner
                if(user.getXPosition() > 720 && user.getYPosition() > 615){
                    user.setPosition(715, 610);
                }
                if(user.getXPosition() <= 720 && user.getXPosition() >= 620 && user.getYPosition() > 615){
                    user.setPosition(user.getXPosition(), 610);
                }
                if(user.getXPosition() > 720 && user.getYPosition() <= 615 && user.getYPosition() >= 515){
                    user.setPosition(715, user.getYPosition());
                }
            //Top door: closed
                if(area != -2 && area != -1 && area != 0 && area != 1 && area != 2 && area != 3 && area != 4 && area != 5 && area != 10 && area != 11){
                    if(user.getYPosition() <= 285)  {
                        user.setPosition(user.getXPosition(), 290);
                    }
                }
            //Right door: closed
                if(area != -2 && area != -1 && area != 0 && area != 3 && area != 4 && area != 6 && area != 7 && area != 8 && area != 10 && area != 12){
                    if(user.getXPosition() >= 720){
                        user.setPosition(715, user.getYPosition());
                    }
                }
            //Bottom door: closed
                if(area != -2 && area != -1 && area != 0 && area != 1 && area != 2 && area != 7 && area != 8 && area != 9 && area != 10 && area != 13){
                    if(user.getYPosition() >= 615){
                        user.setPosition(user.getXPosition(), 610);
                    }
                }
            //Left door: closed
                if(area != -2 && area != -1 && area != 2 && area != 3 && area != 5 && area != 6 && area != 8 && area != 9 && area != 10 && area != 14){
                    if(user.getXPosition() <= 390){
                        user.setPosition(395, user.getYPosition());
                    }
                }
            //North-south room type 10 collision logic
                //East-West collision
                if(area == 10 && northSouth){
                    if(user.getXPosition() >= 720){
                        user.setPosition(715, user.getYPosition());
                    }
                    if(user.getXPosition() <= 390){
                        user.setPosition(395, user.getYPosition());
                    }
                }
                //North-South collision
                if(area == 10 && !northSouth){
                    if(user.getYPosition() <= 285)  {
                        user.setPosition(user.getXPosition(), 290);
                    }
                    if(user.getYPosition() >= 615){
                        user.setPosition(user.getXPosition(), 610);
                    }
                }
                
                //Travel between rooms (Player can't get a certain distance without collision normally, so this logic will always work)
                //Left
                if(user.getXPosition() <= 360){
                    user.setPosition(715, user.getYPosition());
                    if(user.getRoomY() >= 0){
                        user.setRoom(user.getRoomX()-1, user.getRoomY());
                    } else{
                        if(user.getRoomY()-MapGen.verticalEdge < 0){
                            user.setRoom(7, user.getRoomY()+8-MapGen.verticalEdge);
                        } else {
                            user.setRoom(7, user.getRoomY()-MapGen.verticalEdge);
                        }
                    }
                    northSouth = false;
                }
                //Right
                if(user.getXPosition() >= 750){
                    user.setPosition(395, user.getYPosition());
                    if(user.getRoomY() <= 7){
                        user.setRoom(user.getRoomX()+1, user.getRoomY());
                    } else{
                        if(user.getRoomY()+MapGen.verticalEdge > 7){
                            user.setRoom(0, user.getRoomY()-8+MapGen.verticalEdge);
                        } else {
                            user.setRoom(0, user.getRoomY()+MapGen.verticalEdge);
                        }
                    }
                    northSouth = false;
                }
                //Top
                if(user.getYPosition() <= 255){
                    user.setPosition(user.getXPosition(), 610);
                    if(user.getRoomY() >= 0){
                        user.setRoom(user.getRoomX(), user.getRoomY()-1);
                    } else{
                        if(user.getRoomY()-MapGen.horizontalEdge < 0){
                            user.setRoom(user.getRoomX()+8-MapGen.horizontalEdge, 7);
                        } else {
                            user.setRoom(user.getRoomX()-MapGen.horizontalEdge, 7);
                        }
                    }
                    northSouth = true;
                }
                //Bottom
                if(user.getYPosition() >= 645){
                    user.setPosition(user.getXPosition(), 290);
                    if(user.getRoomY() <= 7){
                        user.setRoom(user.getRoomX(), user.getRoomY()+1);
                    } else{
                        if(user.getRoomY()+MapGen.horizontalEdge > 7){
                            user.setRoom(user.getRoomX()-8+MapGen.horizontalEdge, 0);
                        } else {
                            user.setRoom(user.getRoomX()+MapGen.horizontalEdge, 0);
                        }
                    }
                    northSouth = true;
                }
                //Fix for a bug when crosses two thresholds in one move -- Causes a bug where walls sometimes close behind you. This makes sense for the game, so I'm keeping it!
                while(user.getRoomX() < 0 || user.getRoomX() > 7 || user.getRoomY() < 0 || user.getRoomY() > 7){
                    if(user.getRoomX() > 7){
                        if(user.getRoomY()+MapGen.verticalEdge >= 7){
                            user.setRoom(0, user.getRoomY()-8+MapGen.verticalEdge);
                        } else {
                            user.setRoom(0, user.getRoomY()+MapGen.verticalEdge);
                        }
                    }
                    if(user.getRoomY() > 7){
                        if(user.getRoomY()+MapGen.horizontalEdge >= 7){
                            user.setRoom(user.getRoomX()-8+MapGen.horizontalEdge, 0);
                        } else {
                            user.setRoom(user.getRoomX()+MapGen.horizontalEdge, 0);
                        }
                    }
                    if(user.getRoomX() < 0){
                        if(user.getRoomY()-MapGen.verticalEdge <= 0){
                            user.setRoom(7, user.getRoomY()+8-MapGen.verticalEdge);
                        } else {
                            user.setRoom(7, user.getRoomY()-MapGen.verticalEdge);
                        }
                    }
                    if(user.getRoomY() < 0){
                        if(user.getRoomY()-MapGen.horizontalEdge <= 0){
                            user.setRoom(user.getRoomX()+8-MapGen.horizontalEdge, 7);
                        } else {
                            user.setRoom(user.getRoomX()-MapGen.horizontalEdge, 7);
                        }
                    }
                }
                //Check if Trick Room then randomize which room player is in
                if(user.getRoomX() == MapGen.coordinates.get(8)[0] && user.getRoomY() == MapGen.coordinates.get(8)[1]){
                    randomRoomX = random.nextInt(8);
                    randomRoomY = random.nextInt(8);
                    user.setRoom(randomRoomX, randomRoomY);
                    System.out.println("You have entered the trick room and have been moved to coordinates: " + randomRoomX + ", " + randomRoomY);
                }
                
                //Update room
                try{
                    area = MapGen.map[user.getRoomY()][user.getRoomX()].getRoomType();
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(user.getRoomX() + ", " + user.getRoomY());
                }
            //Win condition, use orbs on receptacles
            if(GreenReceptacle.getReceptacleRoomX() == GreenOrb.getOrbRoomX() && GreenReceptacle.x >= GreenOrb.x-GreenOrb.l &&
                GreenReceptacle.x <= GreenOrb.x+GreenOrb.l && GreenReceptacle.getReceptacleRoomY() == GreenOrb.getOrbRoomY() &&
                GreenReceptacle.y >= GreenOrb.y-GreenOrb.w && GreenReceptacle.y <= GreenOrb.y+GreenOrb.w){
                GreenReceptacle.setReceptacleRoom(-1, -1);
                GreenOrb.setOrbRoom(-1, -1);
            }
            if(RedReceptacle.getReceptacleRoomX() == RedOrb.getOrbRoomX() && RedReceptacle.x >= RedOrb.x-RedOrb.l &&
                RedReceptacle.x <= RedOrb.x+RedOrb.l && RedReceptacle.getReceptacleRoomY() == RedOrb.getOrbRoomY() &&
                RedReceptacle.y >= RedOrb.y-RedOrb.w && RedReceptacle.y <= RedOrb.y+RedOrb.w){
                RedReceptacle.setReceptacleRoom(-1, -1);
                RedOrb.setOrbRoom(-1, -1);
            }
            if(BlueReceptacle.getReceptacleRoomX() == BlueOrb.getOrbRoomX() && BlueReceptacle.x >= BlueOrb.x-BlueOrb.l &&
                BlueReceptacle.x <= BlueOrb.x+BlueOrb.l && BlueReceptacle.getReceptacleRoomY() == BlueOrb.getOrbRoomY() &&
                BlueReceptacle.y >= BlueOrb.y-BlueOrb.w && BlueReceptacle.y <= BlueOrb.y+BlueOrb.w){
                BlueReceptacle.setReceptacleRoom(-1, -1);
                BlueOrb.setOrbRoom(-1, -1);
            }
            if(YellowReceptacle.getReceptacleRoomX() == YellowOrb.getOrbRoomX() && YellowReceptacle.x >= YellowOrb.x-YellowOrb.l &&
                YellowReceptacle.x <= YellowOrb.x+YellowOrb.l && YellowReceptacle.getReceptacleRoomY() == YellowOrb.getOrbRoomY() &&
                YellowReceptacle.y >= YellowOrb.y-YellowOrb.w && YellowReceptacle.y <= YellowOrb.y+YellowOrb.w){
                YellowReceptacle.setReceptacleRoom(-1, -1);
                YellowOrb.setOrbRoom(-1, -1);
            }
            //If all receptacles have been activated, set to endgame screen
            if(GreenReceptacle.getReceptacleRoomX() == -1 && GreenReceptacle.getReceptacleRoomY() == -1 &&
                RedReceptacle.getReceptacleRoomX() == -1 && RedReceptacle.getReceptacleRoomY() == -1 &&
                BlueReceptacle.getReceptacleRoomX() == -1 && BlueReceptacle.getReceptacleRoomY() == -1 &&
                YellowReceptacle.getReceptacleRoomX() == -1 && YellowReceptacle.getReceptacleRoomY() == -1){
                if(!gameWon){
                    timeEnded = System.currentTimeMillis();
                    timeTaken = (timeEnded-timeStarted)/1000;
                }
                gameWon = true;
                area = 39;
            }
        }
    }
    //Methods to draw shapes
    private void defineTriangle(int[] array, int p1, int p2, int p3) {
        array[0] = p1;
        array[1] = p2;
        array[2] = p3;
    }
    
    private void defineQuad(int[] array, int p1, int p2, int p3, int p4){
        array[0] = p1;
        array[1] = p2;
        array[2] = p3;
        array[3] = p4;
    }
    
    private void defineHex(int[] array, int p1, int p2, int p3, int p4, int p5, int p6){
        array[0] = p1;
        array[1] = p2;
        array[2] = p3;
        array[3] = p4;
        array[4] = p5;
        array[5] = p6;
    }

    private void defineOct(int[] array, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        array[0] = p1;
        array[1] = p2;
        array[2] = p3;
        array[3] = p4;
        array[4] = p5;
        array[5] = p6;
        array[6] = p7;
        array[7] = p8;
    }

    // Used to implement any of the Mouse Actions
    private class Mouse extends MouseAdapter {

        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e) {

        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        // if the scroll wheel has been moved
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Used to implements any of the Keyboard Actions
    private class Keyboard extends KeyAdapter {

        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e) {
            //Main menu movement left/right, z to select
            if(!mapCreated){
                if(e.getKeyChar() == 'a' && user.getXPosition() >= 220){
                    user.adjustXPos(-9);
                }
                if(e.getKeyChar() == 'd' && user.getXPosition() <= 890){
                    user.adjustXPos(+9);
                }
                //Make premade map
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 460 && user.getXPosition() <= 655){
                    mapCreated = true;
                    user.setPosition(555, 450);
                    MapGen.generatePremadeMap();
                    user.setRoom(MapGen.startingRoom[0], MapGen.startingRoom[1]);
                    area = MapGen.map[MapGen.startingRoom[1]][MapGen.startingRoom[0]].getRoomType();
                    timeStarted = System.currentTimeMillis();
                }
                //Make map
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 230 && user.getXPosition() <= 425){
                    mapCreated = true;
                    user.setPosition(555, 450);
                    MapGen.generateMap();
                    user.setRoom(MapGen.startingRoom[0], MapGen.startingRoom[1]);
                    area = MapGen.map[MapGen.startingRoom[1]][MapGen.startingRoom[0]].getRoomType();
                    timeStarted = System.currentTimeMillis();
                }
                //Close on exit
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 690 && user.getXPosition() <= 885){
                    Music.cleaning();
                    System.exit(0);
                }
                //Bugtest location data
                if(e.getKeyChar() == 'f'){
                    System.out.println(user.getXPosition());
                }
            //Game movement left/right, z to select; This is second menu
            } else if(mapCreated && area == -2 && !gameWon){
                if(e.getKeyChar() == 'a' && user.getXPosition() >= 220){
                    user.adjustXPos(-9);
                }
                if(e.getKeyChar() == 'd' && user.getXPosition() <= 890){
                    user.adjustXPos(+9);
                }
                //Make map
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 230 && user.getXPosition() <= 425){
                    mapCreated = true;
                    user.setPosition(555, 450);
                    heldPlayerLocation[0] = -1;
                    heldPlayerLocation[1] = -1;
                    MapGen.generateMap();
                    heldPlayerLocation = MapGen.startingRoom;
                    area = MapGen.map[heldPlayerLocation[1]][heldPlayerLocation[0]].getRoomType();
                    user.setRoom(heldPlayerLocation[0], heldPlayerLocation[1]);
                }
                //Resume game
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 460 && user.getXPosition() <= 655){
                    user.setPosition(savedPlayerPosX, savedPlayerPosY);
                    area = MapGen.map[heldPlayerLocation[1]][heldPlayerLocation[0]].getRoomType();
                    user.setRoom(heldPlayerLocation[0], heldPlayerLocation[1]);
                }
                //Close on exit
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 690 && user.getXPosition() <= 885){
                    System.exit(0);
                }
                //Bugtest location data
                if(e.getKeyChar() == 'f'){
                    System.out.println(user.getXPosition());
                }
            }
            //In game screen
            else if(!gameWon){
                //Movement left/right/up/down
                if(e.getKeyChar() == 'a'){
                    user.adjustXPos(-7);
                }
                if(e.getKeyChar() == 'd'){
                    user.adjustXPos(+7);
                }
                if(e.getKeyChar() == 'w'){
                    user.adjustYPos(-7);
                }
                if(e.getKeyChar() == 's'){
                    user.adjustYPos(+7);
                }
                //Pickup/drop orbs
                if(e.getKeyChar() == 'z'){
                    if(user.getHeldOrb() != 0){
                        if(user.getHeldOrb() == 1){
                            GreenOrb.setOrbRoom(user.getRoomX(), user.getRoomY());
                            GreenOrb.setOrbPosition(user.getXPosition(), user.getYPosition());
                            user.setHeldOrb(0);
                        }
                        if(user.getHeldOrb() == 2){
                            RedOrb.setOrbRoom(user.getRoomX(), user.getRoomY());
                            RedOrb.setOrbPosition(user.getXPosition(), user.getYPosition());
                            user.setHeldOrb(0);
                        }
                        if(user.getHeldOrb() == 3){
                            BlueOrb.setOrbRoom(user.getRoomX(), user.getRoomY());
                            BlueOrb.setOrbPosition(user.getXPosition(), user.getYPosition());
                            user.setHeldOrb(0);
                        }
                        if(user.getHeldOrb() == 4){
                            YellowOrb.setOrbRoom(user.getRoomX(), user.getRoomY());
                            YellowOrb.setOrbPosition(user.getXPosition(), user.getYPosition());
                            user.setHeldOrb(0);
                        }
                    } else{
                        if(user.getXPosition() >= GreenOrb.x-GreenOrb.l && user.getYPosition() >= GreenOrb.y-GreenOrb.w &&
                            user.getXPosition() <= GreenOrb.x+GreenOrb.l && user.getYPosition() <= GreenOrb.y+GreenOrb.w &&
                            user.getHeldOrb() == 0 && user.getRoomX() == GreenOrb.getOrbRoomX() && user.getRoomY() == GreenOrb.getOrbRoomY()){
                                user.setHeldOrb(1);
                                GreenOrb.setOrbRoom(-1, -1);
                        }
                        if(user.getXPosition() >= RedOrb.x-RedOrb.l && user.getYPosition() >= RedOrb.y-RedOrb.w &&
                            user.getXPosition() <= RedOrb.x+RedOrb.l && user.getYPosition() <= RedOrb.y+RedOrb.w && 
                            user.getHeldOrb() == 0 && user.getRoomX() == RedOrb.getOrbRoomX() && user.getRoomY() == RedOrb.getOrbRoomY()){
                                user.setHeldOrb(2);
                                RedOrb.setOrbRoom(-1, -1);
                        }
                        if(user.getXPosition() >= BlueOrb.x-BlueOrb.l && user.getYPosition() >= BlueOrb.y-BlueOrb.w &&
                            user.getXPosition() <= BlueOrb.x+BlueOrb.l && user.getYPosition() <= BlueOrb.y+BlueOrb.w &&
                            user.getHeldOrb() == 0 && user.getRoomX() == BlueOrb.getOrbRoomX() && user.getRoomY() == BlueOrb.getOrbRoomY()){
                                user.setHeldOrb(3);
                                BlueOrb.setOrbRoom(-1, -1);
                        }
                        if(user.getXPosition() >= YellowOrb.x-YellowOrb.l && user.getYPosition() >= YellowOrb.y-YellowOrb.w &&
                            user.getXPosition() <= YellowOrb.x+YellowOrb.l && user.getYPosition() <= YellowOrb.y+YellowOrb.w &&
                            user.getHeldOrb() == 0 && user.getRoomX() == YellowOrb.getOrbRoomX() && user.getRoomY() == YellowOrb.getOrbRoomY()){
                                user.setHeldOrb(4);
                                YellowOrb.setOrbRoom(-1, -1);
                        }
                    }
                }
                //Cheatcode for moving between rooms
                if(e.getKeyChar() == 'i'){
                    bugfixX = Integer.parseInt(bugfixConsoleInput.nextLine());
                    bugfixY = Integer.parseInt(bugfixConsoleInput.nextLine());
                    user.setRoom(bugfixX, bugfixY);
                    area = MapGen.map[bugfixY][bugfixX].getRoomType();
                }
                //Escape to menu
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    heldPlayerLocation[0] = user.getRoomX();
                    heldPlayerLocation[1] = user.getRoomY();
                    savedPlayerPosX = user.getXPosition();
                    savedPlayerPosY = user.getYPosition();
                    
                    user.setPosition(560, 575);
                    area = -2;
                }
            }
            //On the win screen
            else if(gameWon){
                //If enter is pressed, all the things which run the game logic will be cleared
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    gameWon = false;
                    mapCreated = false;
                    area = -1;
                    MapGen.coordinates.clear();
                    MapGen.map = null;
                    greenComplete = false;
                    redComplete = false;
                    blueComplete = false;
                    yellowComplete = false;
                    user.setPosition(560, 575);
                }
            }
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar() == 'a'){
                user.adjustXPos(0);
            }
            if(e.getKeyChar() == 'd'){
                user.adjustXPos(0);
            }
            if(e.getKeyChar() == 'w'){
                user.adjustYPos(0);
            }
            if(e.getKeyChar() == 's'){
                user.adjustYPos(0);
            }
            if(e.getKeyChar() == 'z'){
                
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        gameLoop();
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates an instance of my game
        @SuppressWarnings("unused")
        SummativeGame game = new SummativeGame();
    }
    
    public static int getSongId() {
        return songId;
    }

}
