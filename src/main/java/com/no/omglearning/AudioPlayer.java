package com.no.omglearning;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class AudioPlayer {
    static boolean playing = true;
    private static final String AUDIO_FILE = "Explore.mp3";
    private static MediaPlayer mediaPlayer;
    public static void play() {
        playing = true;
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer(new Media(new File(AUDIO_FILE).toURI().toString()));
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
            mediaPlayer.play();
        } else {
            mediaPlayer.play();
        }
        setVolume(0.2);
    }

    public static void pause() {
        if (mediaPlayer != null & playing) {
            mediaPlayer.pause();
            playing = false;
        }
    }

    public static void setVolume(double value) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(value);
        }
    }
    public static void playing(){
        if (playing){
            play();
            playing = true;
        }
        else {
            pause();
            playing = false;

        }
    }
}
