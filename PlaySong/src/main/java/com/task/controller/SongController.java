package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.task.dto.SongRequest;
import com.task.service.SongService;

@RestController
@RequestMapping("/api")
public class SongController {

    @Autowired
    private SongService songService;

    // ✅ Set Song Time and Path
    @PostMapping("/set-song-time")
    public String setSongTime(@RequestBody SongRequest request) { 
        if (request.getTime() == null || request.getTime().isEmpty() ||
            request.getPath() == null || request.getPath().isEmpty()) {
            return "Error: Time and Path are required!";
        }
        String sanitizedPath = request.getPath().replaceAll("^\"|\"$", "");

        songService.save(request.getTime(), sanitizedPath);
        return "Song scheduled at " + request.getTime() + " with path: " + sanitizedPath;
       
    }

    // ✅ Get Latest Scheduled Song Details
    @GetMapping("/get-song-time")
    public String getSongTime() {
        return songService.getLatestSchedule()
            .map(schedule -> "Scheduled time: " + schedule.getTime() + " | Path: " + schedule.getPath())
            .orElse("No song schedule found.");
    }

    // ✅ Delete All Scheduled Songs
    @DeleteMapping("/delete-song-time")
    public String deleteSongTime() {
        songService.deleteSchedule();
        return "Scheduled song deleted.";
    }
}
