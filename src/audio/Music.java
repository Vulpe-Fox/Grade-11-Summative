/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;

/**
 *
 * @author carmc9538
 */
public class Music{
    
    private static List<Integer> buffers = new ArrayList<Integer>();
    
    public static void initialization(){
        ALCreate();
    }
    
    public static void ALCreate(){
    //    ALC.create();
    }
    
   public static void setListenerData(){
       AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
       AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
   }
    
    public static int loadSound(String filename){
        int buffer = AL10.alGenBuffers();
        buffers.add(buffer);
        WaveData waveFile = WaveData.create(filename);
        AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
        waveFile.dispose();
        return buffer;
    }
    
    /*public static void cleaning(){
        buffers.forEach((buffer) -> {
            AL10.alDeleteBuffers(buffer);
        });
        ALC.destroy();
    }*/
    
}
