/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.SummativeGame;

/**
 *
 * @author carmc9538
 */
public class MusicLoop extends Thread{
    public static boolean gameRunning = true;
    
    public static int songId = 0;
    
    public static String path = new String();
    
    @Override
    public void run(){
        while(gameRunning = true){
            songId = SummativeGame.getSongId();
            switch(songId){
                //1: Title Scrren Music
                case 1:
                    //path = "audio/";
                case 2:
                    
                case 3:
                    
                case 4:    
                
                case 5:
                
                default:
                    
            }
            if(path.equals("ignore")){
                int buffer = Music.loadSound(path);
                Source source = new Source();

                source.play(buffer);

                source.delete();
                Music.cleaning();
            }
            
            notify();
        }
    }
}
