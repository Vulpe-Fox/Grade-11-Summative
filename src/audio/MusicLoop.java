/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.SummativeGame;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC;
import static org.lwjgl.openal.ALC10.ALC_DEFAULT_DEVICE_SPECIFIER;
import static org.lwjgl.openal.ALC10.alcCreateContext;
import static org.lwjgl.openal.ALC10.alcGetString;
import static org.lwjgl.openal.ALC10.alcMakeContextCurrent;
import static org.lwjgl.openal.ALC10.alcOpenDevice;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

/**
 *
 * @author carmc9538
 */
public class MusicLoop extends Thread{
    public static boolean gameRunning = true;
    public static boolean firstRun = true;
    public static boolean musicLoaded = false;
    public static boolean musicPlaying = false;
    
    public static int songId = 0;
    
    public static String path = new String();
    
    @Override
    public void run(){
        System.out.println("Initializing music");
        //Music.initialization();
        
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        long device = alcOpenDevice(defaultDeviceName);
        int[] attributes = {0};
        long context = alcCreateContext(device, attributes);

        System.out.println("Creating sound capabilities");
        
        alcMakeContextCurrent(context);
        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);
        
        ShortBuffer rawAudioBuffer;

        int channels;
        int sampleRate;
        
        if(firstRun){
            System.out.println("Music Initialized");
            firstRun = false;
        }
        
        while(gameRunning = true){
            songId = SummativeGame.getSongId();
            if(!musicLoaded){
                switch(songId){
                    //1: Title Scrren Music
                    case 1:
                        path = "TitleTrack.wav";
                    case 2:

                    case 3:

                    case 4:    

                    case 5:

                    default:
                }
                System.out.println("Music Loaded: " + path);
                musicLoaded = true;
            }
            if(!path.equals("") && musicLoaded && !musicPlaying){
                int buffer = Music.loadSound(path);
                Source source = new Source();

                source.play(buffer);

                source.delete();
                Music.cleaning();
                
            }
            if(musicLoaded && !musicPlaying){
                System.out.println("Playing music: " + path);
                AL10.alSourcePlay(Music.loadSound(path));
                musicPlaying = true;
            }
        }
    }
}
