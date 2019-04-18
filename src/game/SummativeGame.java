package game;

import audio.MusicLoop;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.imageio.ImageIO;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import objects.Player;

public class SummativeGame extends JComponent implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 790622003833586344L;

    static final double xScreen = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final double yScreen = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    // Height and Width of our game
    static final int WIDTH = (int) xScreen*6/7;
    static final int HEIGHT = (int) yScreen*6/7;


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
    
    //Definitions of Position Variables
    public static int area = -1;
    
    //(Player)
    public static int playerPosX;
    public static int playerPosY;
    
    //Sounds
    public static final MusicLoop musicLoop = new MusicLoop();
    public static int songId;
    
    //Definitions of Colours
    public static Color gameGreen = new Color(151, 220, 51);
    public static Color gameBlue = new Color(55, 231, 93);
    public static Color gameYellow = new Color(192, 248, 61);
    public static Color gameDebug = new Color(255, 0, 255);
    
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
    
    public static boolean mapCreated = false;
    
    //Images
    BufferedImage startButton = loadImage("Start.png");
    BufferedImage premadeButton = loadImage("Premade Map.png");
    BufferedImage exitButton = loadImage("Exit.png");

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
        }catch(Exception e){
            e.printStackTrace();
        }
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
        if(area == -1){
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
            
            g.drawImage(startButton, 230, 525, null);
            g.drawImage(premadeButton, 460, 525, null);
            g.drawImage(exitButton, 690, 525, null);
        }
        try{
            user.draw(g);
        }
        catch(Exception e){}
        
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
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
        if(!mapCreated){
            
        } else{
            
        }
    }

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
            //Menu movement left/right, z to select
            if(!mapCreated){
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
					MapGen.generateMap();
                }
                //Make premade map
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 460 && user.getXPosition() <= 655){
                    
                }
                //Close on exit
                if(e.getKeyChar() == 'z' && user.getXPosition() >= 690 && user.getXPosition() <= 885){
                    System.exit(0);
                }
                //Bugtest location data
                if(e.getKeyChar() == 'f'){
                    System.out.println(user.getXPosition());
                }
            //Game movement up/down, z to select
            } else{
                if(e.getKeyChar() == 'a'){
                    user.adjustXPos(-3);
                }
                if(e.getKeyChar() == 'd'){
                    user.adjustXPos(+3);
                }
                if(e.getKeyChar() == 'w'){
                    user.adjustYPos(-3);
                }
                if(e.getKeyChar() == 's'){
                    user.adjustYPos(+3);
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
