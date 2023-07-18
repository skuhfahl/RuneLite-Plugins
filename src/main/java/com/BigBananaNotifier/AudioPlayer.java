package com.BigBananaNotifier;

import java.io.File;

import javax.sound.sampled.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AudioPlayer {
    private final String path = "src/main/resources/big_banana.wav";
    private Clip clip;


    public AudioPlayer()  {
        initialize();
    }

    public void initialize() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(0);
        }catch(Exception e){
            log.warn("Could not play sound");
        }
    }


    public void playAudio() {
        clip.start();
    }
}
