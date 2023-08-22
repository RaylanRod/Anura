package com.anura.view;

import com.anura.controller.GameLogic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;

public class Music {

    private static Clip backgroundClip;
    private static Float volume = 1.0f; // Default max volume min is 0.0/stop
    private static Clip FXClip;
    private static Float FXVolume = 1.0f;

    public static Float getVolume() {
        return volume;
    }

    public static float decreaseVolume() {
        if (volume > 0.1f) {
            volume -= 0.1F;
        }else{
            volume = 0.1f;
        }
        return volume;
    }

    public static float increaseVolume() {
        if (volume <= 1.0f) {
            volume += 0.1F;
        }else{
            volume = 1.0f;
        }
        return volume;
    }


    public static synchronized void playBGMusic(String fileName) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    stopBackgroundMusic();
                    backgroundClip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new BufferedInputStream(GameLogic.class.getResourceAsStream("/" + fileName)));
                    backgroundClip.open(inputStream);
                    setBGMVolume(volume);
                    backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }

    public static void setBGMVolume(float newVolume) {
        if (newVolume >= 0.1f && newVolume <= 1.0f && backgroundClip != null) {
            if (volume > 0.0f) {
                volume = newVolume;
                FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(newVolume) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
            }
        }
    }

    public static synchronized void playFX(String fileName) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    stopFX();
                    FXClip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            new BufferedInputStream(GameLogic.class.getResourceAsStream("/" + fileName)));
                    FXClip.open(inputStream);
                    // Move this line here
                    setFXVolume(FXVolume);
                    FXClip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void stopFX() {
        if (FXClip != null) {
            FXClip.close();
        }
    }

    public static void setFXVolume(float newVolume) {
        if (newVolume >= 0.0f && newVolume <= 1.0f && FXClip != null) {
            FXVolume = newVolume;
            FloatControl gainControl = (FloatControl) FXClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(newVolume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }
}