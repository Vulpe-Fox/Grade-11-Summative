/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import org.lwjgl.openal.AL10;

/**
 *
 * @author carmc9538
 */
public class Source {
    
    public final int sourceId;
    
    public Source(){
        sourceId = AL10.alGenSources();
        AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
        AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
        AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
    }  
    
    public void play(int buffer){
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
        AL10.alSourcePlay(sourceId);
    }
    
    public void delete(){
        AL10.alDeleteSources(sourceId);
    }
    
}
