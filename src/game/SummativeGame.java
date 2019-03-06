package game;

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

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class SummativeGame extends JComponent implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 790622003833586344L;

    static double xScreen = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static double yScreen = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
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
    
    //Definitions of Colours
    public static Color gameGreen = new Color(151, 220, 51);
    public static Color gameBlue = new Color(55, 231, 93);
    
    //Definitions of Drawing Points
    public static int xTriangle[] = new int[3];
    public static int yTriangle[] = new int[3];
    
    public static int xQuad[] = new int[4];
    public static int yQuad[] = new int[4];
    
    public static int xHex[] = new int[6];
    public static int yHex[] = new int[6];
    
    public static int xOct[] = new int[8];
    public static int yOct[] = new int[8];

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

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        
        //Title Box
        g.setColor(gameGreen);
        
        defineTriangle(xTriangle, 1, 200, 200);
        defineTriangle(yTriangle, 1, 130, 250);
        g.fillPolygon(xTriangle, yTriangle, 3);
        
        defineTriangle(xTriangle, WIDTH-1, WIDTH-200, WIDTH-200);
        g.fillPolygon(xTriangle, yTriangle, 3);
        
        g.drawRect(200, 130, WIDTH-2*200, 120);
        //Title Box
        
        //Words
        g.setColor(gameBlue);
        //T
        defineOct(xOct, 230, 290, 290, 270, 270, 250, 250, 230);
        defineOct(yOct, 150, 150, 170, 170, 230, 230, 170, 170);
        g.fillPolygon(xOct, yOct, 8);
        //H
        defineQuad(xQuad, 320, 340, 340, 320);
        defineQuad(yQuad, 150, 150, 230, 230);
        g.fillPolygon(xQuad, yQuad, 4);
        defineQuad(xQuad, 380, 360, 360, 380);
        g.fillPolygon(xQuad, yQuad, 4);
        
        
        
        
        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here
        
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void gameLoop() {

    }

    private void defineTriangle(int[] array, int p1, int p2, int p3) {
        array[0] = p1;
        array[1] = p2;
        array[2] = p3;
    }
    
    private void defineQuad(int[] xQuad, int p1, int p2, int p3, int p4){
        xQuad[0] = p1;
        xQuad[1] = p2;
        xQuad[2] = p3;
        xQuad[3] = p4;
    }

    private void defineOct(int[] xOct, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
        xOct[0] = p1;
        xOct[1] = p2;
        xOct[2] = p3;
        xOct[3] = p4;
        xOct[4] = p5;
        xOct[5] = p6;
        xOct[6] = p7;
        xOct[7] = p8;
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

        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {

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

}
