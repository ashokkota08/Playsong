package com.task.scheduler;


import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.task.service.SongService;
import com.task.model.SongSchedule;

@Component
public class TimeScheduler {
    @Autowired
    private SongService songService;

    @Scheduled(cron = "0 * * * * *") // Runs every minute to check the time
    public void checkAndPlaySong() {
        Optional<SongSchedule> scheduledSong = songService.getLatestSchedule();

        if (scheduledSong.isPresent()) {
            String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            SongSchedule song = scheduledSong.get();
            String scheduledTime = song.getTime();
            String filePath = song.getPath();

            System.out.println("🔍 Checking method: Current Time = " + currentTime + ", Scheduled Time = " + scheduledTime);

            if (currentTime.equals(scheduledTime)) {
                System.out.println("✅ Scheduled time reached! Playing song...");
                
                if (filePath == null || filePath.isEmpty()) {
                    System.err.println("❌ No file path provided for the song.");
                    return;
                }

                File file = new File(filePath);
                if (!file.exists()) {
                    System.err.println("❌ File not found: " + filePath);
                    return;
                }

                new Thread(() -> playSong(filePath)).start(); // Run in a new thread
            } else {
                System.out.println("⏳ Not yet time to play the song.");
            }
        } else {
            System.out.println("⚠️ No scheduled song found.");
        }
    }

    private void playSong(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            AdvancedPlayer player = new AdvancedPlayer(inputStream);
            player.play();
        } catch (Exception e) {
            System.err.println("❌ Error playing the song: " + e.getMessage());
        }
    }
}
