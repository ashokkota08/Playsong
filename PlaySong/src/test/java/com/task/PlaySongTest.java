/*package com.task;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlaySongTest {
    public static void main(String[] args) {
        // Provide the correct path to the MP3 file
        String filePath = "C:/Users/hp/Music/anuvanuvu.mp3";

        // Call the playSong method directly
        playSong(filePath);
    }

    public static void playSong(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Player player = new Player(fileInputStream);
            System.out.println("🎵 Playing: " + filePath);
            player.play(); // This will play the song
        } catch (FileNotFoundException e) {
            System.err.println("❌ File not found: " + filePath);
        } catch (JavaLayerException e) {
            System.err.println("❌ Error playing song: " + e.getMessage());
        }
    }
}*/

