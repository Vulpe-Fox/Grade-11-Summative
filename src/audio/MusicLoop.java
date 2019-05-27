/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import game.SummativeGame;
import java.nio.ShortBuffer;
import org.lwjgl.openal.AL;
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
    
    public static int songId = 0;
    
    public static String path = new String();
    
    
    @Override
    public void start(){
        
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        long device = alcOpenDevice(defaultDeviceName);
        int[] attributes = {0};
        long context = alcCreateContext(device, attributes);

        alcMakeContextCurrent(context);
        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);
        
        ShortBuffer rawAudioBuffer;

        int channels;
        int sampleRate;
        
    }
    
    @Override
    public void run(){
        
        Music.initialization();
        System.out.println("Music Initialized");
        
        while(gameRunning = true){
            songId = SummativeGame.getSongId();
            switch(songId){
                //1: Title Scrren Music
                case 1:
                    path = "TitleTrack.wav";
                    System.out.println("Music Loaded: Title Screen");
                case 2:
                    
                case 3:
                    
                case 4:    
                
                case 5:
                
                default:
                    
            }
            if(!path.equals("")){
                int buffer = Music.loadSound(path);
                Source source = new Source();

                source.play(buffer);

                source.delete();
                Music.cleaning();
            }
        }
    }
}
