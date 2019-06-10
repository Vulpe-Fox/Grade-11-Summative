/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.SummativeGame;
import org.lwjgl.openal.AL10;
import static org.lwjgl.openal.AL10.alGetSourcei;

/**
 *
 * @author carmc9538
 */
public class MusicLoop extends Thread{
    public static boolean gameRunning = true;
    public static boolean firstRun = true;
    public static boolean musicLoaded = false;
    public static boolean musicPlaying = false;
    
    public static int songId = 1;
    
    public static String path = new String();
    
    @Override
    public void run(){
        //Initialize the music listener and add audio context
        System.out.println("Initializing music");
        Music.initialization();
        Music.setListenerData();
        System.out.println("Music Initialized");
        //Loop while game is running
        while(gameRunning){
            //Get songid for a specific zone in the game
            songId = SummativeGame.getSongId();
            //If music is unloaded, choose an audio track using the songid
            while(!musicLoaded){
                switch(songId){
                    //1: Title Scrren Music
                    case 1:
                        path = "TitleTrack.wav";
                    case 2:
                    case 3:
                }
                if(path.equals("")){
                    
                } else{
                    System.out.println("Music Loaded: " + path);
                    //Set musicloaded to true
                    musicLoaded = true;
                }
            }
            //If the title track for music is loaded, initialize a source using the wav file
            if(path.equals("TitleTrack.wav") && musicLoaded){
                int buffer = Music.loadSound(path);
                Source source = new Source();
                
                //Play music
                System.out.println("Playing music: " + path);
                musicPlaying = true;
                source.play(buffer);
                //While music is loaded, keep checking whether A) Area has changed -- or B) Music has stopped
                while(musicLoaded){
                    //Area not title
                    if(SummativeGame.area != -1){
                        source.delete();
                        Music.cleaning();
                        musicLoaded = false;
                    }
                    //Music not playing
                    if(AL10.alGetSourcei(source.sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_STOPPED){
                        source.delete();
                        Music.cleaning();
                        musicLoaded = false;
                    }
                }
                
            } else if(path.equals("[][]")){
                        
            }
        }
    }
}
