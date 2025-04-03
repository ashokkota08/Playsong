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

    
    @PostMapping("/set-song-time")
    public String setSongTime(@RequestBody SongRequest request) { 
        if (request.getTime() == null || request.getTime().isEmpty() ||
            request.getPath() == null || request.getPath().isEmpty()) {
            return "Error: Time and Path are required!";
        }
        String Path = request.getPath().replaceAll("^\"|\"$", "");

        songService.save(request.getTime(), Path);
        return "Song scheduled at " + request.getTime() + " with path: " + Path;
       
    }

 
    @GetMapping("/get-song-time")
    public String getSongTime() {
        return songService.getLatestSchedule()
            .map(schedule -> "Scheduled time: " + schedule.getTime() + " | Path: " + schedule.getPath())
            .orElse("No song schedule found.");
    }

  
    @DeleteMapping("/delete-song-time")
    public String deleteSongTime() {
        songService.deleteSchedule();
        return "Scheduled song deleted.";
    }
}
