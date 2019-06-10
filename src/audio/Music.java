/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
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
public class Music{
    
    private static List<Integer> buffers = new ArrayList<Integer>();
    private static FileInputStream fileName;
    private static BufferedInputStream file;
    
    
    public static void initialization(){
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
    
   public static void setListenerData(){
       AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
       AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
   }
    
    public static int loadSound(String path){
        try{
            fileName = new FileInputStream(path);
            file = new BufferedInputStream(fileName);
        } catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println(fileName);
            System.out.println(file);
        }        
        int buffer = AL10.alGenBuffers();
        buffers.add(buffer);
        WaveData waveFile = WaveData.create(file);
        AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
        waveFile.dispose();
        return buffer;
    }
    
    public static void cleaning(){
        buffers.forEach((buffer) -> {AL10.alDeleteBuffers(buffer);});
        ALC.destroy();
    }
    
}
